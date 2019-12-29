package com.springRestErp.dao;

import java.util.ArrayList;
import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.springRestErp.entity.AddressEntity;
import com.springRestErp.entity.EmployeeEntity;
import com.springRestErp.model.Address;
import com.springRestErp.model.Employee;




@Repository(value = "employeeDao1")
public class EmployeeDaoImpl implements EmployeeDao {
	private static Integer hitCount = 0;
	static{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(EntityManagerFactory emf,EntityManager em){
		if(em!=null && em.isOpen()){
			em.close();
		}
		if(emf!=null && emf.isOpen()){
			emf.close();
		}
	}
	
	
	public Integer addEmployee(Employee employee) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		Integer newEmployeeId = null;
		try {
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			EmployeeEntity employeeEntity = Employee.converFromModelToEntity(employee);
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(employeeEntity);
			newEmployeeId = employeeEntity.getEmployeeId();
			et.commit();
			return newEmployeeId;
		} catch (Exception e) {
			throw e;
		}finally {
			close(emf,em);
		}
	}

	public Integer addNewAddressToExistingEmployee(Integer employeeId, Address address) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		try {
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			AddressEntity addressEntity = Address.converFromModelToEntity(address);
			EmployeeEntity employeeEntity =null;
			employeeEntity= em.find(EmployeeEntity.class, employeeId);
			
			if(employeeEntity!=null){
				List<AddressEntity> addressSet= employeeEntity.getAddressEntities();
				addressSet.add(addressEntity);
			}else{
				return -1; //-1 for employee not found
			}
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(employeeEntity);
			employeeEntity.getEmployeeId();
			et.commit();
			List<AddressEntity> addressSet= employeeEntity.getAddressEntities();
			Integer addressId = 0;
			if(addressSet.size()>1)
			addressId = (addressSet.get(addressSet.size()-1)).getAddressID();
			return addressId;
		} catch (Exception e) {
			throw e;
		}finally {
			close(emf,em);
		}
	}

	public List<Employee> getAllEmployee(Integer pageId,Integer size) throws Exception {
		String sql="select e from EmployeeEntity e"; 
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		try {
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			Query query = em.createQuery(sql);
			
			@SuppressWarnings("unchecked")
			List<EmployeeEntity> results = query.setFirstResult(pageId-1).setMaxResults(size).getResultList();
			if(results.size()>0){
				List<Employee> reply = new ArrayList<Employee>();
				
				results.parallelStream().forEach(
						(employeeEntity)->{
							reply.add(Employee.convertFromEntity(employeeEntity));
						}
				);
				return reply;
			}else{
				return new ArrayList<Employee>(); //this will be empty handle in service
			}
		} catch(Exception e){
			throw e;
		}
		finally {
			close(emf,em);
		}
	}

	
	
	public Employee getEmployee(Integer employeeId) throws Exception {
		
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		Employee emp = null;
		try{
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			EmployeeEntity employeeEntity = em.find(EmployeeEntity.class,employeeId);
			if(employeeEntity!=null){
				emp = Employee.convertFromEntity(employeeEntity);
				return emp;
			}else{
				return null;
			}
		}catch (Exception e) {
			throw e;
		}finally{
			close(emf,em);
		}
	}

	
	
	
	public Integer updateEmployeeDetails(Employee employee) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		
		try{
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			EmployeeEntity employeeEntity = null;
			employeeEntity =  em.find(EmployeeEntity.class,employee.getEmployeeId());
			
			if(employeeEntity!=null){
				employeeEntity.setFirstName(employee.getFirstName());
				employeeEntity.setLastName(employee.getLastName());
				EntityTransaction et = em.getTransaction();
				et.begin();
				em.persist(employeeEntity);
				employeeEntity.getEmployeeId();
				et.commit();
				return employeeEntity.getEmployeeId();
			}else{
				return -1; //no employee found
			}
		}catch (Exception e) {
			throw e;
		}finally{
			close(emf,em);
		}
	}

	public Integer updateAddressDetails(Address address) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		
		try{
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			AddressEntity addressEntity = null;
			addressEntity =  em.find(AddressEntity.class,address.getAddressId());
			
			if(addressEntity!=null){
				addressEntity.setCity(address.getCity());
				addressEntity.setCountry(address.getCountry());
				addressEntity.setPincode(address.getPincode());
				addressEntity.setState(address.getState());
				addressEntity.setStreet(address.getStreet());
				EntityTransaction et = em.getTransaction();
				et.begin();
				em.persist(addressEntity);
				et.commit();
				return addressEntity.getAddressID();
			}else{
				return -1; //no address found
			}
		}catch (Exception e) {
			throw e;
		}finally{
			close(emf,em);
		}
	}

	public Integer deleteEmployee(Integer employeeId) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		
		try{
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			EmployeeEntity employeeEntity = null;
			employeeEntity =  em.find(EmployeeEntity.class,employeeId);
			
			if(employeeEntity!=null){
				EntityTransaction et = em.getTransaction();
				et.begin();
				em.remove(employeeEntity);
				et.commit();
				return employeeEntity.getEmployeeId();
			}else{
				return -1;
			}
		}catch (Exception e) {
			throw e;
		}finally{
			close(emf,em);
		}
	}

	public Integer deleteAddress(Integer addressId) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		try{
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			AddressEntity addressEntity = null;
			addressEntity =  em.find(AddressEntity.class,addressId);
			
			if(addressEntity!=null){
				EntityTransaction et = em.getTransaction();
				et.begin();
				em.remove(addressEntity);
				et.commit();
				return addressEntity.getAddressID();
			}else{
				return -1;
			}
		}finally{
			close(emf,em);
		}
	}

	public List<Employee> getAllEmployeeByCity(String City, Integer pageId, Integer size) throws Exception {
		String sql="select e from EmployeeEntity e where e.employeeId in (select a.emp_id from AddressEntity a where a.city = :inputCity)";
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		try {
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			Query query = em.createQuery(sql);
			query.setParameter("inputCity",City);
			@SuppressWarnings("unchecked")
			List<EmployeeEntity> results = query.setFirstResult(pageId-1).setMaxResults(size).getResultList();
			if(results.size()>0){
				List<Employee> reply = new ArrayList<Employee>();
				results.parallelStream().forEach(
						(employeeEntity)->{
							reply.add(Employee.convertFromEntity(employeeEntity));
						}
				);
				return reply;
			}else{
				return new ArrayList<Employee>(); //empty result;
			}
		}finally {
			close(emf,em);
		}
	}

	
	public List<Employee> getAllEmployeeByState(String State,Integer pageId,Integer size) throws Exception{		
		String sql="select e from EmployeeEntity e where e.employeeId in (select a.emp_id from AddressEntity a where a.state = :input)";
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		try {
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			Query query = em.createQuery(sql);
			query.setParameter("input",State);
			@SuppressWarnings("unchecked")
			List<EmployeeEntity> results = query.setFirstResult(pageId-1).setMaxResults(size).getResultList();
			if(results.size()>0){
				List<Employee> reply = new ArrayList<Employee>();
				results.parallelStream().forEach(
						(employeeEntity)->{
							reply.add(Employee.convertFromEntity(employeeEntity));
						}
				);
				return reply;
			}else{
				return new ArrayList<Employee>();
			}
		} 
		finally {
			close(emf,em);
		}
	}

	public List<Employee> getAllEmployeeByCountry(String input, Integer pageId, Integer size) throws Exception {
		String sql="select e from EmployeeEntity e where e.employeeId in (select a.emp_id from AddressEntity a where a.country = :input)";
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		try {
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			Query query = em.createQuery(sql);
			query.setParameter("input",input);
			@SuppressWarnings("unchecked")
			List<EmployeeEntity> results = query.setFirstResult(pageId-1).setMaxResults(size).getResultList();
			if(results.size()>0){
				List<Employee> reply = new ArrayList<Employee>();
				results.parallelStream().forEach(
						(employeeEntity)->{
							reply.add(Employee.convertFromEntity(employeeEntity));
						}
				);
				return reply;
			}else{
				return new ArrayList<Employee>();
			}
		}
		finally {
			close(emf,em);
		}
	}

	public List<Employee> getAllEmployeeByStreet(String input1, Integer pageId, Integer size) throws Exception {
		String input = "%"+input1+"%";
		String sql="select e from EmployeeEntity e where e.employeeId in (select a.emp_id from AddressEntity a where a.street LIKE :input)";
		
		//+(pageId-1)+","+size;  
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		try {
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			Query query = em.createQuery(sql);
			query.setParameter("input",input);
			@SuppressWarnings("unchecked")
			List<EmployeeEntity> results = query.setFirstResult(pageId-1).setMaxResults(size).getResultList();
			if(results.size()>0){
				List<Employee> reply = new ArrayList<Employee>();
				results.parallelStream().forEach(
						(employeeEntity)->{
							reply.add(Employee.convertFromEntity(employeeEntity));
						}
				);
				return reply;
			}else{
				return new ArrayList<Employee>();
			}
	
		}finally {
			close(emf,em);
		}
	}

	public List<Employee> getAllEmployeeByPincode(String input, Integer pageId, Integer size) throws Exception {
		System.out.println("Database hit***********>>>"+hitCount);
		hitCount+=1;
		String sql="select e from EmployeeEntity e where e.employeeId in (select a.emp_id from AddressEntity a where a.pincode = :input)";
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		try {
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			Query query = em.createQuery(sql);
			query.setParameter("input",input);
			@SuppressWarnings("unchecked")
			List<EmployeeEntity> results = query.setFirstResult(pageId-1).setMaxResults(size).getResultList();
			if(results.size()>0){
				List<Employee> reply = new ArrayList<Employee>();
				results.parallelStream().forEach(
						(employeeEntity)->{
							reply.add(Employee.convertFromEntity(employeeEntity));
						}
				);
				return reply;
			}else{
				return new ArrayList<Employee>();
			}
		}
		finally {
			close(emf,em);
		}
	}

	public List<Employee> getAllEmployeeByFirstName(String input1, Integer pageId, Integer size) throws Exception {
		
		String input = "%"+input1+"%";
		String sql="select e from EmployeeEntity e where e.firstName LIKE :input";
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		try {
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			Query query = em.createQuery(sql);
			query.setParameter("input",input);
			@SuppressWarnings("unchecked")
			List<EmployeeEntity> results = query.setFirstResult(pageId-1).setMaxResults(size).getResultList();
			if(results.size()>0){
				List<Employee> reply = new ArrayList<Employee>();
				results.parallelStream().forEach(
						(employeeEntity)->{
							reply.add(Employee.convertFromEntity(employeeEntity));
						}
				);
				return reply;
			}else{
				return new ArrayList<Employee>();
			}
		} 
		finally {
			close(emf,em);
		}
	}

	public List<Employee> getAllEmployeeByLastName(String input1, Integer pageId, Integer size) throws Exception {
		String input = "%"+input1+"%";
		String sql="select e from EmployeeEntity e where e.lastName LIKE :input"; 
		EntityManagerFactory emf = null;
		EntityManager em =  null;
		try {
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			Query query = em.createQuery(sql);
			query.setParameter("input",input);
			@SuppressWarnings("unchecked")
			List<EmployeeEntity> results = query.setFirstResult(pageId-1).setMaxResults(size).getResultList();
			if(results.size()>0){
				List<Employee> reply = new ArrayList<Employee>();
				results.parallelStream().forEach(
						(employeeEntity)->{
							reply.add(Employee.convertFromEntity(employeeEntity));
						}
				);
				return reply;
			}else{
				return new ArrayList<Employee>();
			}
		}
		finally {
			close(emf,em);
		}
	}


}
