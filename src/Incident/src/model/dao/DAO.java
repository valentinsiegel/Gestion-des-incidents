package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class DAO<T> {

	protected PreparedStatement prepare;
	protected Connection connection;
	protected String req;
	protected ResultSet result;

	public DAO(Connection connection) {
		this.connection = connection;
	}

	public abstract T create(T object);

	public abstract T delete(T object);

	public abstract T update(T object);

	public abstract int getId(T object);

	public abstract int getId(String string);

}
