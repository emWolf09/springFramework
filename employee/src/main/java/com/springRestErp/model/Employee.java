package com.springRestErp.model;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

import com.springRestErp.entity.AddressEntity;
import com.springRestErp.entity.EmployeeEntity;

public class Employee {

	private Integer employeeId;
	private String firstName;
	private String lastName;
	private List<Address> addressSet;
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Address> getAddressSet() {
		return addressSet;
	}
	public void setAddressSet(List<Address> addressSet) {
		this.addressSet = addressSet;
	}
	
	
	public static void printEmployee(Employee employee) {
		System.out.println("Start of employee details--->");
		System.out.println("Customer id : " + employee.getEmployeeId());
//		System.out.println("Customer first name : " + employee.getFirstName());
//		System.out.println("Customer last name: " + employee.getLastName());
//		List<Address> aSet = employee.getAddressSet();
//		System.out.println("total address are "+aSet.size());
//		for(Address a : aSet){
//			Address.printAddress(a);
//		}
//		System.out.println("End of employee details--->");
	}
	public static EmployeeEntity converFromModelToEntity(Employee e) {
		EmployeeEntity em = new DozerBeanMapper().map(e,EmployeeEntity.class);
		List<AddressEntity> aSet = new ArrayList<AddressEntity>();
		List<Address> aList = e.getAddressSet();
		for(Address a:aList){
			AddressEntity ad  = Address.converFromModelToEntity(a);
			ad.setEmp_id(e.getEmployeeId());
			aSet.add(ad);
		}
		em.setAddressEntities(aSet);
		return em;
	}
	
	public static Employee convertFromEntity(EmployeeEntity e){
		Employee em = new DozerBeanMapper().map(e,Employee.class);
		List<Address> aSet = new ArrayList<Address>();
		List<AddressEntity> aList = e.getAddressEntities();
		
		aList.parallelStream().forEach(
			(addressEntity)->{
				aSet.add(Address.converFromEntity(addressEntity));
			}
		);
		em.setAddressSet(aSet);
		return em;
	}
	
}
