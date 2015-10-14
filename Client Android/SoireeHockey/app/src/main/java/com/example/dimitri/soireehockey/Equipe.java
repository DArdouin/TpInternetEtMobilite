package com.example.dimitri.soireehockey;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dimitri on 13/10/2015.
 */
public class Equipe implements Parcelable{
    private String nom;
    private int score;

    public Equipe(String unNom)
    {
        this.nom = unNom;
        this.score = 10;
    }

    protected Equipe(Parcel in) {
        nom = in.readString();
        score = in.readInt();
    }

    public static final Creator<Equipe> CREATOR = new Creator<Equipe>() {
        @Override
        public Equipe createFromParcel(Parcel in) {
            return new Equipe(in);
        }

        @Override
        public Equipe[] newArray(int size) {
            return new Equipe[size];
        }
    };

    public String getNom(){
        return nom;
    }
    
    public int getScore(){
        return score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeInt(score);
    }
}
