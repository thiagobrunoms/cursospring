package com.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cursomc.constants.ClientType;
import com.cursomc.constants.PaymentStatus;
import com.cursomc.domain.Address;
import com.cursomc.domain.Category;
import com.cursomc.domain.City;
import com.cursomc.domain.Client;
import com.cursomc.domain.CreditCardPayment;
import com.cursomc.domain.Order;
import com.cursomc.domain.OrderedItem;
import com.cursomc.domain.Payment;
import com.cursomc.domain.Product;
import com.cursomc.domain.State;
import com.cursomc.domain.TicketPayment;
import com.cursomc.repositories.AddressRepository;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.repositories.CityRepository;
import com.cursomc.repositories.ClientRepository;
import com.cursomc.repositories.OrderRepository;
import com.cursomc.repositories.OrderedItemRepository;
import com.cursomc.repositories.PaymentRepository;
import com.cursomc.repositories.ProductRepository;
import com.cursomc.repositories.StateRepository;

@Service
public class DatabaseTestService {
	
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
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderedItemRepository orderedItemRepository;
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	
	@Autowired
	private ProductRepository productRepository;
	
	public void initiateTestDatabase() throws ParseException {
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Eletrodoméstico");
		Category cat3 = new Category(null, "Cama Mesa Banho");
		Category cat4 = new Category(null, "Utensílios");
		Category cat5 = new Category(null, "Automotivo");
		Category cat6 = new Category(null, "Pesca e Cia");
		Category cat7 = new Category(null, "Perfumaria");
		Category cat8 = new Category(null, "Vestuário");
		
		Product prod1 = new Product(null, "Computador", 2000.00);
		Product prod2 = new Product(null, "Mouse", 30.40);
		Product prod3 = new Product(null, "Processador", 1230.34);
		Product prod4 = new Product(null, "Geladeira", 2345.34);
		
		cat1.getProducts().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProducts().addAll(Arrays.asList(prod4));
		
		prod1.getCategories().addAll(Arrays.asList(cat1));
		prod2.getCategories().addAll(Arrays.asList(cat1));
		prod3.getCategories().addAll(Arrays.asList(cat1));
		prod4.getCategories().addAll(Arrays.asList(cat2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
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
		
		Client client1 = new Client(null, "Thiago de Sales", "thiago.sales@arapiraca.ufal.br", ClientType.F_PERSON, passEncoder.encode("233454"));
		client1.getContactNumbers().addAll(Arrays.asList("82999431690", "8233262884"));
		
		Client client2 = new Client(null, "Míryan Soares", "miryansoaresrocha@gmail.com", ClientType.F_PERSON, passEncoder.encode("544332"));
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), client1, address1);
		Order ped2 = new Order(null, sdf.parse("10/10/2017 19:35"), client2, address3);
		
		Payment pagto1 = new CreditCardPayment(null, PaymentStatus.PAID, ped1, 6);
		ped1.setPayment(pagto1);
		
		Payment pagto2 = new TicketPayment(null, PaymentStatus.PENDING, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);
		
		client1.getOrders().addAll(Arrays.asList(ped1));
		client2.getOrders().addAll(Arrays.asList(ped2));
				
		orderRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		OrderedItem oi1 = new OrderedItem(ped1, prod1, 0.00, 1, 2000.00);
		OrderedItem oi2 = new OrderedItem(ped1, prod3, 0.00, 2, 80.00);
		OrderedItem oi3 = new OrderedItem(ped2, prod2, 100.00, 1, 800.00);
		
		ped1.getItems().addAll(Arrays.asList(oi1, oi2));
		ped2.getItems().addAll(Arrays.asList(oi3));
		
		prod1.getItems().addAll(Arrays.asList(oi1));
		prod2.getItems().addAll(Arrays.asList(oi3));
		prod3.getItems().addAll(Arrays.asList(oi2));
		
		
		orderedItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
	}

}
