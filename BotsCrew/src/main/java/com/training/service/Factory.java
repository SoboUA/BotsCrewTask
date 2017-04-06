package com.training.service;

import com.training.jdbc.dao.BookDao;
import com.training.jdbc.transformer.BookTransformer;

public class Factory {

	private BookService service = null;
	private BookDao resources = null;
	private BookTransformer trasformer = null;
	public BookService createService() {
		if(service == null)
			service = new BookService();
		return service;
	}
	public BookDao createResources() {
		if(resources == null)
			resources = new BookDao();
		return resources;
	}
	public BookTransformer getTrasformer() {
		if(trasformer == null)
			trasformer = new BookTransformer();
		return trasformer;
	}
	

}
