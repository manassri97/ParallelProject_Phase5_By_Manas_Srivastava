package com.capgemini.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="customer_phase5")
public class Customer {
	
	@NotNull
	@Size(min=1,message="dfjk")
	@Pattern(regexp="^[A-Z][a-z]+$",message="valid name is required")
	private String name;
	
	@Id
	@Pattern(regexp="^[6-9][0-9]{9}$",message="valid number is required")
	private String mobileno;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Wallet wallet;
	
	public Customer() {
		super();
	}
	public Customer(
			@NotNull @Size(min = 1, message = "name is required") 
			@Pattern(regexp = "^[A-Z][a-z]+$") 
			String name,
			@Pattern(regexp = "^[6-9][0-9]{9}$") 
			String mobileno,
			Wallet wallet) {
		super();
		this.name = name;
		this.mobileno = mobileno;
		this.wallet = wallet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobileno=" + mobileno + ", wallet=" + wallet + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobileno == null) ? 0 : mobileno.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((wallet == null) ? 0 : wallet.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (mobileno == null) {
			if (other.mobileno != null)
				return false;
		} else if (!mobileno.equals(other.mobileno))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (wallet == null) {
			if (other.wallet != null)
				return false;
		} else if (!wallet.equals(other.wallet))
			return false;
		return true;
	}
	
}
