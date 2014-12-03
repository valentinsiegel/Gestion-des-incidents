package controller;

import javax.swing.JOptionPane;
import model.dao.DAOFactory;
import model.dao.DAOIncident;
import model.dao.DAOIntervention;
import model.dao.DAOListeIncident;
import model.dao.DAOTechnicien;
import model.pojo.Incident;
import model.pojo.Technicien;
import view.ListeIncident;
import view.Login;
import view.ReportIncident;

public class Control {
	/*
	 * VARIABLES CONTENANT LES INSTANCES DE CONNEXION A LA BASE DE DONNEE
	 */
	private DAOIncident daoIncident = DAOFactory.getDAOIncident();
	private DAOListeIncident daoListeIncident = DAOFactory
			.getDAOListeIncident();
	private DAOTechnicien daoTechnicien = DAOFactory.getDAOTechnicien();
	private DAOIntervention daoIntervention = DAOFactory.getDAOIntervention();
	
	/*
	 * ENTREE DE L'APPLICATION
	 */
	
	public static void main(String[] args) {
		new Control();

	}
	
	/*
	 * INSTANCIE LA FENETRE DE LOGIN
	 */
	
	public Control() {
		Login frmLogin = new Login(this);
		frmLogin.setVisible(true);

	}
	
	/*
	 * VERIFICATION DU LOGIN TECHNICIEN
	 */
	
	public void Check(Login login, String nomutilisateur, String motdepasse) {

		Technicien technicien = daoTechnicien.LoginTechnicien(nomutilisateur,
				motdepasse);
		if (technicien == null) {
			JOptionPane.showMessageDialog(null, "Identifiants incorrects.");
		} else {
			ListeIncident listeincident = new ListeIncident(this, technicien);
			listeincident.setVisible(true);
			login.dispose();

		}

	}
	
	/*
	 * CREATION DE L'INCIDENT DEPUIS LA FENTRE REPORT INCIDENT
	 */
	
	public void CreerIncident(String poste, String nom, String desc, String type) {

		daoListeIncident
				.create(daoIncident.getId(daoIncident.create(new Incident('0',
						nom, desc, null, type, null))), poste);

	}
	
	/*
	 * INSTANCIE LA FENTRE DE REPORT D'INCIDENT
	 */

	public void Report(Login login) {
		ReportIncident reportIncident = new ReportIncident(this);
		reportIncident.setVisible(true);
		login.dispose();

	}
	
	/*
	 * CETTE METHODE MARQUE L'INCIDENT COMME ETANT RESOLU ET L'ARCHIVE
	 */

	public void IncidentOk(String idposte, String idincident,
			Technicien technicien) {

		//L'INCIDENT EST DETRUIT DEPUIS LA TABLE LISTE_INCIDENT
		daoListeIncident.delete(idposte, idincident);
		
		//UN OBJET INCIDENT EST INSTANCIE
		Incident incident = new Incident(Integer.parseInt(idincident), null,
				null, null, null, null);
		
		//LA VALEUR "RESOLU" EST REGLE SUR VRAI DANS LA TABLE INCIDENT
		daoIncident.updateIncidentOk(incident);
		
		//L'INCIDENT, AINSI QUE LE TECHNICIEN AYANT MARQUE L'INCIDENT COMME RESOLU EST ARCHIVE DANS LA TABLE INTERVENTION
		daoIntervention.createIntervention(incident, technicien);

	}

}
