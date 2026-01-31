package service;

import connexion.Connexion;
import entities.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminService {
    
    public boolean authenticate(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = Connexion.getConnection();
            String query = "SELECT * FROM admin WHERE nom_utilisateur = ? AND mot_de_passe = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            resultSet = preparedStatement.executeQuery();
            
            return resultSet.next(); // Returns true if user exists
            
        } catch (Exception e) {
            System.err.println("Error in authenticate: " + e.getMessage());
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    public Admin getAdminByUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = Connexion.getConnection();
            String query = "SELECT * FROM admin WHERE nom_utilisateur = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setNomUtilisateur(resultSet.getString("nom_utilisateur"));
                admin.setMotDePasse(resultSet.getString("mot_de_passe"));
                return admin;
            }
            
        } catch (Exception e) {
            System.err.println("Error in getAdminByUsername: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return null;
    }
}