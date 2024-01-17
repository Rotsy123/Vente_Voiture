package tech.chillo.sa.entites;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "messagerie")
public class Messagerie {

    @Id
    private String id;
    private Personne expediteur;
    private Personne destinataire;
    private String message;
    private Date dateenvoie;
    private int etat = 0;

    public Messagerie(String id,Personne expediteur,Personne destinataire, String message, Date dateenvoie){
        setId(id);
        setExpediteur(expediteur);
        setDestinataire(destinataire);
        setMessage(message);
        setDateenvoie(dateenvoie);
    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Personne getExpediteur() {
        return this.expediteur;
    }

    public void setExpediteur(Personne expediteur) {
        this.expediteur = expediteur;
    }
    
    public Personne getDestinataire() {
        return this.destinataire;
    }

    public void setDestinataire(Personne destinataire) {
        this.destinataire = destinataire;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Date getDateenvoie() {
        return this.dateenvoie;
    }

    public void setDateenvoie(Date dateenvoie) {
        this.dateenvoie = dateenvoie;
    }

    public static Date convert(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
        

    public void setDateenvoie() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = convert(localDateTime);

        this.setDateenvoie(date);
    }

    
    public int getEtat() { return this.etat; }
    public void setEtat(int etat) { this.etat = etat;}

    public Messagerie() {
        // Initialisation de l'ID, par exemple :
        this.setId(UUID.randomUUID().toString());
        this.setDateenvoie();
    }
}
