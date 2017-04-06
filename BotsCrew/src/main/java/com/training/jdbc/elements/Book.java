package com.training.jdbc.elements;

public class Book {

	private Integer id;
	private String nameEng;
	private String nameUkr;
	private String authorUkr;
	private String authorEng;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getNameUkr() {
		return nameUkr;
	}

	public void setNameUkr(String nameUkr) {
		this.nameUkr = nameUkr;
	}

	public String getAuthorUkr() {
		return authorUkr;
	}

	public void setAuthorUkr(String authorUkr) {
		this.authorUkr = authorUkr;
	}

	public String getAuthorEng() {
		return authorEng;
	}

	public void setAuthorEng(String authorEng) {
		this.authorEng = authorEng;
	}
}
