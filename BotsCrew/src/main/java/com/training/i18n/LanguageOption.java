package com.training.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.training.scanner.MyScanner;

public class LanguageOption {

	private static Locale choosenLanguage = null;
	private static ResourceBundle message;
	private static int choosen;

	public static int getChoosenLanguage() {
		return choosen;
	}

	public static String getOption(String key) {
		String answer = "Error";
		try {
			answer = new String(message.getString(key).getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return answer;

	}

	public static void chooseLanguage() {
		System.out.println("1 - English\t2 - Українська\t");
		String option = MyScanner.getInstance().readLine();
		if (option.equals("1")) {
			choosen = 1;
			choosenLanguage = new Locale("en");
			message = ResourceBundle.getBundle("com.training.i18n.message", choosenLanguage);
		} else if (option.equals("2")) {
			choosen = 2;
			choosenLanguage = new Locale("uk");
			message = ResourceBundle.getBundle("com.training.i18n.message", choosenLanguage);
		} else
			chooseLanguage();
	}
}
