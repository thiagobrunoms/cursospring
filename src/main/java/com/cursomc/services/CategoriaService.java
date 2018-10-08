package com.cursomc.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Category;
import com.cursomc.exceptions.ObjectNotFoundException;
import com.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Category findCategoryById(Integer id) {
		Optional<Category> categoria = repository.findById(id);
		
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Object with id " + id + " does not exist!"));
	}
	
	public Category insertCategory(Category category) {
		return repository.save(category);
	}

}
