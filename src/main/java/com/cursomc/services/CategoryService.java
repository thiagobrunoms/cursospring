package com.cursomc.services;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Category;
import com.cursomc.exceptions.DeleteException;
import com.cursomc.exceptions.ObjectNotFoundException;
import com.cursomc.repositories.CategoriaRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoriaRepository repository;
	
	
	public Category findCategoryById(Integer id) {
		Optional<Category> categoria = repository.findById(id);
		
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Object with id " + id + " does not exist!"));
	}
	
	public Category insertCategory(Category category) {
		return repository.save(category);
	}
	
	public Category updateCategory(Category category) {
		findCategoryById(category.getId());
		
		return repository.save(category);
	}
	
	public void deleteCategory(Integer id) {
		findCategoryById(id);
		
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DeleteException("Não é permitido deletar categorias as quais possuem produtos associados!");
		}	
	}
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Page<Category> findByPage(Integer page, Integer numberOfLines, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, numberOfLines, Direction.valueOf(direction), orderBy);
		
		return repository.findAll(pageRequest);
	}

}
