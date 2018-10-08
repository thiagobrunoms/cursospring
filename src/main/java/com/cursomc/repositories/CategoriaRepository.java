package com.cursomc.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomc.domain.Category;

public interface CategoriaRepository extends JpaRepository<Category, Integer> {

}
