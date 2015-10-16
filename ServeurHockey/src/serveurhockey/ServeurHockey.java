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
import java.text.ParseException;

/**
 *
 * @author Damien
 */
public class ServeurHockey {
    
    private DatagramSocket myBetSocket;
    private DatagramSocket myMatchInfoSocket;
    
    private ListeDesMatchs matchList  ;
    
    public ServeurHockey() throws ParseException{
        matchList = new ListeDesMatchs() ;
    }

    public void demarrer(String serverIP, int serverPort) {
        myBetSocket = null;
        try {
                myBetSocket = new DatagramSocket(11111); // port pour les paris
                System.out.println("Server started");
                //myMatchInfoSocket = new DatagramSocket(11112) ; // port pour les infos des matchs
                
                byte[] buffer = new byte[1000];

                while (true) {
                        DatagramPacket dgp = new DatagramPacket(buffer,buffer.length);
                        
                        System.out.println("Waiting for request...");
                        myBetSocket.receive(dgp); // rŽception bloquante
                        System.out.println("Request receive !!");
                        
                        Request Requete = Request.unmarshall(dgp.getData());
                        new Thread(new RequestHandler(Requete, serverIP, serverPort)).start();
                }
        } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
        }

        finally {
                if (myBetSocket != null)
                        myBetSocket.close();
                if(myMatchInfoSocket != null)
                        myMatchInfoSocket.close();
        }

    }

    public void arreter() {
        if (myBetSocket != null)
                myBetSocket.close();
        if(myMatchInfoSocket != null)
                myMatchInfoSocket.close();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        
        String serverIP = "192.168.0.60" ;
        int serverPort = 11111 ;
        // TODO code application logic here
        /*String serverIP = args[0];
        int serverPort = Integer.valueOf(args[1]);*/
        ServeurHockey monServeur = new ServeurHockey();
        monServeur.demarrer(serverIP,serverPort);

    }
    
}
