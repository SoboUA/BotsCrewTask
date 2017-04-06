package com.training.control;

import java.util.ArrayList;

import com.training.i18n.LanguageOption;
import com.training.jdbc.dao.BookDao;
import com.training.jdbc.elements.Book;
import com.training.scanner.MyScanner;
import com.training.service.BookService;
import com.training.service.FactoryMethod;

public class BookMenu implements Command {
	private ArrayList<Book> list;
	private BookDao resources = FactoryMethod.getInstance().createResources();
	private BookService service = FactoryMethod.getInstance().createService();
	private MyScanner scn = MyScanner.getInstance();

	@Override
	public void execute() {
		System.out.println(LanguageOption.getOption("book_menu1"));
		String choice = scn.readLine();
		switch (choice) {
		case "1":
			list = resources.getAll();
			chooseBook(list);
			break;
		case "2":
			System.out.print(LanguageOption.getOption("book_menu2"));
			choice = scn.readLine();
			list = resources.getByName(choice);
			if (list.isEmpty()) {
				System.out.println(LanguageOption.getOption("no_books"));
			} else
				chooseBook(list);
			break;
		case "3":
			System.out.print(LanguageOption.getOption("book_menu2"));
			choice = scn.readLine();
			list = resources.getByAuthor(choice);
			if (list.isEmpty()) {
				System.out.println(LanguageOption.getOption("no_books"));
			} else
				chooseBook(list);
			break;
		case "0":
			break;
		default:
			System.out.println(LanguageOption.getOption("error"));
			execute();
			break;
		}

	}

	private void chooseBook(ArrayList<Book> list) {
		System.out.println(LanguageOption.getOption("book_menu_choice"));//
		service.showList(list);
		String choice = scn.readLine();
		boolean isNumeric = choice.chars().allMatch(Character::isDigit);
		if (!choice.isEmpty() && isNumeric) {
			int option = Integer.parseInt(choice);
			if (option > 0 && option <= list.size()) {
				System.out.println(LanguageOption.getOption("book_menu_answer") + service.showBook(list, option - 1));
			} else if (option == 0) {
				execute();
			} else {
				System.out.println(LanguageOption.getOption("error"));
				chooseBook(list);
			}
		} else {
			System.out.println(LanguageOption.getOption("error"));
			chooseBook(list);
		}

	}

}
