package tech.chillo.sa.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "historique")
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "annonce")
    private Annonce annonce;
    @ManyToOne
    @JoinColumn(name = "bouquet")
    private Bouquet bouquet;
    private LocalDateTime datedebut;
    private LocalDateTime datefin;

    public Historique(int id, Annonce annonce, Bouquet bouquet, LocalDateTime datedebut, LocalDateTime datefin) {
        this.id = id;
        this.annonce = annonce;
        this.bouquet = bouquet;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public Historique( ) {
    }
    public Historique(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    public LocalDateTime getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(LocalDateTime datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDateTime getDatefin() {
        return datefin;
    }

    public void setDatefin(LocalDateTime datefin) {
        this.datefin = datefin;
    }
}
