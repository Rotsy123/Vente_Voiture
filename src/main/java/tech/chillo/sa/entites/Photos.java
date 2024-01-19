//package tech.chillo.sa.entites;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table (name="photos")
//public class Photos {
//    byte[] photos;
//    @ManyToOne
//    @JoinColumn(name="idvoiture")
//    Voiture voiture;
//
//    public Photos(byte[] photos, Voiture voiture) {
//        this.photos = photos;
//        this.voiture = voiture;
//    }
//    public Photos(){}
//
//    public byte[] getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(byte[] photos) {
//        this.photos = photos;
//    }
//
//    public Voiture getVoiture() {
//        return voiture;
//    }
//
//    public void setVoiture(Voiture voiture) {
//        this.voiture = voiture;
//    }
//}
