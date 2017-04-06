package com.training.control;

import com.training.i18n.LanguageOption;

public class Welcome implements Command {

	@Override
	public void execute() {
		LanguageOption.chooseLanguage();
		System.out.println(LanguageOption.getOption("introduction"));
		new Menu().execute();
	}

}
