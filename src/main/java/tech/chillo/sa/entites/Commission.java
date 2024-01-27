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
//    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "commission", cascade = CascadeType.MERGE)
//    Annonce annonce;
//    Date datedebut;
//    Date datefin;
//    double prix;
//
//
//    public Commission(){}
//    public Commission(Annonce annonce, Date date, Date datefin,double prix)throws Exception{
//        this.annonce = annonce;
//        this.datedebut = date;
//        this.datefin = datefin;
//        this.setPrix(prix);
//    }
//
//    public Commission(int id, Annonce annonce, Date date,Date datefin, double prix)throws Exception{
//        this.id = id;
//        this.annonce = annonce;
//        this.datedebut = date;
//        this.datedebut = date;
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
//    public Date getDatedebut() {
//        return datedebut;
//    }
//
//    public void setDatedebut(Date datedebut) {
//        this.datedebut = datedebut;
//    }
//
//    public Date getDatefin() {
//        return datefin;
//    }
//
//    public void setDatefin(Date datefin) {
//        this.datefin = datefin;
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
