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
    private int paris;
    private String ipParieur;

    public String getIpParieur() {
        return ipParieur;
    }

    public void setIpParieur(String ipParieur) {
        this.ipParieur = ipParieur;
    }

    public int getParis() {
        return paris;
    }

    public void setParis(int paris) {
        this.paris = paris;
    }

    public String getNomDeLequipe() {
        return nomDeLequipe;
    }

    public void setNomDeLequipe(String nomDeLequipe) {
        this.nomDeLequipe = nomDeLequipe;
    }
}
