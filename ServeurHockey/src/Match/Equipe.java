/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Match;

/**
 * Cette classe permet de stocker toutes les informations relatives à un équipe de Hockey
 * @author Damien
 */
public class Equipe {
    /**
     * Nom de l'équipe
     */
    private String nom;    
    /**
     * Score de l'équipe
     */
    private int score;

    /**
     * Permet de créer une équipe avec un score initial différent de 0
     * @param nom Le nom de l'équipe que l'on vient de créer
     * @param score Le score initial de l'équipe, si différent de 0
     */
    public Equipe(String nom, int score) {
        this.nom = nom;
        this.score = score;
    }
    
    /**
     * Permet de créer une équipe, en mettant initialement le score à 0
     * @param nom Le nom de l'équipe que l'on vient de créer
     */
    public Equipe(String nom){
        
    }

    /**
     * Modifier le nom de l'équipe (à faire via le constructeur de préférence)
     * @param nom 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Modifie le score de l'équipe
     * @param score le nombre de point à ajouter à l'équipe (mettre un - pour enlever des points)
     */
    public void ajouterScore(int score) {
        this.score = this.score + score;
    }
    
    /**
     * Permet de remetre le score de l'équipe à 0
     */
    public void resetScore(){
        this.score = 0;
    }

    /**
     * Récupère le nom de l'équipe
     * @return Le score
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère le score actuel de l'équipe
     * @return Le score
     */
    public int getScore() {
        return score;
    }
}
