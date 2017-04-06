package com.training.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.training.jdbc.connection.ConnectionPool;

public abstract class AbstractDao<E> {

	public abstract void create(E entity);
	public abstract void update(E entity);
	public abstract void deleteById(E entity);
	public abstract ArrayList<E> getAll();
	
	protected void executeUpdate(String query) {
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closePreparedStatement(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void closePreparedStatement(PreparedStatement pr) throws SQLException {
		if (pr != null) {
			pr.close();
			ConnectionPool.closeConnection();
		}
	}

	protected PreparedStatement getPreparedStatement(String query) throws SQLException {
		return ConnectionPool.getConnection().prepareStatement(query.toString());
	}
}
