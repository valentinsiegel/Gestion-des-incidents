package model.pojo;

import java.sql.Timestamp;

import org.joda.time.DateTime;

public class Incident {

	/*
	 * ATTRIBUTS
	 */

	private int id;
	private String nom;
	private String desc;
	private String type;
	private Boolean resolu;
	private Timestamp timeStamp;

	/*
	 * CONSTRUCTEUR
	 */

	public Incident(int id, String nom, String desc, Timestamp timeStamp,
			String type, Boolean estResolu) {
		this.id = id;
		this.nom = nom;
		this.desc = desc;
		// INSTANCIE UNE CLASSE DATETIME DE L'API JODA-TIME ET EST CONVERTIT EN
		// TIMESTAMP
		this.timeStamp = new Timestamp(new DateTime().getMillis());
		this.type = type;
		this.resolu = false;
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

	public String getDesc() {
		return this.desc;
	}

	public Timestamp getDate() {
		return this.timeStamp;
	}

	public String getType() {
		return this.type;
	}

	public Boolean getResolu() {
		return this.resolu;
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

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setDate(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setResolu(Boolean resolu) {
		this.resolu = resolu;
	}

}
