package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

	private static String login = "root";
	private static String password = "";
	private static String url = "jdbc:mysql://localhost:3306/plateforme_elearning";
	
	private static Connection connection = null;
	
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, login, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver introvable "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("impossible de se connecter "+ e.getMessage());
		}	
	}


	public static Connection getConnection() {
		return connection;
	}
	
	public static void main(String[] args) {
		getConnection();
	}
}
