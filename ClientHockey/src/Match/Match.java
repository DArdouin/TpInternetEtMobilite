/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Match;

import java.io.Serializable;

/**
 *
 * @author Damien
 */
public class Match implements Serializable{
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

    /**
     * Permet de créer un Match, en passant les équipes en paramètres
     * @param e1 La première équipe
     * @param e2 La seconde équipe
     */
    public Match(Equipe e1, Equipe e2){
        equipeDomicile = e1;
        equipeExterieur = e2;
        temps = 0 ;
        nbButsDomicile = 0 ;
        nbButsExterieur = 0 ;
        nbPenaliteDomicile = 0 ;
        nbPenaliteExterieur = 0 ;
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

    @Override
    public String toString() {
        return "Match :" + "Equipe domicile : " + equipeDomicile.getNom() + "- Equipe exterieur : " + equipeExterieur.getNom() ;
    }
    
    
}
