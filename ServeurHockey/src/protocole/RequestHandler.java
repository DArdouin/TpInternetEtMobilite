/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocole;

import sun.misc.RequestProcessor;

/**
 *
 * @author Quentin
 */
public class RequestHandler implements Runnable{
    
    private String serverIP ;
    private int serverPort ;
    private Request request ;
    
    private RequestProcessor myProcessor ;
    
    public RequestHandler(Request request, String IP, int port){
        this.request = request ;
        this.serverIP = IP ;
        this.serverPort = port ;
    }
    
    @Override
    public void run() {
        
        System.out.println("Thread created ! ");
        System.out.println("équipe domicile : " + this.request.getMatch().getEquipeDomicile()+ " équipe exterieur : " + this.request.getMatch().getEquipeExterieur());
    }
    
}
