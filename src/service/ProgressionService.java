package service;

import dao.IDao;
import entities.Progression;
import entities.Cours;
import entities.Apprenant;
import connexion.Connexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgressionService implements IDao<Progression> {
    
    private CoursService coursService;
    private ApprenantService apprenantService;
    
    public ProgressionService() {
        coursService = new CoursService();
        apprenantService = new ApprenantService();
    }

    @Override
    public boolean create(Progression o) {
        String req = "INSERT INTO progression (cours_id, apprenant_id, date, score) VALUES (?, ?, ?, ?)";
        int etat = 0;
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getCours().getId());
            ps.setInt(2, o.getApprenant().getId());
            ps.setDate(3, new java.sql.Date(o.getDate().getTime()));
            ps.setDouble(4, o.getScore());
            etat = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (etat == 0) ? false : true;
    }

    @Override
    public boolean delete(Progression o) {
        String req = "DELETE FROM progression WHERE id = ?";
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
    public boolean update(Progression o) {
        String req = "UPDATE progression SET cours_id = ?, apprenant_id = ?, date = ?, score = ? WHERE id = ?";
        int etat = 0;
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getCours().getId());
            ps.setInt(2, o.getApprenant().getId());
            ps.setDate(3, new java.sql.Date(o.getDate().getTime()));
            ps.setDouble(4, o.getScore());
            ps.setInt(5, o.getId());
            etat = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (etat == 0) ? false : true;
    }

    @Override
    public List<Progression> findAll() {
        List<Progression> progressions = new ArrayList<>();
        String req = "SELECT * FROM progression";
        try {
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Cours cours = coursService.findById(rs.getInt("cours_id"));
                Apprenant apprenant = apprenantService.findById(rs.getInt("apprenant_id"));
                
                progressions.add(new Progression(
                    rs.getInt("id"),
                    cours,
                    apprenant,
                    rs.getDate("date"),
                    rs.getDouble("score")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return progressions;
    }

    @Override
    public Progression findById(int id) {
        String req = "SELECT * FROM progression WHERE id = ?";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cours cours = coursService.findById(rs.getInt("cours_id"));
                Apprenant apprenant = apprenantService.findById(rs.getInt("apprenant_id"));
                
                return new Progression(
                    rs.getInt("id"),
                    cours,
                    apprenant,
                    rs.getDate("date"),
                    rs.getDouble("score")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Progression> findByApprenant(int apprenantId) {
        List<Progression> progressions = new ArrayList<>();
        String req = "SELECT * FROM progression WHERE apprenant_id = ? ORDER BY date DESC";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, apprenantId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cours cours = coursService.findById(rs.getInt("cours_id"));
                Apprenant apprenant = apprenantService.findById(apprenantId);
                
                progressions.add(new Progression(
                    rs.getInt("id"),
                    cours,
                    apprenant,
                    rs.getDate("date"),
                    rs.getDouble("score")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return progressions;
    }
    
    public List<Progression> findByCours(int coursId) {
        List<Progression> progressions = new ArrayList<>();
        String req = "SELECT * FROM progression WHERE cours_id = ? ORDER BY date DESC";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, coursId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cours cours = coursService.findById(coursId);
                Apprenant apprenant = apprenantService.findById(rs.getInt("apprenant_id"));
                
                progressions.add(new Progression(
                    rs.getInt("id"),
                    cours,
                    apprenant,
                    rs.getDate("date"),
                    rs.getDouble("score")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return progressions;
    }
    
    public List<Progression> findByScoreRange(double minScore, double maxScore) {
        List<Progression> progressions = new ArrayList<>();
        String req = "SELECT * FROM progression WHERE score BETWEEN ? AND ?";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setDouble(1, minScore);
            ps.setDouble(2, maxScore);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cours cours = coursService.findById(rs.getInt("cours_id"));
                Apprenant apprenant = apprenantService.findById(rs.getInt("apprenant_id"));
                
                progressions.add(new Progression(
                    rs.getInt("id"),
                    cours,
                    apprenant,
                    rs.getDate("date"),
                    rs.getDouble("score")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return progressions;
    }
    
    public double getMoyenneScoreByCours(int coursId) {
        String req = "SELECT AVG(score) as moyenne FROM progression WHERE cours_id = ?";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, coursId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("moyenne");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    
    public List<Object[]> getStatsByCours() {
        List<Object[]> stats = new ArrayList<>();
        String req = "SELECT c.titre, AVG(p.score) as moyenne FROM progression p " +
                    "JOIN cours c ON p.cours_id = c.id " +
                    "GROUP BY c.id, c.titre";
        try {
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Object[] stat = new Object[2];
                stat[0] = rs.getString("titre");
                stat[1] = rs.getDouble("moyenne");
                stats.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }
}