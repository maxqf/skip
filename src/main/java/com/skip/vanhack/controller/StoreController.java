package com.skip.vanhack.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skip.vanhack.exception.CustomValidationException;
import com.skip.vanhack.model.Product;
import com.skip.vanhack.model.Store;
import com.skip.vanhack.repository.ProductRepository;
import com.skip.vanhack.repository.StoreRepository;

@RestController
@RequestMapping("/api/v1")
public class StoreController {

	@Autowired
	StoreRepository StoreRepository;

	@Autowired
	ProductRepository ProductRepository;
	
	@GetMapping("/Store")
	public List<Store> getAllStore() {
		return StoreRepository.findAll();
	}

	@GetMapping("/Store/search/{searchText}")
	public List<Store> searchText(@PathVariable(value = "searchText") String searchText) {
		
		return StoreRepository.findByName(searchText);
	}

	@GetMapping("/Store/{storeId}")
	public Optional<Store> findById(@PathVariable(value = "storeId") long storeId) {
		
		return StoreRepository.findById(storeId);
	}

	@GetMapping("/Store/{storeId}/products")
	public List<Product> findProductsByStoreId(@PathVariable(value = "storeId") long storeId) {
		
		return ProductRepository.findByStoreId(storeId);
	}

	@PostMapping("/Store")
	public Store createStore(@Valid @RequestBody Store Store) {

		return StoreRepository.save(Store);
	}

	@PostMapping("/StoreList")
	public ResponseEntity<?> createStoreList(@Valid @RequestBody List<Store> ListOfStores) {

		for (Store store : ListOfStores) {
			try {			
				StoreRepository.save(store);
			} catch (Exception e) {
				new CustomValidationException("Store", "id",
						"Failed to attempt on creating a store #" + store.getId());
			}
		}

		return ResponseEntity.ok().build();
	}

	
}
