package com.love2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.love2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager =theEntityManager;
	}
	
	

	@Override
	public List<Employee> findAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		//query
		
		Query <Employee> thequery = currentSession.createQuery("from Employee",Employee.class);
		
		List<Employee> employees =thequery.getResultList();
		return employees;
	}
	
	

	@Override
	public Employee findById(int theId) {                                            //get employee by id
		Session currentSession = entityManager.unwrap(Session.class);
		Employee theEmployee =currentSession.get(Employee.class,theId);
		return theEmployee;
	}

	
	
	@Override
	public void save(Employee theEmployee) {
		Session currentSession = entityManager.unwrap(Session.class);
	    currentSession.saveOrUpdate(theEmployee);
		
		
	}

	@Override
	public void deleteById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query thequery = currentSession.createQuery("delete from Employee where id=:employeeId");
		thequery.setParameter("employeeId",theId);
		thequery.executeUpdate();
		
		
	}
	

}
