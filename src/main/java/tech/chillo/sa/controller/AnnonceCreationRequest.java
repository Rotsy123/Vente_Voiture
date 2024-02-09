package tech.chillo.sa.controller;

import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.entites.DetailsVoiture;
import tech.chillo.sa.entites.Photos;
import tech.chillo.sa.entites.Voiture;

public class AnnonceCreationRequest {

    private Voiture voiture;
    private DetailsVoiture detailsVoiture;
    private Annonce annonce;
    private String[] photos;

    public AnnonceCreationRequest() {
    }

    public String[] getPhotos(){
        return this.photos;
    }
    public void setPhotos(String[] photos){
        this.photos = photos;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public DetailsVoiture getDetailsVoiture() {
        return detailsVoiture;
    }

    public void setDetailsVoiture(DetailsVoiture detailsVoiture) {
        this.detailsVoiture = detailsVoiture;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public AnnonceCreationRequest(Voiture voiture, DetailsVoiture detailsVoiture, Annonce annonce) {
        this.voiture = voiture;
        this.detailsVoiture = detailsVoiture;
        this.annonce = annonce;
    }
    public AnnonceCreationRequest(String[] photos, Voiture voiture, DetailsVoiture detailsVoiture, Annonce annonce) {
        this.voiture = voiture;
        this.detailsVoiture = detailsVoiture;
        this.annonce = annonce;
        this.photos = photos;
    }
// getters et settersti

}

