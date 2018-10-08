package com.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursomc.domain.Category;
import com.cursomc.services.CategoryService;
import com.cursomc.domain.Category;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@Autowired	
	private CategoryService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Integer id) {
		Category categoria = service.findCategoryById(id);
		
		return ResponseEntity.ok().body(categoria);
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> create(@RequestBody Category category) {
		category = service.insertCategory(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Category category, @PathVariable Integer id) {
		category.setId(id);
		
		service.updateCategory(category);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleteCategory(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
