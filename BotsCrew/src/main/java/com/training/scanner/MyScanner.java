package com.training.scanner;

import java.util.Scanner;

public class MyScanner {
	private Scanner scn;
	private static MyScanner instance = null;

	private MyScanner() {
		scn = new Scanner(System.in);
	}

	public String readLine() {
		return scn.nextLine();
	}

	public static MyScanner getInstance() {
		if (instance == null)
			instance = new MyScanner();
		return instance;
	}
}
