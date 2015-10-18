/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurhockey;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import protocole.Request ;
import protocole.RequestHandler;
import Match.* ;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Damien
 */
public class ServeurHockey {


    private ListeDesMatchs matchList;
    private FilExecutionParis filDeParis;
    private FilExecutionMatch filDeMatchs;
    
    public ServeurHockey(String serverIp, int matchPort, int parisPort) throws ParseException{
        matchList = new ListeDesMatchs();
        
        //On initialise nos deux fils d'éxecution
        filDeMatchs  = new FilExecutionMatch(serverIp, matchPort, matchList);
        filDeParis = new FilExecutionParis(serverIp, parisPort, matchList);
  
        //Puis on les lance
        new Thread(filDeMatchs).start();
        new Thread(filDeParis).start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        
        String serverIP = new String ("");
        try {
            serverIP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServeurHockey.class.getName()).log(Level.SEVERE, null, ex);
        }
        int matchPort = 11111 ;
        int parisPort = 22222;
        // TODO code application logic here
        /*String serverIP = args[0];
        int matchPort = Integer.valueOf(args[1]);*/
        ServeurHockey monServeur = new ServeurHockey(serverIP,matchPort,parisPort);       
    }
    
}
