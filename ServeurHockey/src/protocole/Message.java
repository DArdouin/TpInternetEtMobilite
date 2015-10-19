/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocole;

import Match.*;
import Paris.Paris;
import java.io.Serializable;

/**
 *
 * @author Quentin
 */
public class Message implements Serializable{
    
    private boolean request ;
    private int numeroRequete;
    private Methodes methode;
    private Paris paris;
    private String message;
    private Match match;
    private ListeDesMatchs matchList;
    private int gain;

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
    public Methodes getMethode() {
        return methode;
    }

    public void setMethode(Methodes methode) {
        this.methode = methode;
    }

    public ListeDesMatchs getMatchList() {
        return matchList;
    }

    public void setMatchList(ListeDesMatchs matchList) {
        this.matchList = matchList;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }
    
    public int getGain(){
        return gain;
    }
}
