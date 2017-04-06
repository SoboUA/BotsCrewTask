package com.training.jdbc.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.training.jdbc.elements.Book;

public class BookTransformer {
	public ArrayList<Book> getAllBooks(ResultSet rs) throws SQLException {
		ArrayList<Book> allUnits = new ArrayList<>();
		while (rs.next()) {
			Book book = getBook(rs);
			allUnits.add(book);
		}
		return allUnits;
	}

	public Book getBook(ResultSet result) throws SQLException {
		Book book = new Book();
		book.setId(result.getInt("id_book"));
		book.setNameEng(result.getString("nameEng"));
		book.setNameUkr(result.getString("nameUkr"));
		book.setAuthorEng(result.getString("authorEng"));
		book.setAuthorUkr(result.getString("authorUkr"));
		return book;

	}
}
