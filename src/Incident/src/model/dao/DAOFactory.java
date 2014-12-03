package model.dao;

import java.sql.Connection;

import model.SQLConnect;

public class DAOFactory {
	
	/*
	 * CETTE CLASSE S'OCCUPE D'INSTANCIER LES CONNEXION A LA BASE DE DONNE POUR LES RENVOYER DANS DES VARIABLES
	 */
	
	protected static final Connection connection = SQLConnect.getSQLInstance();

	public static DAOIncident getDAOIncident() {
		return new DAOIncident(connection);
	}

	public static DAOPoste getDAOPoste() {
		return new DAOPoste(connection);
	}

	public static DAOListeIncident getDAOListeIncident() {
		return new DAOListeIncident(connection);
	}

	public static DAOTechnicien getDAOTechnicien() {
		return new DAOTechnicien(connection);
	}
	
	public	static DAOIntervention getDAOIntervention(){
		return new DAOIntervention(connection);
	}
}
