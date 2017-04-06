package com.training.control;

import java.util.ArrayList;

import com.training.i18n.LanguageOption;
import com.training.jdbc.dao.BookDao;
import com.training.jdbc.elements.Book;
import com.training.scanner.MyScanner;
import com.training.service.BookService;
import com.training.service.FactoryMethod;

public class EditBookMenu implements Command {

	private ArrayList<Book> list;
	private BookDao resources =  FactoryMethod.getInstance().createResources();
	private BookService service = FactoryMethod.getInstance().createService();
	private MyScanner scn = MyScanner.getInstance();

	@Override
	public void execute() {
		System.out.println(LanguageOption.getOption("edit_menu"));
		list = resources.getAll();
		service.showList(list);
		editBook();
	}

	private void editBook() {
		System.out.println(LanguageOption.getOption("edit_option"));
		String command = scn.readLine();
		String[] choices = command.split(" ");
		boolean isCommand = false;
		switch (choices[0]) {
		case "видалити":
		case "delete":
			service.deleteBook(choices);
			break;
		case "змінити":
		case "edit":
			service.editBook(choices);
			break;
		case "0":
			break;
		default:
			System.out.println(LanguageOption.getOption("error"));
			editBook();
			break;
		}

	}

}
