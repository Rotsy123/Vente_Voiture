package tech.chillo.sa.entites;

import jakarta.persistence.*;

@Entity
@Table(name="modele")
public class Modele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    public Modele(){}
    public Modele(int id, String nom){
        setId(id);
        setNom(nom);
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
