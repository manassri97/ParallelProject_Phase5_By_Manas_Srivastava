package com.capgemini.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.capgemini.beans.Customer;

@Repository
@Transactional
public class WalletRepoImpl implements WalletRepo 
{

	@PersistenceContext
	EntityManager entitymanager;
	
	@Override
	public boolean save(Customer customer)
	{
		if(findOne(customer.getMobileno())==null) {
 
		entitymanager.persist(customer);  
		return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public Customer findOne(String mobileNo)
	{
		Customer customer = entitymanager.find(Customer.class, mobileNo);  
		if(customer!=null)
			return customer;
		else
			return null;
	}

	@Override
	public boolean update(Customer customer) 
	{
		//entitymanager.getTransaction().begin();
		customer.getWallet().setBalance(customer.getWallet().getBalance());
		//entitymanager.getTransaction().commit();
		return true;
	}
}