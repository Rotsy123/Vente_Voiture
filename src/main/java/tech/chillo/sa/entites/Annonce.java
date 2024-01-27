package tech.chillo.sa.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    private Timestamp  datepublication;
    private int etat;
    private Timestamp  datevalidation;
    public Annonce(int id, Voiture voiture, Personne personne, Bouquet bouquet, Timestamp  datepublication, int etat, Timestamp  datevalidation) {
        this.id = id;
        this.voiture = voiture;
        this.personne = personne;
        this.bouquet = bouquet;
        this.datepublication = datepublication;
        this.etat = etat;
        this.datevalidation = datevalidation;
    }


    public Timestamp  getDatevalidation() {
        return datevalidation;
    }

    public void setDatevalidation(Timestamp  datevalidation) {
        this.datevalidation = datevalidation;
    }

    public Annonce(){}
    public Annonce(int id, Voiture voiture, Personne personne,Bouquet bouquet, Timestamp  datepublication, Timestamp  datevalidation){
        setId(id);
        setVoiture(voiture);
        setPersonne(personne);
        setBouquet(bouquet);
        setDateplublication(datepublication);
        setDatevalidation(datevalidation);

    }
    public Annonce(Voiture voiture, Personne personne,Bouquet bouquet, Timestamp  datepublication,Timestamp  datevalidation){
        setVoiture(voiture);
        setPersonne(personne);
        setBouquet(bouquet);
        setDateplublication(datepublication);
        setDatevalidation(datevalidation);
    }

    public Timestamp  getDatepublication() {
        return datepublication;
    }

    public void setDatepublication(Timestamp  datepublication) {
        this.datepublication = datepublication;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
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

    public Timestamp getDateplublication() {
        return datepublication;
    }

    public void setDateplublication(Timestamp  dateplublication) {
        this.datepublication = dateplublication;
    }
}
