package tech.chillo.sa.entites;

import jakarta.persistence.*;
import java.sql.Timestamp;

public class Messagerie {
    private int id;
    private Object expediteur;
    private Object destinataire;
    private String message;
    private Timestamp dateenvoie;

    public Messagerie(){}
    public Messagerie(int id,Object expediteur,Object destinataire, String message, Timestamp dateenvoie){
        setId(id);
        setExpediteur(expediteur);
        setDestinataire(destinataire);
        setMessage(message);
        setDateenvoie(dateenvoie);
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getExpediteur() {
        return this.expediteur;
    }

    public void setExpediteur(Object expediteur) {
        this.expediteur = expediteur;
    }
    
    public Object getDestinataire() {
        return this.destinataire;
    }

    public void setDestinataire(Object destinataire) {
        this.destinataire = destinataire;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Timestamp getDateenvoie() {
        return this.dateenvoie;
    }

    public void setDateenvoie(Timestamp dateenvoie) {
        this.dateenvoie = dateenvoie;
    }
}
