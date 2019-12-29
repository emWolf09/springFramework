package com.springRestErp.CustomException;

public class NoAddressFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoAddressFoundException() {
		super("CustomException.NoAddressFound");
	}
	
	public NoAddressFoundException(String msg) {
		super(msg);
	}
	
}