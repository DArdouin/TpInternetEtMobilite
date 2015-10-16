/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocole;

import Match.*;
import java.io.Serializable;

/**
 *
 * @author Quentin
 */
public class Message implements Serializable{
    
    private boolean request ;
    
    private int numeroRequete;
    private Methodes methode; 
    private Object[] argument; // positionGPS  positionGPS  noTaxi
    private String message;
    private Match match ;
    private ListeDesMatchs matchList ;
    
    public boolean isRequest(){
        return this.request;
    }
    
    public void setRequest(boolean request){
        this.request = request ;
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

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match leMatch) {
        this.match = leMatch;
    }

    public Methodes getMethode() {
        return methode;
    }

    public void setMethode(Methodes methode) {
        this.methode = methode;
    }

    public Object[] getArgument() {
        return argument;
    }

    public ListeDesMatchs getMatchList() {
        return matchList;
    }

    public void setArgument(Object[] argument) {
        this.argument = argument;
    }

    public void setMatchList(ListeDesMatchs matchList) {
        this.matchList = matchList;
    }
    
    
    
}
