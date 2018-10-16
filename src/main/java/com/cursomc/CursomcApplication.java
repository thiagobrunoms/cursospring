package com.cursomc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
}
