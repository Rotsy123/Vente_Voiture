package tech.chillo.sa.entites;

import jakarta.persistence.*;
import java.sql.Timestamp;

public class Annonce {
    private int idannonce;
    private Object idpersonne;
    private Timestamp datepublication;

    public Annonce(){}
    public Annonce(int idannonce,Object personne,Timestamp datepublication){
        setIdan(id);
        setIdbouquet(idbouquet);
        setIdpersonne(idpersonne);
        setIdvoiture(idvoiture);
        setDatepublication(datepublication);
    }
    public int getIdannonce() {
        return this.idannonce;
    }

    public void setIdannonce(int idannonce) {
        this.idannonce = idannonce;
    }
    
    public Object getPersonne() {
        return this.personne;
    }

    public void setPersonne(Object personne) {
        this.personne = personne;
    }
    
    public Timestamp getDatepublication() {
        return this.datepublication;
    }

    public void setDatepublication(Timestamp datepublication) {
        this.datepublication = datepublication;
    }
}
