/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurhockey;

import Match.ListeDesMatchs;
import Match.Match;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import protocole.Methodes;
import protocole.Request;
import protocole.RequestHandler;

/**
 *
 * @author Damien
 */
public class FilExecutionParis implements Runnable{
    /**
     * Ip du serveur 
     */
    private String  serverIP;
    /**
     * Port correspondant à notre service d'information des matchs
     */
    private int serverPort;
    /**
     * Permet de récupérer les requêtes UDP 
     */
    private DatagramSocket myBetSocket; 
    private ListeDesMatchs matchList;

    public FilExecutionParis(String serverIP, int serverPort, ListeDesMatchs matchList) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.matchList = matchList;
    }
    
    @Override
    public void run() {
        myBetSocket = null;
        
        try {
                myBetSocket = new DatagramSocket(serverPort); // port pour les paris
                System.out.println("Le fil de gestion des paris est lancé, sur à l'IP " + serverIP + " sur le port " + serverPort);
                byte[] buffer = new byte[10000];
                while (true) {
                    DatagramPacket dgp = new DatagramPacket(buffer,buffer.length);
                    System.out.println("Waiting for bet request...");
                    myBetSocket.receive(dgp); // réception bloquante
                    Request requete = Request.unmarshall(dgp.getData());
                    System.out.println("Request receive from "+ requete.getAddress() +" for method : "+ requete.getMethode().toString());

                    if(requete.getMethode() == Methodes.parier){
                        //Thread-per-Object. On va envoyer la requête vers l'objet qui lui correspond. Pour cela, on regarde l'équipe sur laquelle le joueur paris
                        for(Match m : matchList.getMatchs()){ //On parcours la liste de match 
                            if(requete.getMatch().getEquipeDomicile().getNom().equals(m.getEquipeDomicile().getNom())){ //Si on a la même équipe domicile
                                if(requete.getMatch().getEquipeExterieur().getNom().equals(m.getEquipeExterieur().getNom())){ //Si on a la même équipe extérieur 
                                    //On ajoute alors le Paris sur le match
                                    System.out.println("Match found !");
                                    m.ajouterRequete(requete);
                                    break ;
                                }
                            }
                        }
                    }
                }
        } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
        }

        finally {
                if (myBetSocket != null)myBetSocket.close();
        }
    }
}
