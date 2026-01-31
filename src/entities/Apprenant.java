package entities;

import java.util.Date;

public class Apprenant  {
    private int id;
    private String nom;
    private String email;
    private Date dateInscription;
    
    public Apprenant() {
        this.dateInscription = new Date();
    }
    
    public Apprenant(String nom, String email) {
        this.nom = nom;
        this.email = email;
        this.dateInscription = new Date();
    }
    
    public Apprenant(int id, String nom, String email, Date dateInscription) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.dateInscription = dateInscription;
    }
    
    
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
    
    public String getEmail() {
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public Date getDateInscription() { 
        return dateInscription;
    }
    public void setDateInscription(Date dateInscription) { 
        this.dateInscription = dateInscription; 
    }
    
    @Override
    public String toString() {
        return nom;
    }
}