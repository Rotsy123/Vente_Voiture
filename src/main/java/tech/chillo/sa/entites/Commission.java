//package tech.chillo.sa.entites;
//
//import jakarta.persistence.*;
//
//import java.sql.Date;
//
//public class Commission {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id;
//    @OneToOne(fetch = FetchType.EAGER,mappedBy = "commission", cascade = CascadeType.MERGE)
//    Annonce annonce;
//    Date date;
//    double prix;
//
//
//    public Commission(){}
//    public Commission(Annonce annonce, Date date, double prix)throws Exception{
//        this.annonce = annonce;
//        this.date = date;
//        this.setPrix(prix);
//    }
//
//    public Commission(int id, Annonce annonce, Date date, double prix)throws Exception{
//        this.id = id;
//        this.annonce = annonce;
//        this.date = date;
//        this.setPrix(prix);
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Annonce getAnnonce() {
//        return annonce;
//    }
//
//    public void setAnnonce(Annonce annonce) {
//        this.annonce = annonce;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public double getPrix() {
//        return prix;
//    }
//
//    public void setPrix(double prix) throws Exception {
//        if(prix<0){
//            throw new Exception("PRIX NEGATIF");
//        }
//        this.prix = prix;
//    }
//}
