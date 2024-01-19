package tech.chillo.sa.entites;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

@Document(collection = "profil")
public class Profil {

    @Id
    private String id;
    private int idpersonne;
    private byte[] image;

    // Getters and setters
    public String getId(){ return this.id;}
    public void setId(String id) { this.id = id;}
    
    public int getIdpersonne(){ return this.idpersonne;}
    public void setIdpersonne(int idpersonne) { this.idpersonne = idpersonne;}
    
    public byte[] getImage(){ return this.image;}
    public void setImage(byte[] image) { this.image = image;}

    public void setImage(MultipartFile file) { 
        try {
            this.setImage(file.getBytes());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    public Profil(int idpersonne, byte[] image) {
        this.setId(UUID.randomUUID().toString());
        this.setIdpersonne(idpersonne);
        this.setImage(image);
    }

    public Profil() {this.setId(UUID.randomUUID().toString());}
}
