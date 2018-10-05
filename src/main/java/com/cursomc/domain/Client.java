package com.cursomc.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cursomc.constants.ClientType;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String name;
	private Integer clientType;
	
	@OneToMany(mappedBy = "client")
	private List<Address> addresses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> contactNumbers = new HashSet<>();

	public Client() {}
	
	public Client(Integer id, String name, ClientType clientType) {
		super();
		Id = id;
		this.name = name;
		this.clientType = clientType.getType();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClientType getClientType() throws Exception {
		return ClientType.toEnum(clientType);
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType.getType();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getContactNumbers() {
		return contactNumbers;
	}

	public void setContactNumbers(Set<String> contactNumbers) {
		this.contactNumbers = contactNumbers;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

}
