package com.cursomc.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.cursomc.domain.Address;

public class ClientDTO {
	
	private String name;
	private Integer clientType;
	private Set<String> contactNumbers = new HashSet<>();
	private String email;
	private List<Address> addresses = new ArrayList<>();
	
	public ClientDTO() {}
	
	
	
	public ClientDTO(String name, Integer clientType, Set<String> contactNumbers, String email) {
		super();
		this.name = name;
		this.clientType = clientType;
		this.contactNumbers = contactNumbers;
		this.email = email;
	}



	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Set<String> getContactNumbers() {
		return contactNumbers;
	}

	public void setContactNumbers(Set<String> contactNumbers) {
		this.contactNumbers = contactNumbers;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	

	public List<Address> getAddresses() {
		return addresses;
	}



	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}



	@Override
	public String toString() {
		String wholeAddress = "";
		for (Address a : addresses) {
			String add = a.toString();
			wholeAddress = wholeAddress + add;
			
		}
			
		return "ClientDTO [name=" + name + ", clientType=" + clientType + ", contactNumbers=" + contactNumbers
				+ ", email=" + email + ", addresses= " + wholeAddress;
	}




	
	
	
}
