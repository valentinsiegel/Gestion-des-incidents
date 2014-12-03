package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TableModel;

public class DAOListeIncident {

	/*
	 * CETTE CLASSE CONTIENT LES REQUETES SQL ADRESSEES A LA TABLE
	 * LISTE_INCIDENT
	 */

	private Connection connection;
	private String req;
	private PreparedStatement prepare;
	private ResultSet result;

	public DAOListeIncident(Connection connection) {
		this.connection = connection;
	}

	/*
	 * CETTE METHODE CREEE UNE ENTREE DANS LA TABLE LISTE_INCIDENT A PARTIR D'UN
	 * ID INCIDENT ET DU NOM D'UN POSTE
	 */

	public void create(int idIncident, String nomPoste) {
		req = "INSERT INTO liste_incident (poste_id,incident_id) VALUES ((SELECT id FROM poste WHERE nom='"
				+ nomPoste + "')," + idIncident + ")";
		try {
			prepare = this.connection.prepareStatement(req);
			prepare.execute();
			System.out.println(prepare);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * CETTE METHODE RENVOIT LE LISTE DES INCIDENTS NON RESOLUS DANS UN OBJET
	 * TABLEMODEL
	 */

	public TableModel getNonResolu() {
		TableModel rtm = null;
		req = "SELECT poste.id, incident.* FROM poste, incident, liste_incident WHERE poste_id=poste.id AND incident_id=incident.id AND resolu=false";
		try {
			result = this.connection.createStatement().executeQuery(req);

			rtm = new TableModel(result);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rtm;
	}
	
	/*
	 * CETTE METHODE DETRUIT UNE ENTREE DANS LA TABLE LISTE_INCIDENT A PARTIR D'UN ID POSTE ET D'UN ID INCIDENT
	 */

	public void delete(String idPoste, String idIncident) {
		req = "DELETE FROM liste_incident WHERE poste_id=? AND incident_id=?";
		try {
			prepare = this.connection.prepareStatement(req);
			prepare.setString(1, idPoste);
			prepare.setString(2, idIncident);
			prepare.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
