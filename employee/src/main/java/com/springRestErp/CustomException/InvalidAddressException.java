package com.springRestErp.CustomException;

public class InvalidAddressException extends Exception{
	private static final long serialVersionUID = 1L;
	public InvalidAddressException() {
		super("Validation.InvalidName");
	}
	public InvalidAddressException(String msg) {
		super(msg);
	}
}
