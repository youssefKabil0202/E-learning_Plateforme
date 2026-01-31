package service;

import dao.IDao;
import entities.Apprenant;
import connexion.Connexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApprenantService implements IDao<Apprenant> {
    
    @Override
    public boolean create(Apprenant o) {
        String req = "INSERT INTO apprenant (nom, email, dateInscription) VALUES (?, ?, ?)";
        int etat = 0;
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getEmail());
            ps.setDate(3, new java.sql.Date(o.getDateInscription().getTime()));
            etat = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (etat == 0) ? false : true;
    }

    @Override
    public boolean delete(Apprenant o) {
        String req = "DELETE FROM apprenant WHERE id = ?";
        int etat = 0;
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            etat = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (etat == 0) ? false : true;
    }

    @Override
    public boolean update(Apprenant o) {
        String req = "UPDATE apprenant SET nom = ?, email = ?, dateInscription = ? WHERE id = ?";
        int etat = 0;
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getEmail());
            ps.setDate(3, new java.sql.Date(o.getDateInscription().getTime()));
            ps.setInt(4, o.getId());
            etat = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (etat == 0) ? false : true;
    }

    @Override
    public List<Apprenant> findAll() {
        List<Apprenant> apprenants = new ArrayList<>();
        String req = "SELECT * FROM apprenant";
        try {
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                apprenants.add(new Apprenant(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getDate("dateInscription")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apprenants;
    }

    @Override
    public Apprenant findById(int id) {
        String req = "SELECT * FROM apprenant WHERE id = ?";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Apprenant(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getDate("dateInscription")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Apprenant> findByNom(String nom) {
        List<Apprenant> apprenants = new ArrayList<>();
        String req = "SELECT * FROM apprenant WHERE nom LIKE ?";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, "%" + nom + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                apprenants.add(new Apprenant(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getDate("dateInscription")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apprenants;
    }
}