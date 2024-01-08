package tech.chillo.sa.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "Bouquet")
public class Bouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private double pourcentage_commission;

    public Bouquet() {
    }

    public Bouquet(int id, String nom, double pourcentage_commission) throws Exception {
        this.setId(id);
        this.setNom(nom);
        this.setPourcentage_commision(pourcentage_commission);
    }

    public int getId() {
        return this.id;
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

    public double getPourcentage_commission() {
        return pourcentage_commission;
    }

    public void setPourcentage_commision(double pourcentage_commission) throws Exception {
        if(pourcentage_commission>=0){
            this.pourcentage_commission = pourcentage_commission;
        }else{
            throw new Exception("LA VALEUR DE LA COMMISSION DOIT ETRE POSITIVE");
        }
    }
}
