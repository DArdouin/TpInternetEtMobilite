/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paris;

import java.io.Serializable;

/**
 *
 * @author Damien
 */
public class Paris implements Serializable{
    private String nomDeLequipe;
    private int somme;

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }

    public String getNomDeLequipe() {
        return nomDeLequipe;
    }

    public void setNomDeLequipe(String nomDeLequipe) {
        this.nomDeLequipe = nomDeLequipe;
    }
}
