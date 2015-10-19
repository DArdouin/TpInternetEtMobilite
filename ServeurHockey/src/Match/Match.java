/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Match;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocole.Methodes;
import protocole.Request;

/**
 *
 * @author Damien
 */
public class Match implements Serializable, Runnable{
    /**
     * 
     */
    private Equipe equipeDomicile;
    /**
     * 
     */
    private Equipe equipeExterieur;
    private Integer nbPenaliteDomicile ;
    private Integer nbPenaliteExterieur ;
    private Integer nbButsDomicile ;
    private Integer nbButsExterieur ;
    private String dateDebut  ;    
    private LinkedList<Request> listeDeRequete;
    private HashMap<String,Request> parisEquipeExterieur;
    private HashMap<String,Request> parisEquipeDomicile;   

    /**
     * Permet de créer un Match, en passant les équipes en paramètres
     * @param e1 La première équipe
     * @param e2 La seconde équipe
     * @param date
     */
    public Match(Equipe e1, Equipe e2, String date){
        equipeDomicile = e1;
        equipeExterieur = e2;
        nbButsDomicile = 0 ;
        nbButsExterieur = 0 ;
        nbPenaliteDomicile = 0 ;
        nbPenaliteExterieur = 0 ;
        dateDebut = date ;        
        listeDeRequete = new LinkedList<Request>();
        //On initialise nos HashMap, qui contiennent les sommes de chaque parieur
        parisEquipeExterieur = new HashMap<>();
        parisEquipeDomicile = new HashMap<>();
    }

    public Equipe getEquipeDomicile() {
        return equipeDomicile;
    }

    public Equipe getEquipeExterieur() {
        return equipeExterieur;
    }

    public long getTemps(){
        try {
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date actualDate = new Date();
            Date scheduledDate = format.parse(dateDebut);
            
            long diff = (actualDate.getTime() - scheduledDate.getTime())/1000 ;
            
            //System.out.println("Date actuelle : " + actualDate + "\nDate Match : " + scheduledDate);
            //System.out.println("Diff : " + diff);
            //System.out.println("Secondes : " + diff / 1000);
            
            if(diff < 0 ){
                return -1 ;
            }
            else if(diff >= (60*60)){
                return 0 ;
            }
            else {
                return diff ;
            }
        } catch (ParseException ex) {
            Logger.getLogger(Match.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public Integer getNbPenaliteDomicile() {
        return nbPenaliteDomicile;
    }

    public Integer getNbPenaliteExterieur() {
        return nbPenaliteExterieur;
    }

    public Integer getNbButsDomicile() {
        return nbButsDomicile;
    }

    public Integer getNbButsExterieur() {
        return nbButsExterieur;
    }

    public void setEquipeDomicile(Equipe equipeDomicile) {
        this.equipeDomicile = equipeDomicile;
    }

    public void setEquipeExterieur(Equipe equipeExterieur) {
        this.equipeExterieur = equipeExterieur;
    }

    public void setNbPenaliteDomicile(Integer nbPenaliteDomicile) {
        this.nbPenaliteDomicile = nbPenaliteDomicile;
    }

    public void setNbPenaliteExterieur(Integer nbPenaliteExterieur) {
        this.nbPenaliteExterieur = nbPenaliteExterieur;
    }

    public void setNbButsDomicile(Integer nbButsDomicile) {
        this.nbButsDomicile = nbButsDomicile;
    }

    public void setNbButsExterieur(Integer nbButsExterieur) {
        this.nbButsExterieur = nbButsExterieur;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    @Override
    public String toString() {
        return " " + equipeDomicile.getNom() + " Versus " + equipeExterieur.getNom() ;
    }
    
     /**
     * Permet d'ajouter un Paris à la queue
     * @param p Le paris à ajouter dans la liste
     */
    public void ajouterRequete(Request p){
        listeDeRequete.add(p);
    }

    /**
     * C'est ici que l'on va gérer les paris (calcul des sommes, ...)
     */
    @Override
    public void run() {
        try {
            //Tant que notre seconde mi-temps n'est pas terminée
            while(getTemps()<=3000){
                if(!listeDeRequete.isEmpty()){
                    //On récupère le paris dans la liste de requête
                    Request nouvelleRequete = listeDeRequete.removeFirst();
                    System.out.println("Paris pour l'équipe " + nouvelleRequete.getParis().getNomDeLequipe() + " d'une somme de " + nouvelleRequete.getParis().getSomme() + "€");
                    if(nouvelleRequete.getParis().getNomDeLequipe().equals(getEquipeDomicile().getNom())) //On cherche sur quelle équipe parier
                        monCompute(parisEquipeDomicile, nouvelleRequete);//On ajoute la requête contenante le paris à la liste
                    else if(nouvelleRequete.getParis().getNomDeLequipe().equals(getEquipeExterieur().getNom())) //On cherche sur quelle équipe parier
                        monCompute(parisEquipeExterieur, nouvelleRequete);
                    avertirParisOk(nouvelleRequete, nouvelleRequete.getAddress(), nouvelleRequete.getPort());
                } 
                else {
                    Thread.sleep(2000);
                }
            }
            
            //On attend la fin du match
            while(getTemps()!=0)
                Thread.sleep(30000);
            
            //Le match est terminé, on envois la somme à tout les gagnants
            if(nbButsDomicile.compareTo(nbButsExterieur) >= 0){ //Si l'équipe domicile gagne
                avertirParieursGagnants(parisEquipeDomicile);//On avertis toutes les personnes de leur victoire
            }
            else if(nbButsDomicile.equals(nbButsExterieur)) {
                System.out.println("Egalité !");
            }
            else{
                avertirParieursGagnants(parisEquipeExterieur);//On avertis toutes les personnes de leur victoire
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Match.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Permet d'ajouter un paris pour une équipe
     * @param map La table contenant les paris d'une équipe
     * @param nouveauParis Le paris que l'on veut record
     */
    private void monCompute(HashMap<String,Request> map, Request nouvelleRequete){
        if(map.get(nouvelleRequete.getAddress()) == null)
            map.put(nouvelleRequete.getAddress(),nouvelleRequete); //Si la clé n'existe pas, on l'ajoute
        else
        {
            int montantParie = (int) map.get(nouvelleRequete.getAddress()).getParis().getSomme(); //On récupère le montant du paris contenu dans notre requête
            montantParie += nouvelleRequete.getParis().getSomme();
            nouvelleRequete.getParis().setSomme(montantParie); //On modifie le montant du paris contenu dans notre requête
            map.put(nouvelleRequete.getAddress(), nouvelleRequete); //On modifie le paris présent dans notre HashMap
        }
    }
    
    private void avertirParieursGagnants(HashMap<String,Request> map){
        int montantTotal = 0;
        double prorata = 0.0;
        int gain = 0;
        //On calcul le montant total parié
        for(Request r : map.values()){
            montantTotal += r.getParis().getSomme();
        }
        //On parcours notre table de parieurs
        for(String currentKey : map.keySet()){
            //On calcul la somme gagnée
            prorata = (map.get(currentKey).getParis().getSomme()/ montantTotal) * 100;
            gain = (int) (0.75 * montantTotal * prorata);
            //On envoie le message au client
            Request r = new Request(); //On crée un nouveau message, contenant les gains
            r.setMethode(Methodes.annoncerGains);
            r.setGain(gain);
            try {
                transmettre(r, map.get(currentKey).getAddress(), map.get(currentKey).getPort());
            } catch (IOException ex) {
                Logger.getLogger(Match.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void avertirParisOk(Request nouvelleRequete,String address, int port){
        try {
            //On envois le message au client
            Request r = new Request();
            r.setMethode(Methodes.confirmerParis); //Permet de confirmer une requête
            r.setNumeroRequete(nouvelleRequete.getNumeroRequete()); //Même numéro de requête

            transmettre(r, address , port);
        } catch (IOException ex) {
            Logger.getLogger(Match.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void transmettre(Request messageToSend, String address, int port) throws UnknownHostException, IOException{
        DatagramSocket aSocket = new DatagramSocket();        

        try {
            byte[] buf = new byte[1000];
            buf = Request.marshall(messageToSend);

            DatagramPacket out = new DatagramPacket(buf, buf.length, InetAddress.getByName(address), port);

            aSocket.send(out);
            System.out.println("Response send to "+ address + "for method : " + messageToSend.getMethode());
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        finally {
            if (aSocket != null)
                aSocket.close();
        }
    }
}
