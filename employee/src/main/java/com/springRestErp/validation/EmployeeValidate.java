package com.springRestErp.validation;

import com.springRestErp.CustomException.InvalidNameException;
import com.springRestErp.model.Employee;


public class EmployeeValidate {
	
	
	public static void Validate(Employee emp) throws Exception{
		
		try{
			if(validateFirstName(emp.getFirstName())==false)throw new InvalidNameException();
			if(validateLastName(emp.getLastName())==false)throw new InvalidNameException();
		}catch (Exception e) {
			throw e;
		}
		
	}
	
	public static Boolean validateFirstName(String firstName){
//		String reg = "[A-Za-z0-9 ]{*}";
//		if(firstName.matches(reg))return true;
		return true;
	}
	
	public static Boolean validateLastName(String firstName){
//		String reg = "[A-Za-z0-9 ]{*}";
//		if(firstName.matches(reg))return true;
		return true;
	}
}
