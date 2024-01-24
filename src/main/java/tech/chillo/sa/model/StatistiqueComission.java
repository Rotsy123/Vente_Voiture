package tech.chillo.sa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import tech.chillo.sa.entites.Carburant;
import tech.chillo.sa.entites.Transmission;

public class StatistiqueComission {
    private int annee;
    private int mois;
    private double prix;

    public StatistiqueComission(int annee, int mois, double prix) {
        this.setPrix(prix);
        this.setAnnee(annee);
        this.setMois(mois);
    }

    public StatistiqueComission() {}


    public void setPrix(double prix){
        this.prix = prix;
    }

    public void setAnnee(int annee){
        this.annee = annee;
    }

    public void setMois(int mois){
        this.mois = mois;
    }

    public double getPrix(){
        return this.prix;
    }

    public int getAnnee(){
        return this.annee;
    }

    public int getMois(){
        return this.mois;
    }



}
