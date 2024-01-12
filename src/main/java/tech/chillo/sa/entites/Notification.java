package tech.chillo.sa.entites;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notification")
public class Notification {

    @Id
    private int _id;
    private String type;
    private int etat;
    private Object content;
    // Ajoutez d'autres champs en fonction de votre modèle

    
    // Getters et setters
    public int get_id() {return _id;}
    public String getType() {return type;}
    public int getEtat() {return etat;}
    public Object getContent() {return content;}
    
    public void set_id(int id) {this._id = _id;}
    public void setType(String type) {this.type = type;}
    public void setEtat(int etat) {this.etat = etat;}
    public void setContent(Object content) {this.content = content;}

    public Notification () {}
}

