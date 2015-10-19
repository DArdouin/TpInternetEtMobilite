package Paris;

import java.io.Serializable;

/**
 * Created by Dimitri on 18/10/2015.
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
