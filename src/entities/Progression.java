package entities;

import java.io.Serializable;
import java.util.Date;

public class Progression implements Serializable {
    private int id;
    private Cours cours;
    private Apprenant apprenant;
    private Date date;
    private double score;
    
    public Progression() {
        this.date = new Date();
    }
    
    public Progression(Cours cours, Apprenant apprenant,Date date ,double score) {
        this.cours = cours;
        this.apprenant = apprenant;
        this.score = score;
        this.date = date;
    }
    
    public Progression(int id, Cours cours, Apprenant apprenant, Date date, double score) {
        this.id = id;
        this.cours = cours;
        this.apprenant = apprenant;
        this.date = date;
        this.score = score;
    }
    
    // Getters and Setters
    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    
    public Cours getCours() { 
        return cours; 
    }
    public void setCours(Cours cours) { 
        this.cours = cours; 
    }
    
    public Apprenant getApprenant() { 
        return apprenant; 
    }
    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant; 
    }
    
    public Date getDate() { 
        return date; 
    }
    public void setDate(Date date) {
        this.date = date; 
    }
    
    public double getScore() {
        return score; 
    }
    public void setScore(double score) {
        this.score = score; 
    }
    
    @Override
    public String toString() {
        return "Progression{" + "id=" + id + ", cours=" + cours.getTitre() + 
               ", apprenant=" + apprenant.getNom() + ", date=" + date + 
               ", score=" + score + '}';
    }
}