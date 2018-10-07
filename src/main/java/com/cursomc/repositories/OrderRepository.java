package com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomc.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
