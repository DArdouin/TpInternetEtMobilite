/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurhockey;

import java.net.DatagramSocket;

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

    public FilExecutionParis(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
    
    @Override
    public void run() {
        System.out.println("Le fil de gestion des paris est lancé, sur à l'IP " + serverIP + " sur le port " + serverPort);
    }
    
}
