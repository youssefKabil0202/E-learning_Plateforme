package service;

import dao.IDao;
import entities.Cours;
import connexion.Connexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursService implements IDao<Cours> {
    
    @Override
    public boolean create(Cours o) {
        String req = "INSERT INTO cours (titre, categorie, dureeHeures) VALUES (?, ?, ?)";
        int etat = 0;
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getCategorie());
            ps.setDouble(3, o.getDureeHeures());
            etat = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (etat == 0) ? false : true;
    }

    @Override
    public boolean delete(Cours o) {
        String req = "DELETE FROM cours WHERE id = ?";
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
    public boolean update(Cours o) {
        String req = "UPDATE cours SET titre = ?, categorie = ?, dureeHeures = ? WHERE id = ?";
        int etat = 0;
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getCategorie());
            ps.setDouble(3, o.getDureeHeures());
            ps.setInt(4, o.getId());
            etat = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (etat == 0) ? false : true;
    }

    @Override
    public List<Cours> findAll() {
        List<Cours> coursList = new ArrayList<>();
        String req = "SELECT * FROM cours";
        try {
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                coursList.add(new Cours(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("categorie"),
                    rs.getDouble("dureeHeures")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursList;
    }

    @Override
    public Cours findById(int id) {
        String req = "SELECT * FROM cours WHERE id = ?";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cours(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("categorie"),
                    rs.getDouble("dureeHeures")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Cours> findByCategorie(String categorie) {
        List<Cours> coursList = new ArrayList<>();
        String req = "SELECT * FROM cours WHERE categorie = ?";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, categorie);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                coursList.add(new Cours(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("categorie"),
                    rs.getDouble("dureeHeures")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursList;
    }
}