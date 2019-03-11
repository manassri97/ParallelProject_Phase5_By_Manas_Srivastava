package com.capgemini.exceptions;

public class WalletAccountDoesNotExist extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WalletAccountDoesNotExist(String string) {
		super(string);
	}

}
