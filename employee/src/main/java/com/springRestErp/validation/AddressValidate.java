package com.springRestErp.validation;

import com.springRestErp.CustomException.InvalidAddressException;
import com.springRestErp.model.Employee;

public class AddressValidate {
public static void Validate(Employee emp) throws Exception{
		
		try{
			if(validateStreet(emp.getFirstName())==false)throw new InvalidAddressException();
			if(validateCity(emp.getLastName())==false)throw new InvalidAddressException();
		}catch (Exception e) {
			throw e;
		}
		
	}
	
	public static Boolean validateStreet(String firstName){
//		String reg = "[A-Za-z0-9 ]{*}";
//		if(firstName.matches(reg))return true;
//		else 
		return true;
	}
	
	public static Boolean validateCity(String firstName){
//		String reg = "[A-Za-z0-9 ]{*}";
//		if(firstName.matches(reg))return true;
//		else 
		return true;
	}
}
