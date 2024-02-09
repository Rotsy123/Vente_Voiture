 package tech.chillo.sa.entites;

 import jakarta.persistence.*;
 import org.springframework.data.mongodb.core.mapping.Document;

 import java.util.UUID;

 @Document(collection = "photos")
 public class Photos {
     @Id
     private String id;
     String[] photos;
     String idvoiture;

     public Photos(String[] photos, String idvoiture) {
         this.setId(UUID.randomUUID().toString());
         this.photos = photos;
         this.idvoiture = idvoiture;
     }
//     public Photos(String photos, Voiture voiture) {
//         this.photos = photos;
//         this.voiture = voiture;
//     }
     public Photos(){}

     public String getId(){
         return this.id;
     }
     public void setId(String id){
         this.id = id;
     }

     public String[] getPhotos() {
         return photos;
     }

     public void setPhotos(String[] photos) {
         this.photos = photos;
     }

     public String getIdvoiture() {
         return idvoiture;
     }

     public void setIdvoiture(String idvoiture) {
         this.idvoiture = idvoiture;
     }
 }
