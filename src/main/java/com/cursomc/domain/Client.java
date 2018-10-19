package com.cursomc.domain;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.cursomc.constants.ClientType;
import com.cursomc.constants.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Nome não pode ser vazio!")
	private String name;
	private Integer clientType;
	
	//No caso do cascade.all, ira refletir a deleção de um cliente, removendo todos os enderecos.
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> contactNumbers = new HashSet<>();
	
	@ElementCollection(fetch = FetchType.EAGER) //EAGER faz com que essa colecao seja buscada quando client for buscado no banco
	@CollectionTable(name = "PROFILE")
	private Set<Integer> profiles = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders= new ArrayList<>();
	
	@NotEmpty(message = "Email não pode ser vazio")
	@Email(message = "Email inválido!")
	@Column(unique=true)
	private String email;
	
	@NotEmpty
	@JsonIgnore
	private String password;

	public Client() {
		addProfile(UserProfile.ADMIN);
	}
	
	public Client(Integer id, String name, String email, ClientType clientType, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.clientType = clientType.getType();
		this.password = password;
		addProfile(UserProfile.CLIENT); //Nesse caso, todo usuario é um client
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<UserProfile> getProfiles() {
		Set<UserProfile> userProfiles = new HashSet<>();
		
		for (Integer profileId : profiles) {
			userProfiles.add(UserProfile.toEnum(profileId));
		}
		return userProfiles;
	}

	public void addProfile(UserProfile profile) {
		this.profiles.add(profile.getType());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [Id=" + id + ", name=" + name + ", clientType=" + clientType + ", addresses=" + addresses
				+ ", contactNumbers=" + contactNumbers + ", orders=" + orders + ", email=" + email + "]";
	}
	
	
	
	

}
