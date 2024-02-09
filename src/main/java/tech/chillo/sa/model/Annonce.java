package tech.chillo.sa.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

import tech.chillo.sa.entites.Personne;

public class Annonce {
    
    private String id;
    private Personne personne;
    private int idpersonne;
    private Voiture voiture;
    private int idvoiture;
    private Date datepublication = new Date(Calendar.getInstance().getTime().getTime());
    private static int etat = 0 ;
    
    // Autres champs en fonction de votre modèle

    // Getters et setters
    public Object getPersonne(){ return this.personne; }
    public void setPersonne(Personne personne){ this.personne=personne; }
    
    public Object getVoiture(){ return this.voiture; }
    public void setVoiture(Voiture voiture){ this.voiture=voiture; }
    
    public Date getDatepublication(){ return this.datepublication; }
    public void setDatepublication(Date datepublication){ this.datepublication=datepublication; }
    

    // public static Date convert(LocalDateTime localDateTime) {
    //     return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    // }
        

    // public void setDatepublication() {
    //     LocalDateTime localDateTime = LocalDateTime.now();
    //     Date date = convert(localDateTime);

    //     this.setDatepublication(date);
    // }
    
    // public void setDatepublication(LocalDateTime localDateTime) {
    //     Date date = convert(localDateTime);

    //     this.setDatepublication(date);
    // }

    public int getEtat() { return this.etat; }
    public void setEtat(int etat) { this.etat = etat;}

    public int getIdpersonne() { return this.idpersonne; }
    public void setIdpersonne(int idpersonne) { this.idpersonne = idpersonne;}

    public int getIdpersonne() { return this.idpersonne; }
    public void setIdpersonne(int idpersonne) { this.idpersonne = idpersonne;}

    public String getId() {return this.id; }
    public void setId(String id){ this.id = id; }

    public Annonce() {
        // Initialisation de l'ID, par exemple :
        this.setId(UUID.randomUUID().toString());
    }

}
