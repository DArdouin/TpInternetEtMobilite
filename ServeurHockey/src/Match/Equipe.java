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
     * Récupère le nom de l'équipe
     * @return Le score
     */
    public String getNom() {
        return nom;
    }
}
