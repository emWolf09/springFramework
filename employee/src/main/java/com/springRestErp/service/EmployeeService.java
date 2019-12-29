package com.springRestErp.service;

import java.util.List;

import com.springRestErp.model.Address;
import com.springRestErp.model.Employee;

public interface EmployeeService {
		//add employee
		public Integer addEmployee(Employee employee) throws Exception;
		
		//add address to existing employee
		public Integer addNewAddressToExistingEmployee(Integer employeeId, Address address)throws Exception;
		
		//get all employee
		public List<Employee> getAllEmployee(Integer page,Integer size) throws Exception;
		
		//get a employee by given id 
		public Employee getEmployee(Integer employeeId) throws Exception;
		
		//update employee
		public Integer updateEmployeeDetails(Employee employee) throws Exception;
		
		//update address details of employee
		public Integer updateAddressDetails(Address address) throws Exception;
		//for this we can first delete the existing address for that employee
	
		//delete employee
		public Integer deleteEmployee(Integer employeeId) throws Exception;
		//delete address
		public Integer deleteAddress(Integer addressId) throws Exception;

		public List<Employee> getAllEmployeeByCity(String City, int page, int size) throws Exception;
		
		public List<Employee> getAllEmployeeByState(String State,Integer page,Integer size) throws Exception;

		public List<Employee> getAllEmployeeByCountry(String input,Integer page,Integer size) throws Exception;
		
		public List<Employee> getAllEmployeeByStreet(String input,Integer page,Integer size) throws Exception;
		
		public List<Employee> getAllEmployeeByPincode(String input,Integer page,Integer size) throws Exception;
		
		public List<Employee> getAllEmployeeByFirstName(String input,Integer page,Integer size) throws Exception;
		
		public List<Employee> getAllEmployeeByLastName(String input,Integer page,Integer size) throws Exception;
}
