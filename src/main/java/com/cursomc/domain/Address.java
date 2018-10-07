package com.cursomc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer number;
	private String district;
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	public Address() {}	
	
	public Address(Integer id, String name, Integer number, String district, String cep, City city, Client client) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.district = district;
		this.cep = cep;
		this.city = city;
		this.client = client;
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
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	

}
