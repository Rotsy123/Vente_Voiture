package tech.chillo.sa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import tech.chillo.sa.entites.Carburant;
import tech.chillo.sa.entites.Transmission;

public class DetailsVoiture {

    private int id;
    Voiture voiture;
    double kilometrage;
    int nbplaces;
    int nbportes;
    double etat_interieur;
    double etat_exterieur;
    double consommation;
    Carburant carburant;
    int idcarburant;
    Transmission transmission;
    int idtransmission;
    double prix;

    public DetailsVoiture() {
    }

    public DetailsVoiture(Voiture voiture, double kilometrage, int nbplaces, int nbportes, double etat_interieur,
            double etat_exterieur, double consommation, Carburant carburant, Transmission transmission, double prix)
            throws Exception {
        this.voiture = voiture;
        this.kilometrage = kilometrage;
        this.nbplaces = nbplaces;
        this.nbportes = nbportes;
        this.etat_interieur = etat_interieur;
        this.etat_exterieur = etat_exterieur;
        this.consommation = consommation;
        this.carburant = carburant;
        this.transmission = transmission;
        this.setPrix(prix);
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) throws Exception {
        if (prix >= 0) {
            this.prix = prix;
        } else {
            throw new Exception("PRIX NEGATIF");
        }
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getNbplaces() {
        return nbplaces;
    }

    public void setNbplaces(int nbplaces) {
        this.nbplaces = nbplaces;
    }

    public int getNbportes() {
        return nbportes;
    }

    public void setNbportes(int nbportes) {
        this.nbportes = nbportes;
    }

    public double getEtat_interieur() {
        return etat_interieur;
    }

    public void setEtat_interieur(double etat_interieur) {
        this.etat_interieur = etat_interieur;
    }

    public double getEtat_exterieur() {
        return etat_exterieur;
    }

    public void setEtat_exterieur(double etat_exterieur) {
        this.etat_exterieur = etat_exterieur;
    }

    public double getConsommation() {
        return consommation;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    public Carburant getCarburant() {
        return carburant;
    }

    public void setCarburant(Carburant carburant) {
        this.carburant = carburant;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdtransmission() {
        return idtransmission;
    }

    public void setIdtransmission(int id) {
        this.idtransmission = id;
    }

    public int getIdcarburant() {
        return idcarburant;
    }

    public void setIdcarburant(int id) {
        this.idcarburant = id;
    }

    public DetailsVoiture getDetailsVoiture(tech.chillo.sa.entites.DetailsVoiture voiture) {
        DetailsVoiture v = new DetailsVoiture();
        v.setId(voiture.getId());
        v.setKilometrage(voiture.getKilometrage());
        v.setNbplaces(voiture.getNbplaces());
        v.setNbportes(voiture.getNbportes());
        v.setEtat_exterieur(voiture.getEtat_exterieur());
        v.setEtat_interieur(voiture.getEtat_interieur());
        v.setConsommation(voiture.getConsommation());
        v.setCarburant(voiture.getCarburant());
        v.setIdcarburant(voiture.getCarburant().getId());
        v.setTransmission(voiture.getTransmission());
        v.setIdtransmission(voiture.getTransmission().getId());
        try {
            v.setPrix(voiture.getPrix());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return v;
    }
}
