package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.pojo.Incident;

public class DAOIncident {
	
	/*
	 * CETTE CLASSE FAIT INTERFACE ENTRE LES OBJETS INCIDENTS ET LEURS ENTREES EQUIVALENTES DANS LA BASE DE DONNEE
	 */
	
	private PreparedStatement prepare;
	private String req;
	private Connection connnection;
	private ResultSet result;

	public DAOIncident(Connection connection) {
		this.connnection=connection;
	}
	
	/*
	 * CETTE METHODE CREER UNE ENTREE DANS LA TABLE INCIDENT DEPUIS UN OBJET INCIDENT
	 */

	public Incident create(Incident object) {
		req = "INSERT `incident`(`nom`, `description`, `date`, `resolu`, `type`) VALUES (?,?,?,?,(SELECT id FROM type_incident WHERE nom=?))";
		try {
			prepare = this.connnection.prepareStatement(req);
			prepare.setString(1, object.getNom());
			prepare.setString(2, object.getDesc());
			prepare.setTimestamp(3, object.getDate());
			prepare.setBoolean(4, object.getResolu());
			prepare.setString(5, object.getType());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	/*
	 * CETTE METHODE DETRUIT UNE ENTREE DANS LA TABLE INCIDENT DEPUIS UN OBJET INCIDENT
	 */
	
	public Incident delete(Incident incident) {
		req = "DELETE FROM incident WHERE id=?";
		try {
			prepare = this.connnection.prepareStatement(req);
			prepare.setString(1, incident.getDesc());
			prepare.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return incident;
	}
	
	/*
	 * CETTE METHODE MARQUE UN INCIDENT COMME RESOLU DANS LA BASE DE DONNEE DEPUIS UN OBJET INCIDENT
	 */
	
	public Incident updateIncidentOk(Incident incident) {
		req = "UPDATE Incident SET resolu=true WHERE id=?";
		try {
			prepare = this.connnection.prepareStatement(req);
			prepare.setInt(1, incident.getId());
			prepare.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return incident;
	}
	
	/*
	 *CETTE METHODE RENVOIT L'ID UTILISE DANS LA BASE DE DONNEE POUR UN OBJET INCIDENT DONNNE
	 */
	
	public int getId(Incident incident) {
		int id = 0;
		try {
			result = this.connnection.createStatement().executeQuery(
					"SELECT id FROM incident WHERE nom='" + incident.getNom()
							+ "'");
			if (result.first()) {
				id = Integer.parseInt(result.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
