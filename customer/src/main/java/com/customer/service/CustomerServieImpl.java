package com.customer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.entity.Customer;


@Repository
public class CustomerServieImpl implements CustomerService {

	
	private SessionFactory sessionFactory;
	private Session session;
	
	@Autowired
	public CustomerServieImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory=sessionFactory;
		
		try {
			this.session=sessionFactory.getCurrentSession();
			}catch(HibernateException e){
				session=sessionFactory.openSession();
			}
		
	}
	


	@Override
	public void save(Customer theCustomer) {
		// TODO Auto-generated method stub
		
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(theCustomer);
		tx.commit();
		
	}



	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		
		List<Customer> customer=session.createQuery("from Customer").list();
		return customer;
	}

	
	@Transactional
	public Customer findById(int theId) {
		// TODO Auto-generated method stub
		
		Customer cus=new Customer();
		
		 Transaction tx=session.beginTransaction();
		
		cus=session.get(Customer.class,theId);
		
		tx.commit();
		return cus;
	}

	

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		
		Transaction tx = session.beginTransaction();

		// get transaction
		Customer student = session.get(Customer.class,theId);

		// delete record
		session.delete(student);

		tx.commit();
		
	}
	
	
	
	
}
