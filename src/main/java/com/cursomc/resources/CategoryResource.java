package com.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		List<Category> categories = service.findAll();
		
		return ResponseEntity.ok().body(categories);
	}
	
	//org.springframework.data.mapping.PropertyReferenceException: No property ASC found for type Category!
	//http://localhost:8080/categories/pagination?numberOfLines=3&page=2&direction=DESC
	@GetMapping("/pagination")
	public ResponseEntity<Page<Category>> findByInterval(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="numberOfLines", defaultValue="24") Integer numberOfLines,
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="orderBy", defaultValue="name") String orderBy) {
		
		Page<Category> categories = service.findByPage(page, numberOfLines, orderBy, direction);
		return ResponseEntity.ok().body(categories);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> read(@PathVariable Integer id) {
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
	
//	PageRequest.of
	
}
