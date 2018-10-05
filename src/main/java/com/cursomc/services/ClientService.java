package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Client;
import com.cursomc.exceptions.ObjectNotFoundException;
import com.cursomc.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public Client find(Integer id) {
		Optional<Client> client = repository.findById(id);
		
		return client.orElseThrow(() -> new ObjectNotFoundException("Client not found with id " + id));
	}

}
