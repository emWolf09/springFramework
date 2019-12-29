package com.springRestErp.service;

import java.util.List;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.springRestErp.CustomException.InsertingAddressFailedException;
import com.springRestErp.CustomException.InsertingEmployeeFaildedException;
import com.springRestErp.CustomException.NoAddressFoundException;
import com.springRestErp.CustomException.NoEmployeeFoundException;
import com.springRestErp.dao.EmployeeDao;
import com.springRestErp.model.Address;
import com.springRestErp.model.Employee;





@Service("employeeService1")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	

	@Autowired
	Environment environment;
	
	@Autowired
	@Qualifier("employeeDao1")
	private EmployeeDao employeeDao ;
	Logger logger = Logger.getLogger(this.getClass());
	
	
	public Integer addEmployee(Employee employee) throws Exception {
		
		try{
			String SuccessMsg = environment.getProperty("SUCCESS.ADDED_EMPLOYEE");
			String exceptionMsg = environment.getProperty("CustomException.InsertingEmployeeFailed");
			Integer id =  employeeDao.addEmployee(employee);
			if(id == null){
				throw new InsertingEmployeeFaildedException(exceptionMsg);
			}
			logger.info(SuccessMsg+id);
			return id;
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public Integer addNewAddressToExistingEmployee(Integer employeeId, Address address) throws Exception {
		try {
			String SuccessMsg = environment.getProperty("SUCCESS.ADDED_ADDRESS");
			String exceptionMsg = environment.getProperty("CustomException.NoEmoloyeeFound");
			String exceptionMsg1 = environment.getProperty("CustomException.InsertingAddressFailed");
			Integer addressId = employeeDao.addNewAddressToExistingEmployee(employeeId, address);
			if(addressId == -1){
				throw new NoEmployeeFoundException(exceptionMsg);
			}else if(addressId==0){
				throw new InsertingAddressFailedException(exceptionMsg1);
			}
			logger.info(SuccessMsg+addressId);
			return addressId;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}

	}
	
	
	
	@Cacheable(value="employeeCache" , key="#employeeId")
	public Employee getEmployee(Integer employeeId) throws Exception {
		try {
			String exceptionMsg = environment.getProperty("CustomException.NoEmoloyeeFound");
			Employee e = employeeDao.getEmployee(employeeId);
			if(e == null){
				throw new NoEmployeeFoundException(exceptionMsg);
			}
			return e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		
	}

	@Cacheable(value="employeeCache")
	public List<Employee> getAllEmployee(Integer page,Integer size) throws Exception {
		
		try {
			String exceptionMsg = environment.getProperty("CustomException.NoEmoloyeeFound");
			page = getPageId(page, size);
			List<Employee> employees= employeeDao.getAllEmployee(page, size);
			if(employees.isEmpty()){
				throw new NoEmployeeFoundException(exceptionMsg);
			}
			return employees;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		
	}

	@Cacheable(value="employeeCache" , key="#City")
	public List<Employee> getAllEmployeeByCity(String City, int page, int size)throws Exception{
		
		try {
			String exceptionMsg = environment.getProperty("CustomException.NoEmoloyeeFound");
			page = getPageId(page, size);
			List<Employee> employees= employeeDao.getAllEmployeeByCity(City,page, size);
			if(employees.isEmpty()){
				throw new NoEmployeeFoundException(exceptionMsg);
			}
			return employees;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		
	}

	@Cacheable(value="employeeCache" , key="#State")
	public List<Employee> getAllEmployeeByState(String State, Integer page, Integer size) throws Exception {
		try {
			page = getPageId(page, size);
			List<Employee> employees= employeeDao.getAllEmployeeByState(State,page, size);
			if(employees.isEmpty()){
				throw new NoEmployeeFoundException();
			}
			return employees;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Cacheable(value="employeeCache" , key="#input")
	public List<Employee> getAllEmployeeByCountry(String input, Integer page, Integer size) throws Exception {
		try {
			page = getPageId(page, size);
			List<Employee> employees= employeeDao.getAllEmployeeByCountry(input,page, size);
			if(employees.isEmpty()){
				throw new NoEmployeeFoundException();
			}
			return employees;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}	
	}
	@Cacheable(value="employeeCache" , key="#input")
	public List<Employee> getAllEmployeeByStreet(String input, Integer page, Integer size) throws Exception {
		try {
			page = getPageId(page, size);
			List<Employee> employees= employeeDao.getAllEmployeeByStreet(input,page, size);
			if(employees.isEmpty()){
				throw new NoEmployeeFoundException();
			}
			return employees;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}	
	}

	@Cacheable(value="employeeCache" , key="#input")
	public List<Employee> getAllEmployeeByPincode(String input, Integer page, Integer size) throws Exception {
		try {
			page = getPageId(page, size);
			List<Employee> employees= employeeDao.getAllEmployeeByPincode(input,page, size);
			if(employees.isEmpty()){
				throw new NoEmployeeFoundException();
			}
			return employees;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}	
	}
	
	
	public List<Employee> getAllEmployeeByFirstName(String input, Integer page, Integer size) throws Exception {
		try {
			page = getPageId(page, size);
			List<Employee> employees= employeeDao.getAllEmployeeByFirstName(input,page, size);
			if(employees.isEmpty()){
				throw new NoEmployeeFoundException();
			}
			return employees;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}	
	}

	public List<Employee> getAllEmployeeByLastName(String input, Integer page, Integer size) throws Exception {
		try {
			page = getPageId(page, size);
			List<Employee> employees= employeeDao.getAllEmployeeByLastName(input,page, size);
			if(employees.isEmpty()){
				throw new NoEmployeeFoundException();
			}
			return employees;	
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}	
	}
	
	@CachePut(value = "employeeCache" , key = "#employee.employeeId")
	public Integer updateEmployeeDetails(Employee employee) throws Exception {
		try {
			Integer id = employeeDao.updateEmployeeDetails(employee);
			if(id==-1){
				throw new NoEmployeeFoundException();
			}
			logger.info("SUCCESS.UPDATED_Employee"+id);
			return id;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}


	@CachePut(value = "addressCache", key = "#address.addressId")
	public Integer updateAddressDetails(Address address) throws Exception {
		try {
			Integer id = employeeDao.updateAddressDetails(address);
			if(id==-1){
				throw new NoAddressFoundException();
			}
			logger.info("SUCCESS.UPDATED_ADDRESS"+id);
			return id;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@CacheEvict(value = "employeeCache" , key = "#employeeId")
	public Integer deleteEmployee(Integer employeeId) throws Exception {
		try {
			Integer id  = employeeDao.deleteEmployee(employeeId);
			if(id==-1){
				throw new NoEmployeeFoundException();
			}
			logger.info("SUCCESS.DELETED_EMPLOYEE"+id);
			return id;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	@CacheEvict(value = "addressCache", key = "#addressId")
	public Integer deleteAddress(Integer addressId) throws Exception {
		try {
			Integer id  = employeeDao.deleteAddress(addressId);
			if(id==-1){
				throw new NoAddressFoundException();
			}
			logger.info("SUCCESS.DELETED_ADDRESS"+id);
			return id;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	public static Integer getPageId(Integer page,Integer size){
		Integer pageId = page;
		if(pageId==1){}    
        else{    
            pageId=(pageId-1)*size+1;    
        }
		
		return pageId;
	}


}
