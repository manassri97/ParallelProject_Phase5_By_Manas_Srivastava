package com.capgemini.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request){
		return new ResponseEntity<>(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(),HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(value=PhoneNumberAlreadyExist.class)
	public ResponseEntity<Object> exception(PhoneNumberAlreadyExist phone)
	{
		//System.out.println(phone.getMessage());
		return new ResponseEntity<>(phone.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=WalletAccountDoesNotExist.class)
	public ResponseEntity<Object> exception1(WalletAccountDoesNotExist wallet)
	{
		return new ResponseEntity<>(wallet.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=InsufficientWalletBalanceException.class)
	public ResponseEntity<Object> exception3(InsufficientWalletBalanceException balance)
	{
		return new ResponseEntity<>(balance.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
}
