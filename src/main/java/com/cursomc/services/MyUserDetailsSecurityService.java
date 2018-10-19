package com.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Client;
import com.cursomc.repositories.ClientRepository;
import com.cursomc.security.MyUserSecurityDetails;

@Service
public class MyUserDetailsSecurityService implements UserDetailsService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String emailAsusername) throws UsernameNotFoundException {
		Client client = clientRepository.findByEmail(emailAsusername);
		
		if (client == null)
			throw new UsernameNotFoundException(emailAsusername);
		
		return new MyUserSecurityDetails(client.getId(), client.getEmail(), client.getPassword(), client.getProfiles());
	}

}
