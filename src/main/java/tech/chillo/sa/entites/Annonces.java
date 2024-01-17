package tech.chillo.sa.entites;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Document(collection = "annonces")
public class Annonces {

    @Id
    private String id;
    private Object personne;
    private Object voiture;
    private Date datepublication;
    private static int etat = 0 ;
    
    // Autres champs en fonction de votre modèle

    // Getters et setters
    public Object getPersonne(){ return this.personne; }
    public void setPersonne(Object personne){ this.personne=personne; }
    
    public Object getVoiture(){ return this.voiture; }
    public void setVoiture(Object voiture){ this.voiture=voiture; }
    
    public Date getDatepublication(){ return this.datepublication; }
    public void setDatepublication(Date datepublication){ this.datepublication=datepublication; }
    

    public static Date convert(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
        

    public void setDatepublication() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = convert(localDateTime);

        this.setDatepublication(date);
    }
    
    public void setDatepublication(LocalDateTime localDateTime) {
        Date date = convert(localDateTime);

        this.setDatepublication(date);
    }

    public int getEtat() { return this.etat; }
    public void setEtat(int etat) { this.etat = etat;}

    public String getId() {return this.id; }
    public void setId(String id){ this.id = id; }

    public Annonces() {
        // Initialisation de l'ID, par exemple :
        this.setId(UUID.randomUUID().toString());
    }

    // public Annonces(String _id, Object personne,Object voiture, Date datepublication,int etat ) {
    //     this.setId(_id);
    //     this.setPersonne(personne);
    //     this.setVoiture(voiture);
    //     this.setDatepublication(datepublication);
    //     this.setEtat(etat);
    // }
}
