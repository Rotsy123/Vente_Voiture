package tech.chillo.sa.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "voiture")
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "idmarque")
    private Marque marque;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "idmodele")
    private Modele modele;
    private Date sortie;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "voiture", cascade = CascadeType.MERGE)
    private DetailsVoiture detailsVoiture;
    @Transient
    private String[] photos;
    // @OneToMany(fetch = FetchType.EAGER,mappedBy = "voiture", cascade =
    // CascadeType.MERGE)
    // private List<Photos> photos;
    public Voiture() {
    }

    public Voiture(int id, Marque marque, Modele modele, Date sortie, DetailsVoiture ds) {
        this.setId(id);
        this.setMarque(marque);
        this.setModele(modele);
        this.setSortie(sortie);
        this.setDetailsVoiture(ds);
    }

    public Voiture(int id, Marque marque, Modele modele, Date sortie) {
        this.setId(id);
        this.setMarque(marque);
        this.setModele(modele);
        this.setSortie(sortie);
    }

    public DetailsVoiture getDetailsVoiture() {
        return detailsVoiture;
    }

    public void setDetailsVoiture(DetailsVoiture detailsVoiture) {
        this.detailsVoiture = detailsVoiture;
        if (detailsVoiture != null) {
            detailsVoiture.setVoiture(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public Date getSortie() {
        return sortie;
    }

    public void setSortie(Date sortie) {
        this.sortie = sortie;
    }
    public void setPhotos(String[] photos){this.photos = photos;}
    public String[]getPhotos(){return this.photos;}
}
