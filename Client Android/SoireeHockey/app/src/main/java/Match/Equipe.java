package Match;

import java.io.Serializable;

/**
 * Created by Dimitri on 16/10/2015.
 */
public class Equipe implements Serializable{
    /**
     * Nom de l'équipe
     */
    private String nom;

    /**
     * Permet de créer une équipe, en mettant initialement le score à 0
     * @param nom Le nom de l'équipe que l'on vient de créer
     */
    public Equipe(String nom){
        this.nom = nom ;
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
