/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurhockey;

import Match.ListeDesMatchs;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocole.Request;
import protocole.RequestHandler;

/**
 *
 * @author Damien
 */
public class FilExecutionMatch implements Runnable{
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
    private DatagramSocket myMatchInfoSocket;
    private ListeDesMatchs matchList;

    public FilExecutionMatch(String serverIP, int serverPort, ListeDesMatchs matchList) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.matchList = matchList;
    }
    
    @Override
    public void run() {
        myMatchInfoSocket = null;
        ExecutorService execute = Executors.newCachedThreadPool(); //On crée notre pool de thread (taille variable, 60s to death)
                
        try {
                myMatchInfoSocket = new DatagramSocket(serverPort); // port pour les informations
                System.out.println("Le fil d'actualisation des matchs est lancé, sur l'IP " + serverIP + " sur le port " + serverPort);

                byte[] buffer = new byte[1000];

                while (true) {
                        DatagramPacket dgp = new DatagramPacket(buffer,buffer.length);
                        System.out.println("Waiting for request...");
                        myMatchInfoSocket.receive(dgp); // réception bloquante

                        System.out.println("Request receive !!");
                        Request Requete = Request.unmarshall(dgp.getData());
                        //Thread-per-request. C'est à dire que pour chaque nouvelle requête, on lance un thread qui va aller chercher l'information
                }
        } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
        }

        finally {
                if (myMatchInfoSocket != null)myMatchInfoSocket.close();
                execute.shutdown();
        }
    }
}
