package com.takeo.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takeo.dto.CategoryDto;
import com.takeo.service.impl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl catServiceImpl;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("")
	public ResponseEntity<Map<String, String>> createCat(@Valid @RequestBody CategoryDto category) {
		String message = "Message";
		String catSave = catServiceImpl.create(category);

		Map<String, String> response = new HashMap<>();

		response.put(message, catSave);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		List<CategoryDto> category = catServiceImpl.readAll();
		String message = "Categories";

		Map<String, List<CategoryDto>> response = new HashMap<>();

		response.put(message, category);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, CategoryDto>> get(@PathVariable("id") Long categoryId) {

		CategoryDto category = catServiceImpl.readCategory(categoryId);
		String message = "Category";
		Map<String, CategoryDto> response = new HashMap<>();
		response.put(message, category);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/{cid}")
	public ResponseEntity<Map<String, String>> updateCategory(@PathVariable("cid") long categoryId,
			@Valid @RequestBody CategoryDto category) {

		String existingCategory = catServiceImpl.update(category, categoryId);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, existingCategory);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{cid}")
	public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable("cid") long categoryId) {

		String deleteCategory = catServiceImpl.delete(categoryId);
		String message = "Message";
		Map<String, String> response = new HashMap<>();
		response.put(message, deleteCategory);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
