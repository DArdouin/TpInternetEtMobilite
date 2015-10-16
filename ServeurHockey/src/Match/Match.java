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
    Equipe equipe1;
    /**
     * 
     */
    Equipe equipe2;

    /**
     * Permet de créer un Match, en passant les équipes en paramètres
     * @param e1 La première équipe
     * @param e2 La seconde équipe
     */
    public Match(Equipe e1, Equipe e2){
        equipe1 = e1;
        equipe2 = e2;
    }
}
