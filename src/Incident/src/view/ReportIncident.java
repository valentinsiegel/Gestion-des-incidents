package view;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import controller.Control;
import javax.swing.SwingConstants;



public class ReportIncident extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescIncident;
	private JTextField txtNomPoste;
	private Control control;
	private JTextField txtType;
	private JTextField txTitre;

	/*
	 * CONSTRUCTEUR
	 */
	public ReportIncident(Control control) {
		setTitle("Raporter un incident");
		this.control = control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*
		 * CREATION DU BOUTTON
		 */

		JButton btnValider = new JButton("Reporter");
		btnValider.setBounds(10, 522, 364, 29);
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReporterClick();
			}
		});

		/*
		 * CREATION DES CHAMPS
		 */

		txTitre = new JTextField();
		txTitre.setBounds(10, 29, 116, 20);

		txTitre.setColumns(10);
		txtNomPoste = new JTextField();
		txtNomPoste.setBounds(10, 143, 116, 20);
		txtNomPoste.setColumns(10);

		txtType = new JTextField();
		txtType.setBounds(10, 85, 116, 20);
		txtType.setColumns(10);

		txtDescIncident = new JTextField();
		txtDescIncident.setHorizontalAlignment(SwingConstants.LEFT);
		txtDescIncident.setBounds(10, 199, 364, 312);
		txtDescIncident.setColumns(10);

		/*
		 * CREATION DES LABELS
		 */

		JLabel lblNomPoste = new JLabel("Nom du poste:                       COMPTA/ACCUEIL");
		lblNomPoste.setBounds(10, 118, 364, 14);

		JLabel lblType = new JLabel("Type d'incident:                     virus/panne/internet");
		lblType.setBounds(10, 60, 364, 14);

		JLabel lblDescIncident = new JLabel("Description de l'incident:");
		lblDescIncident.setBounds(10, 174, 364, 14);

		JLabel lblTitre = new JLabel("Titre:");
		lblTitre.setBounds(10, 11, 364, 14);

		contentPane.add(lblTitre);
		contentPane.add(txTitre);
		contentPane.add(txtNomPoste);
		contentPane.add(txtType);
		contentPane.add(txtDescIncident);
		contentPane.add(lblDescIncident);
		contentPane.add(lblType);
		contentPane.add(lblNomPoste);
		contentPane.add(btnValider);
	}
				
		
	/*
	 * METHODE DU BOUTTON
	 */

	private void ReporterClick() {
		this.control.CreerIncident(getTxtNomPoste(), getTxtTitre(),
				getTxtDescIncident(), getTxtType());
	}
	
	private void PosteComboClick(){
		
		
	}

	/*
	 * GETTERS SUR LES CHAMPS
	 */

	public String getTxtTitre() {
		String nom = txTitre.getText();
		return nom;
	}

	public String getTxtDescIncident() {
		String desc = txtDescIncident.getText();
		return desc;
	}

	public String getTxtNomPoste() {
		String nom = txtNomPoste.getText();
		return nom;
	}

	public String getTxtType() {
		String type = txtType.getText();
		return type;
	}
}
