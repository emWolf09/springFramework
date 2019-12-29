package com.springRestErp.CustomException;

public class InsertingAddressFailedException extends Exception {
	private static final long serialVersionUID = 1L;
	public InsertingAddressFailedException() {
		super("CustomException.InsertingAddressFailed");
	}
	
	public InsertingAddressFailedException(String msg) {
		super(msg);
		System.out.println(this.getMessage());
	}
}
