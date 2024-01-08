package tech.chillo.sa.entites;
import jakarta.persistence.*;

@Entity
@Table(name = "Carburant")
public class Carburant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    public Carburant(int id, String nom) {
        this.setId(id);
        this.setNom(nom);
    }
    public Carburant(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
