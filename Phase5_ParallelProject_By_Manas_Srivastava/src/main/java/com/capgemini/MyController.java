package com.capgemini;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.beans.Customer;
import com.capgemini.service.WalletServiceImpl;

@RestController
public class MyController {
	
	@Autowired(required=true)
	WalletServiceImpl walletServiceImpl;
	
	
	
	@RequestMapping(method=RequestMethod.POST, value="/addCustomer")
	public Customer createAccount(@Valid @RequestBody Customer customer){
		
		Customer customer1 = walletServiceImpl.createAccount(customer);
		return customer1;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getBalance/{mobileNo}")
	public Customer showBalance(@PathVariable String mobileNo) {

		return walletServiceImpl.showBalance(mobileNo);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/depositBalance/{mobileNo}/{amount}")
	public ResponseEntity<Object> depositBalance(@PathVariable String mobileNo ,@PathVariable BigDecimal amount) {
		
		Customer customer = walletServiceImpl.depositAmount(mobileNo, amount);
		return new ResponseEntity<Object>(customer,HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/withdrawBalance/{mobileNo}/{amount}")
	public ResponseEntity<Object> withdrawBalance(@PathVariable String mobileNo ,@PathVariable BigDecimal amount) {
		
		Customer customer = walletServiceImpl.withdrawAmount(mobileNo, amount);
		return new ResponseEntity<Object>(customer,HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/fundTransfer/{sourceMobileNo}/{targetMobileNo}/{amount}")
	public ResponseEntity<Object> fundTransfer(@PathVariable String sourceMobileNo ,@PathVariable String targetMobileNo ,@PathVariable BigDecimal amount) {
		
		List<Customer> list = walletServiceImpl.fundTransfer(sourceMobileNo, targetMobileNo, amount);
		return new ResponseEntity<Object>(list,HttpStatus.OK);	
		 
		}
	

}