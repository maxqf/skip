package com.skip.vanhack.controller;

import java.util.List;

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
import com.skip.vanhack.model.Cousine;
import com.skip.vanhack.repository.CousineRepository;

@RestController
@RequestMapping("/api/v1")
public class CousineController {

	@Autowired
	CousineRepository CousineRepository;


	@PostMapping("/Cousine")
	public Cousine createCousine(@Valid @RequestBody Cousine Cousine) {

		return CousineRepository.save(Cousine);
	}

	@PostMapping("/CousineList")
	public ResponseEntity<?> createCousineList(@Valid @RequestBody List<Cousine> ListOfCousine) {

		for (Cousine cousine : ListOfCousine) {
			try {
				CousineRepository.save(cousine);
			} catch (Exception e) {
				new CustomValidationException("Cousine", "id",
						"Failed to attempt on creating cuisine #" + cousine.getId());
			}
		}

		return ResponseEntity.ok().build();
	}

	@GetMapping("/Cousine")
	public List<Cousine> getAllCousine() {
		return CousineRepository.findAll();
	}

	@GetMapping("/Cousine/search/{searchText}")
	public List<Cousine> searchText(@PathVariable(value = "searchText") String searchText) {
		
		return CousineRepository.findByName(searchText);
	}
	
}
