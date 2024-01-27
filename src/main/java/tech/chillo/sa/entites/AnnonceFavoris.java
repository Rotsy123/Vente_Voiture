package tech.chillo.sa.entites;


import jakarta.persistence.*;

@Entity
@Table(name = "favoris")
public class AnnonceFavoris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "idpersonne")
    private Personne personne;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "idannnonce")
    private Annonce annonce;

    public AnnonceFavoris(){}
    public AnnonceFavoris(int id, Personne personne, Annonce annonce) {
        this.setId(id);
        this.setPersonne(personne);
        this.setAnnonce(annonce);
    }
    public AnnonceFavoris(Personne personne, Annonce annonce) {
        this.setPersonne(personne);
        this.setAnnonce(annonce);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    @Entity
    @Table(name = "historique")
    public static class Historique {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        // Other Historique fields

        @ManyToOne
        @JoinColumn(name = "annonce")
        private Annonce annonce;

        @ManyToOne
        @JoinColumn(name = "bouquet")
        private Bouquet bouquet;

    }
}
