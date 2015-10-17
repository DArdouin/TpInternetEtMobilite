/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocole;

import Match.ListeDesMatchs;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocole.Methodes ;


/**
 *
 * @author Quentin
 */
public class RequestHandler implements Runnable{
    
    private String serverIP ;
    private int serverPort ;
    private Request request ;
    private ListeDesMatchs matchList ;
    
    public RequestHandler(Request request, String IP, int port, ListeDesMatchs liste){
        this.request = request ;
        this.serverIP = IP ;
        this.serverPort = port ;
        this.matchList = liste ;
    }
    
    public void transmettre(Request messageToSend, String address, int port) throws UnknownHostException, IOException{
        DatagramSocket aSocket = null;
        
        try {
            byte[] buf = new byte[1000];
            buf = Request.marshall(request);

            DatagramPacket out = new DatagramPacket(buf, buf.length, InetAddress.getByName(address), port);

            aSocket.send(out);
            System.out.println("Requête envoyée");
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
    
    @Override
    public void run() {
        
        try {
            switch(request.getMethode()){
                case demandeListMatch : 
                    Request r = new Request(); 
                    r.setMatchList(this.matchList);
                    r.setNumeroRequete(this.request.getNumeroRequete());
                    transmettre(r, this.request.getAddress(),this.request.getPort());
                    break ;
                case updateMatchInfo : 
                    Request r2 = new Request() ;
                    r2.setMatch(this.request.getMatch());
                    r2.setNumeroRequete(this.request.getNumeroRequete());
                    transmettre(r2, this.request.getAddress(),this.request.getPort());
                    break ;
            }
        }            
        catch (UnknownHostException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
