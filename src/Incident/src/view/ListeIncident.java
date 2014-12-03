package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import model.TableModel;
import model.dao.DAOFactory;
import model.dao.DAOIntervention;
import model.dao.DAOListeIncident;
import model.pojo.Technicien;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;


import controller.Control;

public class ListeIncident extends JFrame {

	private DAOListeIncident daoListeIncident = DAOFactory
			.getDAOListeIncident();
	private DAOIntervention daoIntervention = DAOFactory.getDAOIntervention();
	private JTable tablePanel;
	private Control control;
	private ListeIncident listeIncident;
	private TableModel tableModel;
	private Technicien technicien;

	public ListeIncident(Control control, Technicien technicien) {
		this.control = control;
		this.technicien = technicien;
		final JPanel jPanel = new JPanel();
		jPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		jPanel.setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Technicien :" + technicien.getName());
		jPanel.setLayout(new BorderLayout());

		/*
		 * CREATION DES LABELS
		 */

		JLabel lblListeDesIncidents = new JLabel("Liste des incidents:");
		lblListeDesIncidents.setBounds(10, 11, 414, 14);
		jPanel.add(lblListeDesIncidents, BorderLayout.NORTH);

		/*
		 * CREATION DU JTABLE
		 */

		tableModel = daoListeIncident.getNonResolu();
		tablePanel = new JTable(tableModel);
		JScrollPane tableContainer = new JScrollPane(tablePanel);
		jPanel.add(tableContainer, BorderLayout.CENTER);
		getContentPane().add(jPanel);

		/*
		 * CREATION DES BOUTONS
		 */

		JPanel Boutons = new JPanel();
		jPanel.add(Boutons, BorderLayout.SOUTH);

		JButton btnIncidentOk = new JButton("Incident OK");
		Boutons.add(btnIncidentOk);
		btnIncidentOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// LE BOUTON INCIDENTOK RECUPERE L'ID POSTE ET L'ID INCIDENT DE
				// LA LIGNE SURLIGNE

				String idposte = tablePanel.getValueAt(
						tablePanel.getSelectedRow(), 0).toString();
				String idincident = tablePanel.getValueAt(
						tablePanel.getSelectedRow(), 1).toString();
				System.out.println(idposte + "" + idincident);
				ReporterOk(listeIncident, idposte, idincident);

				// LA JTABLE EST RECONSTRUITE

				tableModel = daoListeIncident.getNonResolu();
				tablePanel = new JTable(tableModel);
				JScrollPane tableContainer = new JScrollPane(tablePanel);
				jPanel.add(tableContainer, BorderLayout.CENTER);
				listeIncident.getContentPane().add(jPanel);
				listeIncident.revalidate();

			}
		});
		
		//LE BOUTTON HISTORIQUE ALTERNE LA VUE ENTRE LA JTABLE DES INCIDENTS RESOLUS ET NON RESOLUS

		JToggleButton tglbtnHistorique = new JToggleButton("Historique");
		Boutons.add(tglbtnHistorique);
		tglbtnHistorique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JToggleButton tBtn = (JToggleButton) e.getSource();
				if (tBtn.isSelected()) {
					tableModel = daoIntervention.getResolu();
					tablePanel = new JTable(tableModel);
					JScrollPane tableContainer = new JScrollPane(tablePanel);
					jPanel.add(tableContainer, BorderLayout.CENTER);
					listeIncident.revalidate();
				} else if (!tBtn.isSelected()) {
					tableModel = daoListeIncident.getNonResolu();
					tablePanel = new JTable(tableModel);
					JScrollPane tableContainer = new JScrollPane(tablePanel);
					jPanel.add(tableContainer, BorderLayout.CENTER);
					listeIncident.revalidate();
				}

			}
		});

		listeIncident.pack();

	}
	
	/*
	 * CETTE METHODE APPELLE LA METHODE INCIDENTOK DE LA CLASSE CONTROL
	 */

	private void ReporterOk(ListeIncident listeIncident, String idPoste,
			String idIncident) {
		this.control.IncidentOk(idPoste, idIncident, technicien);

	}

}
