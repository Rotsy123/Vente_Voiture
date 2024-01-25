package tech.chillo.sa.entites;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Annonce")
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "idvoiture")
    private Voiture voiture;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "idpersonne")
    private Personne personne;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "idbouquet")
    private Bouquet bouquet;
    private LocalDateTime datepublication;
    private int etat = 0;
    private LocalDateTime datevalidation = null;

    public Annonce(){}
    public Annonce(int id, Voiture voiture, Personne personne,Bouquet bouquet, LocalDateTime datepublication,int etat, LocalDateTime datevalidation){
        setId(id);
        setVoiture(voiture);
        setPersonne(personne);
        setBouquet(bouquet);
        setDateplublication(datepublication);
        setDatevalidation(datevalidation);
    }
    public Annonce(Voiture voiture, Personne personne,Bouquet bouquet, LocalDateTime datepublication,int etat, LocalDateTime datevalidation){
        setVoiture(voiture);
        setPersonne(personne);
        setBouquet(bouquet);
        setDateplublication(datepublication);
        setEtat(etat);
        setDatevalidation(datevalidation);
    }

    public LocalDateTime getDatevalidation(){return datevalidation;}
    public void setDatevalidation(LocalDateTime datevalidation){this.datevalidation = datevalidation;}
    public LocalDateTime getDatepublication() {
        return datepublication;
    }

    public void setDatepublication(LocalDateTime datepublication) {
        this.datepublication = datepublication;
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne idpersonne) {this.personne = idpersonne;}

    public LocalDateTime getDateplublication() {
        return datepublication;
    }

    public void setDateplublication(LocalDateTime dateplublication) {
        this.datepublication = dateplublication;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
