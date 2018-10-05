package com.cursomc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursomc.constants.ClientType;
import com.cursomc.domain.Address;
import com.cursomc.domain.Categoria;
import com.cursomc.domain.City;
import com.cursomc.domain.Client;
import com.cursomc.domain.Produto;
import com.cursomc.domain.State;
import com.cursomc.repositories.AddressRepository;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.repositories.CityRepository;
import com.cursomc.repositories.ClientRepository;
import com.cursomc.repositories.ProductRepository;
import com.cursomc.repositories.StateRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ClientRepository clientRepository;	

	
	@Autowired
	private ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Eletrodoméstico");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Mouse", 30.40);
		Produto prod3 = new Produto(null, "Processador", 1230.34);
		Produto prod4 = new Produto(null, "Geladeira", 2345.34);
		
		cat1.getProducts().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProducts().addAll(Arrays.asList(prod4));
		
		prod1.getCategories().addAll(Arrays.asList(cat1));
		prod2.getCategories().addAll(Arrays.asList(cat1));
		prod3.getCategories().addAll(Arrays.asList(cat1));
		prod4.getCategories().addAll(Arrays.asList(cat2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4));
		
		State state1 = new State(null,  "Minas Gerais");
		State state2 = new State(null,  "Alagoas");
		
		City city1 = new City(null, "Urbelândia", state1);
		City city2 = new City(null, "Maceió", state2);
		City city3 = new City(null, "Arapiraca", state2);
		
		state1.getCities().addAll(Arrays.asList(city1));
		state2.getCities().addAll(Arrays.asList(city2, city3));
		
		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
		
		Client client1 = new Client(null, "Thiago de Sales", ClientType.F_PERSON);
		client1.getContactNumbers().addAll(Arrays.asList("82999431690", "8233262884"));
		
		Client client2 = new Client(null, "Míryan Soares", ClientType.F_PERSON);
		client2.getContactNumbers().addAll(Arrays.asList("82999328297"));
		
		Address address1 = new Address(null, "Rua TX", 3, "Massa", "43343343", city2, client1);
		Address address2 = new Address(null, "Rua TY", 55, "Farol", "8787878", city2, client1);
		Address address3 = new Address(null, "Rua MY", 155, "Primavera", "545445", city3, client2);
		
		client1.getAddresses().addAll(Arrays.asList(address1, address2));
		client2.getAddresses().addAll(Arrays.asList(address3));
		
		clientRepository.saveAll(Arrays.asList(client1));
		clientRepository.saveAll(Arrays.asList(client2));
		
		addressRepository.saveAll(Arrays.asList(address1));
		addressRepository.saveAll(Arrays.asList(address2));
		addressRepository.saveAll(Arrays.asList(address3));
		
	}
}
