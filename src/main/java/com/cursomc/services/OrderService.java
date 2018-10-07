package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Order;
import com.cursomc.exceptions.ObjectNotFoundException;
import com.cursomc.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public Order find(Integer id) {
		Optional<Order> order = repository.findById(id);
		
		return order.orElseThrow(() -> new ObjectNotFoundException("Order with ID " + id + " does not exist!"));
	}

	public List<Order> getOrders() {
		List<Order> orders = repository.findAll();
		
		return orders;
	}
	
}
