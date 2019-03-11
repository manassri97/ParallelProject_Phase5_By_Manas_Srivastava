package com.capgemini.exceptions;

public class InsufficientWalletBalanceException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientWalletBalanceException(String string) {
		super(string);
	}

}
