package com.training.service;

import java.util.ArrayList;

import com.training.i18n.LanguageOption;
import com.training.jdbc.dao.BookDao;
import com.training.jdbc.elements.Book;
import com.training.scanner.MyScanner;

public class BookService {

	private BookDao resources = FactoryMethod.getInstance().createResources();
	private MyScanner scn = MyScanner.getInstance();

	public void showList(ArrayList<Book> list) {
		int optionLanguge = LanguageOption.getChoosenLanguage();
		int i = 1;
		if (optionLanguge == 1) {
			for (Book book : list) {
				System.out.print(i + "- " + book.getAuthorEng() + "  " + book.getNameEng() + ".    ");
				if (i % 3 == 0)
					System.out.println();
				i++;
			}
		} else if (optionLanguge == 2) {
			for (Book book : list) {
				System.out.print(i + "- " + book.getAuthorUkr() + "  " + book.getNameUkr() + ".    ");
				if (i % 3 == 0)
					System.out.println();
				i++;
			}
		}
	}

	public String showBook(ArrayList<Book> list, int index) {
		int optionLanguge = LanguageOption.getChoosenLanguage();
		String answer = null;
		if (optionLanguge == 1) {
			answer = list.get(index).getAuthorEng() + "  " + list.get(index).getNameEng();
		} else if (optionLanguge == 2) {
			answer = list.get(index).getAuthorUkr() + "  " + list.get(index).getNameUkr();
		}
		return answer;
	}

	public void deleteBook(String[] command) {
		String bookName = "";
		boolean access = true;
		if (command.length > 1) {
			for (int i = 1; i < command.length; i++) {
				bookName += command[i] + " ";
			}
		} else {
			System.out.println(LanguageOption.getOption("service1"));
			bookName = scn.readLine();
			if (bookName.isEmpty()) {
				access = false;
				deleteBook(command);
			}
		}
		ArrayList<Book> books = resources.getByName(bookName.trim());
		if (!books.isEmpty() && access) {
			if (books.size() > 1) {
				System.out.println(LanguageOption.getOption("edit_menu"));// you have more than one
												// choice
				showList(books);
				String choice = scn.readLine();
				if (choice.chars().allMatch(Character::isDigit) && Integer.parseInt(choice) < books.size() && Integer.parseInt(choice) > 0) {
					resources.deleteById(books.get(Integer.parseInt(choice)));
					System.out.println(LanguageOption.getOption("deleted"));
				} else {
					System.out.println(LanguageOption.getOption("error")); // choice
					deleteBook(command);
				}
			}else{
			resources.deleteById(books.get(0));
			System.out.println(LanguageOption.getOption("deleted"));
			}
			// deleted
		} else
			System.out.println(LanguageOption.getOption("no_books"));
	}

	public void editBook(String[] command) {
		String bookName = "";
		boolean access = true;
		if (command.length > 1) {
			for (int i = 1; i < command.length; i++) {
				bookName += command[i] + " ";
			}
		} else {
			System.out.println(LanguageOption.getOption("service1"));
			bookName = scn.readLine();
			if (bookName.isEmpty()) {
				access = false;
				editBook(command);
			}
		}
		ArrayList<Book> books = resources.getByName(bookName.trim());
		if (!books.isEmpty() && access) {
			Book edited = books.get(0);
			boolean option = true;
			while (option) {
				option = false;
				System.out.println(LanguageOption.getOption("edit_book"));
				String choices = scn.readLine();
				switch (choices) {
				case "1":
					System.out.println(LanguageOption.getOption("edit_book1"));
					edited.setAuthorEng(scn.readLine());
					break;
				case "2":
					System.out.println(LanguageOption.getOption("edit_book2"));
					edited.setAuthorUkr(scn.readLine());
					break;
				case "3":
					System.out.println(LanguageOption.getOption("edit_book3"));//
					edited.setNameEng(scn.readLine());
					break;
				case "4":
					System.out.println(LanguageOption.getOption("edit_book4"));
					edited.setNameUkr(scn.readLine());
					break;
				default:
					System.out.println(LanguageOption.getOption("error"));// wrong
																			// comand
					break;
				}
				resources.update(edited);
				System.out.println(LanguageOption.getOption("cont"));// continue
																		// y/n
				String answer = scn.readLine();
				if (answer.equals("yes") || answer.equals("y")) {
					option = true;
				}
			}
		}
	}

}
