/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocole;

import Match.Match;
import Paris.Paris;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Quentin
 */
public class Message implements Serializable{
    public enum methodes {
            updateMatchInfo, parier, demandeListMatch, confirmerParis, indiquerParisGagnant
    };
    
    private boolean request ;
    private int numeroRequete;
    private methodes methode;
    private Paris paris;
    private String message;
    private Match match;

    public Message() {
    }
    
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

    public void setParis(Paris paris) {
        this.paris = paris;
    }

    public Paris getParis() {
        return paris;
    }    
}
