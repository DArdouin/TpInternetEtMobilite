/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Match;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Damien
 */
public class Match implements Serializable, Runnable {
    /**
     * 
     */
    private Equipe equipeDomicile;
    /**
     * 
     */
    private Equipe equipeExterieur;
    private Integer temps ; 
    private Integer nbPenaliteDomicile ;
    private Integer nbPenaliteExterieur ;
    private Integer nbButsDomicile ;
    private Integer nbButsExterieur ;
    private Date dateDebut  ;

    /**
     * Permet de créer un Match, en passant les équipes en paramètres
     * @param e1 La première équipe
     * @param e2 La seconde équipe
     * @param date
     */
    public Match(Equipe e1, Equipe e2, Date date){
        equipeDomicile = e1;
        equipeExterieur = e2;
        temps = 0 ;
        nbButsDomicile = 0 ;
        nbButsExterieur = 0 ;
        nbPenaliteDomicile = 0 ;
        nbPenaliteExterieur = 0 ;
        dateDebut = date ;
    }

    public Equipe getEquipeDomicile() {
        return equipeDomicile;
    }

    public Equipe getEquipeExterieur() {
        return equipeExterieur;
    }

    public Integer getTemps() {
        return temps;
    }

    public Integer getNbPenaliteDomicile() {
        return nbPenaliteDomicile;
    }

    public Integer getNbPenaliteExterieur() {
        return nbPenaliteExterieur;
    }

    public Integer getNbButsDomicile() {
        return nbButsDomicile;
    }

    public Integer getNbButsExterieur() {
        return nbButsExterieur;
    }

    public void setEquipeDomicile(Equipe equipeDomicile) {
        this.equipeDomicile = equipeDomicile;
    }

    public void setEquipeExterieur(Equipe equipeExterieur) {
        this.equipeExterieur = equipeExterieur;
    }

    public void setTemps(Integer temps) {
        this.temps = temps;
    }

    public void setNbPenaliteDomicile(Integer nbPenaliteDomicile) {
        this.nbPenaliteDomicile = nbPenaliteDomicile;
    }

    public void setNbPenaliteExterieur(Integer nbPenaliteExterieur) {
        this.nbPenaliteExterieur = nbPenaliteExterieur;
    }

    public void setNbButsDomicile(Integer nbButsDomicile) {
        this.nbButsDomicile = nbButsDomicile;
    }

    public void setNbButsExterieur(Integer nbButsExterieur) {
        this.nbButsExterieur = nbButsExterieur;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    @Override
    public String toString() {
        return " " + equipeDomicile.getNom() + " Versus " + equipeExterieur.getNom() ;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
