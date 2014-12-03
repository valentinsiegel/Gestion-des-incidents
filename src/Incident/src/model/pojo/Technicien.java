package model.pojo;

public class Technicien {

	/*
	 * ATTRIBUTS
	 */

	private int id;
	private String name;
	private String login;
	private String password;

	/*
	 * CONSTRUCTEUR
	 */

	public Technicien(int id, String name, String login, String password) {
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
	}

	/*
	 * GETTERS
	 */

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public int getId() {
		return id;
	}

	/*
	 * SETTERS
	 */

	public void setLoggin(String login) {
		this.login = login;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}
}
