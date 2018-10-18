package com.cursomc.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Address;
import com.cursomc.domain.Client;
import com.cursomc.exceptions.IntegrityException;
import com.cursomc.exceptions.ObjectNotFoundException;
import com.cursomc.exceptions.UserAlreadyExistException;
import com.cursomc.repositories.AddressRepository;
import com.cursomc.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@Autowired AddressRepository addressRepository;
	
	public Client find(Integer id) {
		Optional<Client> client = repository.findById(id);
		
		return client.orElseThrow(() -> new ObjectNotFoundException("Client not found with id " + id));
	}
	
	@Transactional
	public Client insert(Client client) {
		if (repository.findByEmail(client.getEmail()) != null)
			throw new UserAlreadyExistException("Usuario com email " + client.getEmail() + " já existente!");
			
		client.setPassword(passEncoder.encode(client.getPassword()));
		client = repository.save(client);
		
		//TODO E se não existirem enderecos?
		
		for(Address eachClientAdd : client.getAddresses())
			eachClientAdd.setClient(client);
		
		
		addressRepository.saveAll(client.getAddresses());
		
		return client;
	}
	
	public void deleteClient(Integer id) {
		find(id); //dispara exceção caso não exista categoria
		
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new IntegrityException("Não é permitido deletar clientes os quais possuem pedidos associados!");
		}	
	}

}
