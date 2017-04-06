package com.training.control;

import java.util.HashMap;
import java.util.Map;

import com.training.i18n.LanguageOption;
import com.training.scanner.MyScanner;

public class Menu implements Command {

	MyScanner scn = MyScanner.getInstance();

	@Override
	public void execute() {
		Map<String, Command> command = new HashMap<>();
		command.put("1", new BookMenu());
		command.put("2", new AddBookMenu());
		command.put("3", new EditBookMenu());
		command.put("4", new LanguageMenu());
		command.put("5", () -> {
			System.out.println(LanguageOption.getOption("menu_exit"));
			System.exit(0);
		});
		while (true) {
			System.out.println("1-" + LanguageOption.getOption("menu_option1") + ", 2-" + LanguageOption.getOption("menu_option2") + ", 3-"
					+ LanguageOption.getOption("menu_option3") + ", 4-" + LanguageOption.getOption("menu_option4") + ", 5-"
					+ LanguageOption.getOption("menu_option5"));
			String choice = scn.readLine();
			if (command.get(choice) != null) {
				command.get(choice).execute();
			} else
				System.out.println(LanguageOption.getOption("error"));

		}
	}
}
