package com.training.service;

public class FactoryMethod {

	private static Factory instance = null;

	public static Factory getInstance() {
		if (instance == null)
			instance = new Factory();
		return instance;
	}

}
