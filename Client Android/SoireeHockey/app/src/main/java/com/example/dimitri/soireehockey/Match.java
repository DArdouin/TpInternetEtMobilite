package com.example.dimitri.soireehockey;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dimitri on 13/10/2015.
 */
public class Match implements Parcelable{
    private Equipe equipeLocal;
    private Equipe equipeVisiteur;


    public Match(Equipe local,Equipe visiteur)
    {
        equipeLocal = local;
        equipeVisiteur = visiteur;
    }

    protected Match(Parcel in) {
        equipeLocal = in.readParcelable(Equipe.class.getClassLoader());
        equipeVisiteur = in.readParcelable(Equipe.class.getClassLoader());
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    public Equipe getEquipeLocal() {
        return equipeLocal;
    }

    public Equipe getEquipeVisiteur() {
        return equipeVisiteur;
    }

    @Override
    public String toString() {
        return "Match :"+ equipeLocal +
                ", vs" + equipeVisiteur;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(equipeLocal, flags);
        dest.writeParcelable(equipeVisiteur, flags);
    }
}
