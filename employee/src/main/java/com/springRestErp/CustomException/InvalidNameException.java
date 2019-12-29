package com.springRestErp.CustomException;


public class InvalidNameException extends Exception {
	private static final long serialVersionUID = 1L;
	public InvalidNameException() {
		super("Validation.InvalidName");
	}
	public InvalidNameException(String msg) {
		super(msg);
	}
}
