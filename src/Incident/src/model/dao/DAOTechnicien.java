package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.pojo.Technicien;

public class DAOTechnicien {

	/*
	 * CETTE CLASSE VERIFIE LES INFORMATIONS SAISIES DANS LA FENETRE LOGIN ET
	 * RETOURNE UN OBJET TECHNICIEN INITIALISE
	 */

	private Connection connection;
	private String req;

	public DAOTechnicien(Connection connection) {
		this.connection = connection;
	}

	public Technicien LoginTechnicien(String userName, String password) {
		Technicien technicien = null;
		req = "SELECT * FROM technicien WHERE login='" + userName
				+ "' AND password='" + password + "'";
		try {
			ResultSet result = this.connection.createStatement().executeQuery(
					req);
			if (result.first()) {
				technicien = new Technicien(result.getInt("id"),
						result.getString("nom"), result.getString("login"),
						result.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return technicien;
	}

}
