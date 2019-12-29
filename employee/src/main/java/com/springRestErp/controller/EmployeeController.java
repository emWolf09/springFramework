package com.springRestErp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springRestErp.model.Address;
import com.springRestErp.model.Employee;
import com.springRestErp.model.Query;
import com.springRestErp.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	//private EmployeeService employeeService = new EmployeeServiceImpl();
	
	
	
	@GetMapping("/getEmployeeById/{employeeId}")
	public ResponseEntity<Employee> getEmployeeBygivenId(@PathVariable Integer employeeId) throws Exception{		
				try {
			Employee e = employeeService.getEmployee(employeeId);
			ResponseEntity<Employee> reply = new ResponseEntity<Employee>(e,HttpStatus.OK);
			return reply;
		} catch (Exception e) {
			//return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);		
			// new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(),e);
			throw e;
		}
	}
	

	@GetMapping("/getAllEmployee/{page}/{size}")
	public ResponseEntity<List<Employee>> getAllEmployee(@PathVariable Integer page,@PathVariable Integer size) throws Exception{
		try {
			List<Employee> e = employeeService.getAllEmployee(page, size);
			ResponseEntity<List<Employee>> reply = new ResponseEntity<List<Employee>>(e,HttpStatus.OK);
			return reply;
		} catch (Exception e) {
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(),e);
			throw e;
		}
	}
	
	//get by name
	@GetMapping("/getAllEmployeeByFirstName")
	public ResponseEntity<List<Employee>> getAllEmployeeByFirstName(@RequestBody Query query) throws Exception{
		try {
			List<Employee> e = employeeService.getAllEmployeeByFirstName(query.getQueryType(),query.getPage(),query.getSize());
			ResponseEntity<List<Employee>> reply = new ResponseEntity<List<Employee>>(e,HttpStatus.OK);
			return reply;
		} catch (Exception e) {
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(),e);
			throw e;
		}
	}
	
	
	//get by Last
	@GetMapping("/getAllEmployeeByLastName")
	public ResponseEntity<List<Employee>> getAllEmployeeByLastName(@RequestBody Query query) throws Exception{
		try {
			List<Employee> e = employeeService.getAllEmployeeByLastName(query.getQueryType(),query.getPage(),query.getSize());
			ResponseEntity<List<Employee>> reply = new ResponseEntity<List<Employee>>(e,HttpStatus.OK);
			return reply;
		} catch (Exception e) {
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage());
			throw e;
		}
	}
	
	
	//get by city
	@GetMapping("/getAllEmployeeByCity")
	public ResponseEntity<List<Employee>> getAllEmployeeByCity(@RequestBody Query query) throws Exception{
		try {
			List<Employee> e = employeeService.getAllEmployeeByCity(query.getQueryType(),query.getPage(),query.getSize());
			ResponseEntity<List<Employee>> reply = new ResponseEntity<List<Employee>>(e,HttpStatus.OK);
			return reply;
		} catch (Exception e) {
			throw e;
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(),e);
		}
	}
	
	
	
	@GetMapping("/getAllEmployeeByState")
	public ResponseEntity<List<Employee>> getAllEmployeeByState(@RequestBody Query query) throws Exception{
		try {
			List<Employee> e = employeeService.getAllEmployeeByState(query.getQueryType(),query.getPage(),query.getSize());
			ResponseEntity<List<Employee>> reply = new ResponseEntity<List<Employee>>(e,HttpStatus.OK);
			return reply;
		} catch (Exception e) {
			throw e;
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(),e);
		}
	}
	
	
	@GetMapping("/getAllEmployeeByCountry")
	public ResponseEntity<List<Employee>> getAllEmployeeByCountry(@RequestBody Query query) throws Exception{
		try {
			List<Employee> e = employeeService.getAllEmployeeByCountry(query.getQueryType(),query.getPage(),query.getSize());
			ResponseEntity<List<Employee>> reply = new ResponseEntity<List<Employee>>(e,HttpStatus.OK);
			return reply;
		} catch (Exception e) {
			throw e;
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(),e);
		}
	}
	
	@GetMapping("/getAllEmployeeByStreet")
	public ResponseEntity<List<Employee>> getAllEmployeeByStreet(@RequestBody Query query) throws Exception{
		try {
			List<Employee> e = employeeService.getAllEmployeeByStreet(query.getQueryType(),query.getPage(),query.getSize());
			ResponseEntity<List<Employee>> reply = new ResponseEntity<List<Employee>>(e,HttpStatus.OK);
			return reply;
		} catch (Exception e) {
			throw e;
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(),e);
		}
	}
	
	
	@GetMapping("/getAllEmployeeByPincode")
	public ResponseEntity<List<Employee>> getAllEmployeeByPincode(@RequestBody Query query) throws Exception{
		try {
			List<Employee> e = employeeService.getAllEmployeeByPincode(query.getQueryType(),query.getPage(),query.getSize());
			ResponseEntity<List<Employee>> reply = new ResponseEntity<List<Employee>>(e,HttpStatus.OK);
			return reply;
		} catch (Exception e) {
			throw e;
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(),e);
		}
	}
	
	@PostMapping("/addNewEmployee")
	public ResponseEntity<Integer> addNewEmployee(@RequestBody Employee e) throws Exception{
		try{
			Integer employeeId = employeeService.addEmployee(e);
			ResponseEntity<Integer> reply = new ResponseEntity<>(employeeId,HttpStatus.OK);
			return reply;
		}catch (Exception ex) {
			throw ex;
			//throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,	ex.getMessage(),ex);
		}
		
	}
	
	
	@PostMapping("/addNewAddressToExistingEmployee/{employeeId}")
	public ResponseEntity<Integer> addNewAddressToExistingEmployee(@PathVariable Integer employeeId,@RequestBody Address a) throws Exception{
		try{
			Integer addressId = employeeService.addNewAddressToExistingEmployee(employeeId, a);
			ResponseEntity<Integer> reply = new ResponseEntity<>(addressId,HttpStatus.OK);
			return reply;
		}catch (Exception ex) {
			throw ex;
//			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,	ex.getMessage(),ex);
		}
		
	}
	
	
	@PostMapping("/updateEmployeeDetails")
	public ResponseEntity<Integer> updateEmployeeDetails(@RequestBody Employee e) throws Exception{
		try{
			Integer employeeId = employeeService.updateEmployeeDetails(e);
			ResponseEntity<Integer> reply = new ResponseEntity<>(employeeId,HttpStatus.OK);
			return reply;
		}catch (Exception ex) {
			throw ex;
//			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,	ex.getMessage(),ex);
		}
		
	}
	
	@PostMapping("/updateAddressDetails")
	public ResponseEntity<Integer> updateAddressDetails(@RequestBody Address e) throws Exception{
		try{
			Integer addressId = employeeService.updateAddressDetails(e);
			ResponseEntity<Integer> reply = new ResponseEntity<>(addressId,HttpStatus.OK);
			return reply;
		}catch (Exception ex) {
			throw ex;
//			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,	ex.getMessage(),ex);
		}
		
	}
	
	
	@PostMapping("/deleteEmployee/{employeeId}")
	public ResponseEntity<Integer> deleteEmployee(@PathVariable Integer employeeId) throws Exception{
		try{
			Integer id = employeeService.deleteEmployee(employeeId);
			ResponseEntity<Integer> reply = new ResponseEntity<>(id,HttpStatus.OK);
			return reply;
		}catch (Exception ex) {
			throw ex;
//			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,	ex.getMessage(),ex);
		}
		
	}
	
	
	@PostMapping("/deleteAddress/{addressId}")
	public ResponseEntity<Integer> deleteAddress(@PathVariable Integer addressId) throws Exception{
		try{
			Integer id = employeeService.deleteAddress(addressId);
			ResponseEntity<Integer> reply = new ResponseEntity<>(id,HttpStatus.OK);
			return reply;
		}catch (Exception ex) {
			throw ex;
//			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,	ex.getMessage(),ex);
		}
		
	}
	
	
	
	
}
