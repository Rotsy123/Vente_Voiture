package tech.chillo.sa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import tech.chillo.sa.entites.Personne;

public class StatistiqueUtilisateur {
    private Personne personne;
    private int nombre_annonce;
    private int nombre_annonce_vendu;

    public StatistiqueUtilisateur(Personne personne, int nombre_annonce, int nombre_annonce_vendu) {
        this.setPersonne(personne);
        this.setNombre_annonce(nombre_annonce);
        this.setNombre_annonce_vendu(nombre_annonce_vendu);
    }

    public void setPersonne(Personne personne){
        this.personne = personne;
    }

    public void setNombre_annonce(int nombre_annonce){
        this.nombre_annonce = nombre_annonce;
    }

    public void setNombre_annonce_vendu(int nombre_annonce_vendu){
        this.nombre_annonce_vendu = nombre_annonce_vendu;
    }

    public Personne getPersonne(){
        return this.personne;
    }

    public int getNombre_annonce(){
        return this.nombre_annonce;
    }

    public int getNombre_annonce_vendu(){
        return this.nombre_annonce_vendu;
    }

    public StatistiqueUtilisateur() {}

}
