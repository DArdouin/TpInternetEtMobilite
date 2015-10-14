/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocole;

/**
 *
 * @author Quentin
 */
public class Message {
    public enum methodes {
            updateMatchInfo, parier
    };
    
    private boolean betRequest ;
    private boolean matchInfoRequest ;
    
    private int numeroRequete;
    private methodes methode; 
    private Object[] argument; // positionGPS  positionGPS  noTaxi
    private String message;
    
    public boolean isBetRequest(){
        return this.betRequest;
    }
    
    public void setBetRequest(boolean request){
        this.betRequest = request ;
    }

    public boolean isMatchInfoRequest() {
        return matchInfoRequest;
    }

    public void setMatchInfoRequest(boolean matchInfoRequest) {
        this.matchInfoRequest = matchInfoRequest;
    }

    public int getNumeroRequete() {
        return numeroRequete;
    }

    public void setNumeroRequete(int numeroRequete) {
        this.numeroRequete = numeroRequete;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
