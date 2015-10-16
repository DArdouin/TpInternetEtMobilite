/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocole;

import protocole.Methodes ;


/**
 *
 * @author Quentin
 */
public class RequestHandler implements Runnable{
    
    private String serverIP ;
    private int serverPort ;
    private Request request ;
    
    public RequestHandler(Request request, String IP, int port){
        this.request = request ;
        this.serverIP = IP ;
        this.serverPort = port ;
    }
    
    public void transmettre(Message messageToSend){
        
    }
    
    @Override
    public void run() {
        
<<<<<<< HEAD
        switch(request.getMethode()){
            case demandeListMatch : 
                Request request = new Request(); 
                request.setMatchList(null);
                break ;
            case parier : 
                break ;
            case updateMatchInfo : 
                break ;
        }
=======
        System.out.println("Request Handler crée dans notre pool de threads");
        System.out.println("équipe domicile : " + this.request.getMatch().getEquipeDomicile().getNom()+ " équipe exterieur : " + this.request.getMatch().getEquipeExterieur().getNom());
>>>>>>> Damien
    }
    
}
