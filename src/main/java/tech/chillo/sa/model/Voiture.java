package tech.chillo.sa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import tech.chillo.sa.entites.Marque;
import tech.chillo.sa.entites.Modele;
import java.sql.Date;
import java.util.List;

public class Voiture {

    private int id;
    private Marque marque;
    private Modele modele;
    private Date sortie;
    private DetailsVoiture detailsVoiture;

    public Voiture(){}

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

    public Voiture getVoiture(tech.chillo.sa.entites.Voiture voiture){
        Voiture v = new Voiture();
        v.setId(voiture.getId());
        v.setMarque(voiture.getMarque());
        v.setModele(voiture.getModele());
        v.setSortie(voiture.getSortie());
        try {
            v.setDetailsVoiture(new tech.chillo.sa.model.DetailsVoiture().getDetailsVoiture(voiture.getDetailsVoiture()));
            
        } catch (Exception e) {
        }
        return v;
    }

}
