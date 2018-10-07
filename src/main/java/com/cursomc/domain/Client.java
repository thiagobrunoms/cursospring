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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String name;
	private Integer clientType;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client")
	private List<Address> addresses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> contactNumbers = new HashSet<>();
	
	@OneToMany(mappedBy = "client")
	private List<Order> orders= new ArrayList<>();

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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Client other = (Client) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
