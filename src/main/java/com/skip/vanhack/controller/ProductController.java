package com.skip.vanhack.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.skip.vanhack.exception.CustomValidationException;
import com.skip.vanhack.exception.ResourceNotFoundException;
import com.skip.vanhack.model.Cousine;
import com.skip.vanhack.model.Product;
import com.skip.vanhack.repository.ProductRepository;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	ProductRepository ProductRepository;

	@GetMapping("/Product")
	public List<Product> getAllProduct() {
		return ProductRepository.findAll();
	}
	
	@GetMapping("/Product/search/{searchText}")
	public List<Product> searchText(@PathVariable(value = "searchText") String searchText) {
	
		return ProductRepository.findByName(searchText);
	}

	@GetMapping("/Product/{id}")
	public Product getProductById(@PathVariable(value = "id") Long ProductId) {
		return ProductRepository.findById(ProductId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", ProductId));
	}

	@PostMapping("/Product")
	public Product createProduct(@Valid @RequestBody Product Product) {

		return ProductRepository.save(Product);
	}
	
	@PostMapping("/ProductsList")
	public ResponseEntity<?> createProduct(@Valid @RequestBody List<Product> Products) {

		for (Product product : Products) {

			try {
				ProductRepository.save(product);
			} catch (Exception e) {
				new CustomValidationException("Store", "id",
						"Failed to attempt on creating a product #" + product.getId());
			}
		}

		return ResponseEntity.ok().build();
	}

}
