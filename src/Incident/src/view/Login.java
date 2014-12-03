package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.JLabel;

import controller.Control;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JTextField password;
	private Control control;

	/*
	 * CONSTRUCTEUR
	 */

	public Login(Control control) {
		setTitle("Log In");
		this.control = control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		/*
		 * CREATION DES BOUTTONS
		 */

		JButton btnINCIDENT = new JButton("Rapporter un incident");
		btnINCIDENT.setBounds(10, 223, 421, 63);
		btnINCIDENT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReporterClick();
			}
		});

		JButton btnLOGIN = new JButton("Log In");
		btnLOGIN.setBounds(10, 159, 421, 53);
		btnLOGIN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginClick();
			}
		});

		/*
		 * CREATION DES CHAMPS D'IDENTIFICATION
		 */

		userName = new JTextField();
		userName.setBounds(159, 58, 170, 20);
		userName.setColumns(10);

		password = new JTextField();
		password.setBounds(159, 114, 170, 20);
		password.setColumns(10);

		/*
		 * CREATION DES LABELS
		 */

		JLabel lblNomDutilisateur = new JLabel("Nom d'utilisateur :");
		lblNomDutilisateur.setBounds(20, 61, 121, 14);

		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setBounds(20, 117, 121, 14);

		JLabel lblSystmeDeGestion = new JLabel(
				"SYSTEME DE GESTION D'INCIDENT");
		lblSystmeDeGestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystmeDeGestion.setBounds(10, 11, 421, 36);

		contentPane.setLayout(null);
		contentPane.add(btnINCIDENT);
		contentPane.add(btnLOGIN);
		contentPane.add(userName);
		contentPane.add(password);
		contentPane.add(lblSystmeDeGestion);
		contentPane.add(lblNomDutilisateur);
		contentPane.add(lblMotDePasse);
	}

	/*
	 * METHODES DES BOUTTONS
	 */

	private void LoginClick() {
		this.control.Check(this, getUser(userName), getPwd(password));
	}

	private void ReporterClick() {
		this.control.Report(this);

	}

	/*
	 * GETTERS SUR LES IDENTIFIANTS
	 */

	public String getPwd(JTextField password) {
		String mdp = password.getText();
		return mdp;
	}

	public String getUser(JTextField userName) {
		String user = userName.getText();
		return user;
	}
}