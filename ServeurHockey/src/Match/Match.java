/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Match;

import Paris.Paris;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

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
    private LinkedList<Paris> listeDeParis;
    private HashMap<String,Integer> parisEquipeExterieur;
    private HashMap<String,Integer> parisEquipeDomicile;   

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
        listeDeParis = new LinkedList<Paris>();
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

    public long getTemps() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date actualDate = new Date();
        Date scheduledDate = format.parse(dateDebut);
        
        long diff = (actualDate.getTime() - scheduledDate.getTime())/1000 ;
        
        System.out.println("Date actuelle : " + actualDate + "\nDate Match : " + scheduledDate);
        System.out.println("Diff : " + diff);
        System.out.println("Secondes : " + diff / 1000);
        
        if(diff < 0 ){
            return -1 ;
        }
        else if(diff >= (60*60)){
            return 0 ;
        }
        else {
            return diff ;
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
    public void ajouterParis(Paris p){
        listeDeParis.add(p);
    }

    /**
     * C'est ici que l'on va gérer les paris (calcul des sommes, ...)
     */
    @Override
    public void run() {
        int totalEquipeDomicile = 0;
        int totalEquipeExterieur = 0;
        boolean matchEnCours = true;
        //while négatif, on attend le début du match
        
        while(matchEnCours){ //Tant que notre match n'est pas terminé --> while getTemps()>0
            //On récupère le paris dans la liste 
            Paris nouveauParis = listeDeParis.getFirst();
            if(nouveauParis.getNomDeLequipe().equals(getEquipeDomicile().getNom())) //On cherche sur quelle équipe parier
                monCompute(parisEquipeDomicile, nouveauParis);//On ajoute le paris à la liste
            else if(nouveauParis.getNomDeLequipe().equals(getEquipeExterieur().getNom())) //On cherche sur quelle équipe parier
                monCompute(parisEquipeExterieur, nouveauParis);
            avertirParisOk(nouveauParis);
        }
        
        //Le match est terminé, on envois la somme à tout les gagnants
        if(nbButsDomicile.compareTo(nbButsExterieur) >= 0){ //Si l'équipe domicile gagne
            avertirParieursGagnants(parisEquipeDomicile);//On avertis toutes les personnes de leur victoire
        }else{
            avertirParieursGagnants(parisEquipeExterieur);//On avertis toutes les personnes de leur victoire
        }
    }
    
    /**
     * Permet d'ajouter un paris pour une équipe
     * @param map La table contenant les paris d'une équipe
     * @param nouveauParis Le paris que l'on veut record
     */
    private void monCompute(HashMap map, Paris nouveauParis){
        if(map.get(nouveauParis.getIpParieur()) == null)
            map.put(nouveauParis.getIpParieur(),nouveauParis.getParis()); //Si la clé n'existe pas, on l'ajoute
        else
        {
            int montantParie = (int) map.get(nouveauParis.getIpParieur()); //On récupère la somme actuelle
            montantParie += nouveauParis.getParis();
            map.put(nouveauParis.getIpParieur(), montantParie); //On modifie le paris présent dans notre HashMap
        }
    }
    
    private void avertirParieursGagnants(HashMap<String,Integer> map){
        int montantTotal = 0;
        double prorata = 0.0;
        int gain = 0;
        //On calcul le montant total parié
        for(int montant : map.values()){
            montantTotal += montant;
        }
        //On parcours notre table de parieurs
        for(String currentKey : map.keySet()){
            //On calcul la somme gagnée
            prorata = (map.get(currentKey) / montantTotal) * 100;
            gain = (int) (0.75 * montantTotal * prorata);
            //On envoie le message au client
            
        }
    }
    
    private void avertirParisOk(Paris nouveauParis){
        //On envois le message au client
    }
}
