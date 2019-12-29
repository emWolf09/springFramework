package com.springRestErp.dao;


import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class CacheEvictDemo {
	@PersistenceContext  
	
	public void clearHibernateCache() {
		EntityManagerFactory emf = null;
		EntityManager em =  null; 
		try {
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			Session s = (Session)em.getDelegate();     
			SessionFactory sf = s.getSessionFactory();       
			Cache cache = sf.getCache();

			if (cache != null) {
			    cache.evictAll(); 
			}
		} catch (Exception e) {
			throw e;
		}finally {
			EmployeeDaoImpl.close(emf, em);
		}
	}
}
