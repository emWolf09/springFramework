package com.springRestErp.CustomException;



public class NoEmployeeFoundException extends Exception{
	private static final long serialVersionUID = 1L;	
	public NoEmployeeFoundException() {
		super("CustomException.NoEmoloyeeFound");
		System.out.println("error is -------------------------------->"+this.getMessage());
	}
	
	public NoEmployeeFoundException(String msg) {
		super(msg);
		System.out.println("error is -------------------------------->"+this.getMessage());
	}
	
}
