/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

public class Cours  {
    private int id;
    private String titre;
    private String categorie;
    private double dureeHeures;
    
    public Cours() {
    }
    
    public Cours(String titre, String categorie, double dureeHeures) {
        this.titre = titre;
        this.categorie = categorie;
        this.dureeHeures = dureeHeures;
    }
    
    public Cours(int id, String titre, String categorie, double dureeHeures) {
        this.id = id;
        this.titre = titre;
        this.categorie = categorie;
        this.dureeHeures = dureeHeures;
    }
    
    public int getId() {
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    
    public String getTitre() { 
        return titre; 
    }
    public void setTitre(String titre) { 
        this.titre = titre; 
    }
    
    public String getCategorie() { 
        return categorie; 
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie; 
    }
    
    public double getDureeHeures() { 
        return dureeHeures; 
    }
    public void setDureeHeures(double dureeHeures) { 
        this.dureeHeures = dureeHeures; 
    }
    
    @Override
    public String toString() {
        return titre; 
    }
}
