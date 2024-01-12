package tech.chillo.sa.entites;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="personne")
public class Personne {
@Entity
@Table(name = "Personne")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private Date dtn;
    private String contact;
    private String email;

    public int getId(){return this.id;}
    public void setId(int id){this.id = id;}
    public String getNom(){return this.nom;}
    public void setNom(String nom){this.nom = nom;}
    public String getPrenom(){return this.prenom;}
    public void setPrenom(String prenom){this.prenom = prenom;}
    public Date getDtn(){return this.dtn;}
    public void setDtn(Date dtn){this.dtn = dtn;}
    public String getContact(){return this.contact;}
    public void setContact(String contact){this.contact= contact;}
    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email = email;}

    public Personne(){}
    public Personne(String nom, String prenom, Date dtn, String contact, String email){
        setNom(nom);
        setPrenom(prenom);
        setDtn(dtn);
        setContact(contact);
        setEmail(email);
    }
    public Personne(int id, String nom, String prenom, Date dtn, String contact, String email){
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setDtn(dtn);
        setContact(contact);
        setEmail(email);
    private String prenoms;
    private Date datedenaissance;
    private String mail;
    private String telephone;
    private String adresse;
    private String motdepasse;

    public Personne() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

      // Getter et Setter pour le champ 'nom'
      public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter et Setter pour le champ 'prenoms'
    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    // Getter et Setter pour le champ 'datedenaissance'
    public Date getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(Date datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    // Getter et Setter pour le champ 'mail'
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    // Getter et Setter pour le champ 'telephone'
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    // Getter et Setter pour le champ 'adresse'
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    // Getter et Setter pour le champ 'motdepasse'
    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
}
