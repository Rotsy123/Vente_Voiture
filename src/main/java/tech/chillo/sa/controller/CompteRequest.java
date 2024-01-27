package tech.chillo.sa.controller;

import tech.chillo.sa.entites.Compte;
import tech.chillo.sa.entites.Personne;

public class CompteRequest {
    private Personne personne;
    private Compte compte;

    public CompteRequest() {
        // Constructeur par défaut
    }

    public CompteRequest(Personne personne, Compte compte) {
        this.personne = personne;
        this.compte = compte;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
