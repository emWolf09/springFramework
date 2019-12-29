package com.springRestErp.employeeTest;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springRestErp.dao.EmployeeDao;
import com.springRestErp.dao.EmployeeDaoImpl;
import com.springRestErp.model.Address;
import com.springRestErp.model.Employee;
import com.springRestErp.service.EmployeeService;
import com.springRestErp.service.EmployeeServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ServiceLayerTest {
	
	@Mock
	EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	@InjectMocks
	EmployeeService employeeService = new EmployeeServiceImpl();
	
	
	@Rule
	public ExpectedException e = ExpectedException.none();
	
	@Autowired
	Environment environment;
	
	@Test
	public void addEmployeeTest() throws Exception{
		String exceptionMsg = environment.getProperty("CustomException.InsertingEmployeeFailed");
		System.out.println(exceptionMsg);
		e.expect(Exception.class);
		
		Employee e = new Employee();
		e.setFirstName("Ram");
		e.setLastName("Rama");
		e.setAddressSet(new ArrayList<Address>());
		
		Mockito.when(employeeDao.addEmployee(Mockito.any())).thenReturn(null);
		
		employeeService.addEmployee(e);
		
		
	}
	
	
	
	@Configuration
	@PropertySource("classpath:application.properties")
	@PropertySource("classpath:message.properties")
    static class AccountServiceTestContextConfiguration {
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		    return new PropertySourcesPlaceholderConfigurer();
		}
}
}
