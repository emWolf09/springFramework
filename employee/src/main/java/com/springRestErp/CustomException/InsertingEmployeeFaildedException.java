package com.springRestErp.CustomException;

public class InsertingEmployeeFaildedException extends Exception{
	private static final long serialVersionUID = 1L;
	public InsertingEmployeeFaildedException() {
		super("CustomException.InsertingEmployeeFailed");
	}
	
	public InsertingEmployeeFaildedException(String msg) {
		super(msg);
	}
}
