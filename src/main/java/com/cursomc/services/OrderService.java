package com.cursomc.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.constants.PaymentStatus;
import com.cursomc.domain.Order;
import com.cursomc.domain.OrderedItem;
import com.cursomc.domain.Product;
import com.cursomc.domain.TicketPayment;
import com.cursomc.exceptions.ObjectNotFoundException;
import com.cursomc.repositories.OrderRepository;
import com.cursomc.repositories.OrderedItemRepository;
import com.cursomc.repositories.PaymentRepository;
import com.cursomc.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderedItemRepository orderedItemsRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	public Order find(Integer id) {
		Optional<Order> order = repository.findById(id);
		
		return order.orElseThrow(() -> new ObjectNotFoundException("Order with ID " + id + " does not exist!"));
	}

	public List<Order> getOrders() {
		List<Order> orders = repository.findAll();
		
		return orders;
	}
	
	@Transactional
	public Order insertOrder(Order order) {
		order.setId(null); //apenas para garantir que irá inserir um novo pedido
		order.setDate(new Date());
		order.getPayment().setStatus(PaymentStatus.PENDING);
		order.getPayment().setOrder(order);
		order.setClient(clientService.find(order.getClient().getId()));
		
		if (order.getPayment() instanceof TicketPayment) {
			TicketPayment tp = (TicketPayment) order.getPayment();
			Calendar c = Calendar.getInstance();
			c.setTime(order.getDate());
			c.add(Calendar.DAY_OF_MONTH, 7);
			tp.setDeadlineDate(c.getTime());
		}
		
		order = repository.save(order);
		paymentRepository.save(order.getPayment());
		
		for(OrderedItem item : order.getItems()) {
			item.setDiscount(0.0);
			Product product = productRepository.findById(item.getProduct().getId()).get();
			item.setProduct(product);
			item.setPrice(product.getValue()); //TODO Mudar para productService para tratar inexistências etc.
			item.setOrder(order);
		}
		
		orderedItemsRepository.saveAll(order.getItems());
//		emailService.sendOrderConfirmationMail(order);
		emailService.sendOrderConfirmationHtmlEmail(order);
		return order;
	}
	
}
