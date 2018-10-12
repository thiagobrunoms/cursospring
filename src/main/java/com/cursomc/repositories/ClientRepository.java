package com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomc.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	Client findByEmail(String email);

}
