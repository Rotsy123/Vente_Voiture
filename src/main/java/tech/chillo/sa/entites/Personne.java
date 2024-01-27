package tech.chillo.sa.entites;

import jakarta.persistence.*;
import java.util.Date;

// import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Personne")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
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

    // public void setMotdepasse(String motdepasse1, String motdepasse2) throws Exception{
    //     if(motdepasse1.equalsIgnoreCase(motdepasse2)) this.setMotdepasse(motdepasse1); 
    //     else throw new Exception("Mot de passe invalide");
    // }
}
