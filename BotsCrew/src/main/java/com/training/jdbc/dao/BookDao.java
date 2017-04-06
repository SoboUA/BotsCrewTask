package com.training.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.training.jdbc.elements.Book;
import com.training.jdbc.transformer.BookTransformer;

public class BookDao extends AbstractDao<Book> {
	private BookTransformer transformer = new BookTransformer();

	@Override
	public ArrayList<Book> getAll() {
		String query = "SELECT * FROM books ";
		return getByQuery(query);
	}

	public ArrayList<Book> getByName(String search) {
		String query = "SELECT * FROM books where lower(nameEng) LIKE '%" + search.toLowerCase() + "%' OR lower(nameUkr) LIKE '%" + search.toLowerCase()
				+ "%';";
		return getByQuery(query);
	}

	public ArrayList<Book> getByAuthor(String search) {
		String query = "SELECT * FROM books where lower(authorEng) LIKE '%" + search.toLowerCase() + "%' OR lower(authorUkr) LIKE '%" + search.toLowerCase()
				+ "%';";
		return getByQuery(query);
	}

	@Override
	public void update(Book entity) {
		int index = entity.getId();
		StringBuilder query = new StringBuilder();
		query.append("UPDATE books ");
		query.append(" SET nameEng = '" + entity.getNameEng());
		query.append("' , nameUkr = '" + entity.getNameUkr());
		query.append("' , authorEng = '" + entity.getAuthorEng());
		query.append("' , authorUkr = '" + entity.getAuthorUkr());
		query.append("' WHERE id_book = " + index + ";");
		executeUpdate(query.toString());
	}

	@Override
	public void deleteById(Book entity) {
		int index = entity.getId();
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM books ");
		query.append(" WHERE id_book = " + index + ";");
		executeUpdate(query.toString());
	}

	@Override
	public void create(Book entity) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO books ");
		query.append("(nameEng, nameUkr, authorEng, authorUkr)");
		query.append(" VALUES ('" + entity.getNameEng() + "','" + entity.getNameUkr() + "','" + entity.getAuthorEng() + "','" + entity.getAuthorUkr() + "');");
		executeUpdate(query.toString());
	}

	protected ArrayList<Book> getByQuery(String query) {
		ArrayList<Book> bookList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			ps = getPreparedStatement(query);
			result = ps.executeQuery();
			bookList = transformer.getAllBooks(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				try {
					result.close();
					closePreparedStatement(ps);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return bookList;
	}
}
