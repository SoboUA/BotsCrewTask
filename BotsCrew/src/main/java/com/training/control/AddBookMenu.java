package com.training.control;

import com.training.i18n.LanguageOption;
import com.training.jdbc.dao.BookDao;
import com.training.jdbc.elements.Book;
import com.training.scanner.MyScanner;
import com.training.service.FactoryMethod;

public class AddBookMenu implements Command {

	private BookDao resources = FactoryMethod.getInstance().createResources();
	private Book book;
	private MyScanner scn = MyScanner.getInstance();

	@Override
	public void execute() {
		book = new Book();
		System.out.println(LanguageOption.getOption("add_book1"));
		book.setAuthorEng(scn.readLine());
		System.out.println(LanguageOption.getOption("add_book2"));
		book.setAuthorUkr(scn.readLine());
		System.out.println(LanguageOption.getOption("add_book3"));
		book.setNameEng(scn.readLine());
		System.out.println(LanguageOption.getOption("add_book4"));
		book.setNameUkr(scn.readLine());
		resources.create(book);
		System.out.println(LanguageOption.getOption("add_book5"));

	}

}
