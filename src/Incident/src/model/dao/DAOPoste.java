package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.pojo.Poste;

public class DAOPoste {

	private Connection connection;
	private ResultSet result;

	public DAOPoste(Connection connection) {
		this.connection = connection;
	}

	/*
	 * ETTE METHODE RENVOIT L'ID UTILISE DANS LA BASE DE DONNEE POUR UN OBJET
	 * POSTE DONNNE
	 */

	public int getId(String nomPoste) {
		int idPoste = 0;
		try {
			result = this.connection.createStatement().executeQuery(
					"SELECT id FROM poste WHERE nom='" + nomPoste + "'");
			if (result.first()) {
				idPoste = Integer.parseInt(result.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idPoste;
	}

}
