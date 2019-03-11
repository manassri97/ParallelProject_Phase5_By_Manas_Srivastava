package com.capgemini.repo;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.beans.Customer;

public interface WalletRepo {
	public boolean save(Customer customer);
	public Customer findOne(String mobileNo);
	public boolean update(Customer customer);
	public List<Customer> showAllCustomer();
	public List<Customer> showCustomerByName(String name);
	public List<Customer> showCustomerClause(BigDecimal min_balance, BigDecimal max_balance);
}
