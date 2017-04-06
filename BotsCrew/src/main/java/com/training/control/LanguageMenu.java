package com.training.control;

import com.training.i18n.LanguageOption;

public class LanguageMenu implements Command {

	@Override
	public void execute() {
		LanguageOption.chooseLanguage();
	}

}
