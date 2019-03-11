package com.capgemini.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.beans.Customer;
import com.capgemini.exceptions.InsufficientWalletBalanceException;
import com.capgemini.exceptions.PhoneNumberAlreadyExist;
import com.capgemini.exceptions.WalletAccountDoesNotExist;

@Service
public interface WalletService {
	public Customer createAccount(Customer customer) throws PhoneNumberAlreadyExist;
	public Customer showBalance(String mobileNo) throws WalletAccountDoesNotExist, SQLException;
	public List<Customer> fundTransfer(String sourceMobile, String targetMobileNo, BigDecimal amount) throws InsufficientWalletBalanceException, WalletAccountDoesNotExist;
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws WalletAccountDoesNotExist;
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InsufficientWalletBalanceException, WalletAccountDoesNotExist;
}
