package com.capgemini.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.beans.Customer;
import com.capgemini.exceptions.InsufficientWalletBalanceException;
import com.capgemini.exceptions.PhoneNumberAlreadyExist;
import com.capgemini.exceptions.WalletAccountDoesNotExist;
import com.capgemini.repo.WalletRepo;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired(required=true)
	WalletRepo walletRepo;
	
	@Override
	public Customer createAccount(Customer customer) throws PhoneNumberAlreadyExist{
		if(walletRepo.save(customer))
		{
			return customer;
		}
		else
		{
			throw new PhoneNumberAlreadyExist("Wallet already exist \nTry with another mobile number");
		}
	}

	@Override
	public Customer showBalance(String mobileNo) throws WalletAccountDoesNotExist{
		Customer customer = new Customer();
		
		customer = walletRepo.findOne(mobileNo);
		if(customer!=null)
			return customer;
		else
			throw new WalletAccountDoesNotExist("No Such Wallet Exists");
	}

	@Override
	public List<Customer> fundTransfer(String sourceMobile, String targetMobileNo, BigDecimal amount) throws InsufficientWalletBalanceException, WalletAccountDoesNotExist{
		Customer customer = new Customer();
		Customer customer1 = new Customer();
		List<Customer> list =new ArrayList<>();
		customer = walletRepo.findOne(sourceMobile);
		if(customer!=null)
		{
			list.add(withdrawAmount(sourceMobile, amount));
		}
		else
		{
			throw new WalletAccountDoesNotExist("Source Mobile Not Found");
		}
		customer1 = walletRepo.findOne(targetMobileNo);
		if(customer1!=null)
		{
			System.out.println("a");
			list.add(depositAmount(targetMobileNo, amount));
			System.out.println("b");
			return list;
		}
		else
		{
			throw new WalletAccountDoesNotExist("Target Mobile Not Found");
		}
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws WalletAccountDoesNotExist{
		Customer customer = new Customer();
		
		customer = walletRepo.findOne(mobileNo);
		if(customer!=null)
		{
			customer.getWallet().setBalance(customer.getWallet().getBalance().add(amount));
			if(walletRepo.update(customer))
				return customer;
		}
		throw new WalletAccountDoesNotExist("Target Mobile Number Not Found");
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InsufficientWalletBalanceException, WalletAccountDoesNotExist{
		Customer customer = new Customer();
		
		customer = walletRepo.findOne(mobileNo);
		if(customer!=null)
		{
			int i=customer.getWallet().getBalance().compareTo(amount);
			if(i<0)
			{
				throw new InsufficientWalletBalanceException("Not Enough Balance");
			}
			else
			{
				customer.getWallet().setBalance(customer.getWallet().getBalance().subtract(amount));
				walletRepo.update(customer);
				return customer;
			}
 		}
		else
			throw new WalletAccountDoesNotExist("No Wallet Exist");
	}

}