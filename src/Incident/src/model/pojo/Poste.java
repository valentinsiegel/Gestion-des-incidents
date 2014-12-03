package model.pojo;

public class Poste {

	/*
	 * ATTRIBUTS
	 */

	private int id;
	private String nom;

	/*
	 * CONSTRUCTEUR
	 */

	public Poste(int id, String nom) {
		this.id = id;
		this.nom = nom;

	}

	/*
	 * GETTERS
	 */
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	/*
	 * SETTERS
	 */

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
