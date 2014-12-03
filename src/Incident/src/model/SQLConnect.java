package model;

import java.sql.*;

public class SQLConnect {
	private static String url = "jdbc:mysql://192.168.1.5/";
	private static String dbName = "incident";
	private static String userName = "root";
	private static String password = "root";
	private static Connection connection;
	
	/*
	 * SI NON EXISTANTE CETTE METHODE INSTANCIE LA CONNEXION A LA BASE DE DONNEE
	 */
	public static Connection getSQLInstance() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url + dbName,
						userName, password);
				System.out.println("Connexion créée.");
			} catch (SQLException e) {
				System.out.println("Echec de la connexion.");
				e.printStackTrace();
			}
		} else {
			System.out.println("Connexion déjà existante.");
		}
		return connection;
	}
}
