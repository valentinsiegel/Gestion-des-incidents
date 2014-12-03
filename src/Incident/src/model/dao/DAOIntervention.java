package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TableModel;

import model.pojo.Incident;
import model.pojo.Technicien;

public class DAOIntervention {
	
	/*
	 * CETTE CLASSE CONTIENT LES REQUETES SQL ADRESSEES A LA TABLE INTERVENTION
	 */

	private Connection connection;
	private String req;
	private PreparedStatement prepare;
	private ResultSet result;
	private TableModel tableModel;

	
	public DAOIntervention(Connection connection) {
		this.connection=connection;
	}
	
	/*
	 * CETTE METHODE CREE UNE ENTREE DANS LA TABLE INTERVENTION A PARTIR D'UN OBJET TECHNICIEN ET D'UN OBJET INCIDENT
	 */
		
		public void createIntervention(Incident incident, Technicien technicien){
			req="INSERT INTO intervention (technicien_id, incident_id) VALUES ((SELECT id FROM technicien WHERE id=?),(SELECT id FROM incident WHERE id=?))";
			try {
				prepare = this.connection.prepareStatement(req);
				prepare.setInt(1, technicien.getId());
				prepare.setInt(2, incident.getId());
				prepare.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * CETTE METHODE RETOURNE LA LISTE DES INCIDENTS RESOLUS DANS UN OBJET TABLEMODEL
		 */
		
		public TableModel getResolu(){
			req="SELECT technicien.nom AS 'TECH', incident.* FROM technicien, incident, intervention WHERE technicien.id=technicien_id AND incident.id=incident_id";
			try {
				result=this.connection.createStatement().executeQuery(req);
				while(result.next()){
					tableModel = new TableModel( result );
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tableModel;
		}
}

