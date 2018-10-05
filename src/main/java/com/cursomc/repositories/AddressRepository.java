package com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomc.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
