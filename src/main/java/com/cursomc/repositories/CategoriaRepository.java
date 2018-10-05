package com.cursomc.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomc.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
