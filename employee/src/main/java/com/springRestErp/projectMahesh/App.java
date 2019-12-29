package com.springRestErp.projectMahesh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.PropertySource;

import com.springRestErp.dao.EmployeeDao;
import com.springRestErp.dao.EmployeeDaoImpl;
import com.springRestErp.model.Address;
import com.springRestErp.model.Employee;


/**
 * Hello world!
 *
 */

@PropertySource("classpath:message.properties")
public class App 
{
//	@Autowired
//	@Qualifier("employeeService1")
//	public static EmployeeService employeeService;
	public static EmployeeDao employeeService= new EmployeeDaoImpl();
    
	public static void main( String[] args )
    {
		getEmployee(1006);
		getAllEmployee(1,3);
		addNewEmployee();
		addNewAddressToExistingEmployee();
		updateEmployeeDetails();
		updateAddressDetails();
		deleteAddress();
		deleteEmployee();
		getAllEmployeeByCity();
		getAllEmployeeByState();
		getAllEmployeeByCountry();
		getAllEmployeeByPincode();
		getAllEmployeeByStreet();
		getAllEmployeeByFirstName();
		getAllEmployeeByLastName();
		
//		try{throw new Exception("SUCCESS.ADDED_EMPLOYEE");}
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
    }
	
	private static void getAllEmployeeByFirstName() {
		try {
			List<Employee> employee = employeeService.getAllEmployeeByFirstName("ra",1,2);
			for(Employee e: employee)Employee.printEmployee(e);
		} catch (Exception e) {
//			e.printStackTrace();
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
		
	}

	private static void getAllEmployeeByLastName() {
		try {
			List<Employee> employee = employeeService.getAllEmployeeByLastName("ogi",1,2);
			for(Employee e: employee)Employee.printEmployee(e);
		} catch (Exception e) {
//			e.printStackTrace();
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
		
	}
	private static void getAllEmployeeByStreet() {
		try {
			List<Employee> employee = employeeService.getAllEmployeeByStreet("neeladri",1,2);
			for(Employee e: employee)Employee.printEmployee(e);
		} catch (Exception e) {
//			e.printStackTrace();
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
	}

	private static void getAllEmployeeByPincode() {
		try {
			List<Employee> employee = employeeService.getAllEmployeeByPincode("302017",1,3);
			for(Employee e: employee)Employee.printEmployee(e);
		} catch (Exception e) {
//			e.printStackTrace();
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
	}

	private static void getAllEmployeeByCountry() {
		try {
			List<Employee> employee = employeeService.getAllEmployeeByCountry("India",1,6);
			for(Employee e: employee)Employee.printEmployee(e);
		} catch (Exception e) {
//			e.printStackTrace();
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
	}

	private static void getAllEmployeeByState() {
		try {
			List<Employee> employee = employeeService.getAllEmployeeByState("Rajasthan",1,2);
			for(Employee e: employee)Employee.printEmployee(e);
		} catch (Exception e) {
//			e.printStackTrace();
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static void getAllEmployeeByCity() {
		try {
			List<Employee> employee = employeeService.getAllEmployeeByCity("Bangalore",1,2);
			for(Employee e: employee)Employee.printEmployee(e);
		} catch (Exception e) {
//			e.printStackTrace();
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
	}



	private static void deleteEmployee() {
		try {
			System.out.println(employeeService.deleteEmployee(1005)+" employee deleted");
		} catch (Exception e) {
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
		
	}



	private static void deleteAddress() {
		try {
			System.out.println(employeeService.deleteAddress(5002)+" address deleted");
		} catch (Exception e) {
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
		
	}



	private static void updateAddressDetails() {
		try {
			Address a = new Address();
			a.setAddressId(5005);
			a.setCity("RK puram");
			a.setState("Rajasthan");
			a.setStreet("RK puram");
			a.setCountry("India");
			a.setPincode("302017");
			System.out.println(employeeService.updateAddressDetails(a));
		} catch (Exception e) {
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
		
	}



	private static void updateEmployeeDetails() {
		try {
				Employee e = employeeService.getEmployee(1003);
				e.setFirstName("Jitu");
				employeeService.updateEmployeeDetails(e);
		} catch (Exception e) {
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
	}



	public static void addNewAddressToExistingEmployee(){
		try {
				Address a = new Address();
				a.setCity("RK puram");
				a.setState("Rajasthan");
				a.setStreet("RK puram");
				a.setCountry("India");
				a.setPincode("302017");
				employeeService.addNewAddressToExistingEmployee(1004,a);
		} catch (Exception e) {
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	
	public static void addNewEmployee(){
		try {
			Employee employee = new Employee();
			employee.setFirstName("Mayank");
			employee.setLastName("Max");
			
			List<Address> addressList= new ArrayList<Address>();
			Address a = new Address();
			a.setCity("Kobra");
			a.setState("Rajasthan");
			a.setStreet("kabra");
			a.setCountry("India");
			a.setPincode("302017");
			addressList.add(a);
			Address a1 = new Address();
			a1.setCity("Kota");
			a1.setState("Rajasthan");
			a1.setStreet("Kota");
			a1.setCountry("India");
			a1.setPincode("302017");
			addressList.add(a1);
			employee.setAddressSet(addressList);
			System.out.println(employee.getAddressSet().size()+"is the address of bica");
			Integer employeeId = employeeService.addEmployee(employee);
			System.out.println(employeeId);
		} catch (Exception e) {
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	

	public static void getAllEmployee(Integer page,Integer size){
		try {
			List<Employee> employee = employeeService.getAllEmployee(page,size);
			for(Employee e: employee)Employee.printEmployee(e);
		} catch (Exception e) {
//			e.printStackTrace();
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void getEmployee(Integer employeeId){
		try {
			Employee employee = employeeService.getEmployee(employeeId);
			Employee.printEmployee(employee);
		} catch (Exception e) {
//			e.printStackTrace();
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
		
		try {
			Employee employee = employeeService.getEmployee(employeeId);
			Employee.printEmployee(employee);
		} catch (Exception e) {
//			e.printStackTrace();
			if (e.getMessage() != null)
			{
				System.out.println(e.getMessage());
			}
		}
	}
}
