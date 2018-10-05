package com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomc.domain.Produto;

public interface ProductRepository extends JpaRepository<Produto, Integer> {

}
