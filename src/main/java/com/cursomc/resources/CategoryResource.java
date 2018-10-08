package com.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursomc.domain.Category;
import com.cursomc.services.CategoriaService;
import com.cursomc.domain.Category;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@Autowired	
	private CategoriaService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable Integer id) {
		Category categoria = service.findCategoryById(id);
		
		return ResponseEntity.ok().body(categoria);
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> insert(@RequestBody Category category) {
		category = service.insertCategory(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
