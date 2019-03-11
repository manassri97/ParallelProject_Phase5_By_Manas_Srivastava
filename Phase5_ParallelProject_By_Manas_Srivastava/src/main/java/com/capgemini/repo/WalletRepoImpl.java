package com.capgemini.repo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;

import javassist.bytecode.Mnemonic;

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

	@Override
	public List<Customer> showAllCustomer() {
		Query query = entitymanager.createQuery("select customer from Customer customer", Customer.class);
		List<Customer> list = query.getResultList();
		return list;
	}

	@Override
	public List<Customer> showCustomerByName(String name) {
		Query query = entitymanager.createQuery("select customer from Customer customer where customer.name=:name_match", Customer.class);
		query.setParameter("name_match", name);
		List<Customer> list = query.getResultList();
		return list;
	}

	@Override
	public List<Customer> showCustomerClause(BigDecimal min_balance, BigDecimal max_balance) {
		Query query = entitymanager.createQuery("select customer from Customer customer where customer.wallet.walletId in (select wallet.walletId from Wallet wallet where wallet.balance>=:min_balance and wallet.balance<=:max_balance)");
		query.setParameter("min_balance",min_balance);
		query.setParameter("max_balance",max_balance);
		List<Customer> list = query.getResultList();
		return list;
	}
}