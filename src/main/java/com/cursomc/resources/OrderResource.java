package com.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.domain.Order;
import com.cursomc.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		List<Order> orders = service.getOrders();
		
		return ResponseEntity.ok().body(orders);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Order order = service.find(id);
		
		return ResponseEntity.ok().body(order);
	}

}
