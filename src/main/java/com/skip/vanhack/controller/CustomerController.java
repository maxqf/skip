package com.skip.vanhack.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.skip.vanhack.exception.CustomValidationException;
import com.skip.vanhack.model.Customer;
import com.skip.vanhack.repository.CustomerRepository;
import com.skip.vanhack.repository.ProductRepository;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    CustomerRepository CustomerRepository;
    @Autowired
    ProductRepository ProductRepository;

    @GetMapping("/Customer")
    public List<Customer> getAllCustomer() {
        return CustomerRepository.findAll();
    }
    
    @PostMapping("/Customer/auth")
    public ResponseEntity<?> authCustomer(@Valid @RequestBody ObjectNode param) {
    	 
    	ObjectNode errorNode = JsonNodeFactory.instance.objectNode();

    	if(!param.has("email") || param.get("email").asText().isEmpty()) {
    		
    		errorNode.put("error ", "you must provide a valid email");  
    		return new ResponseEntity<ObjectNode>(errorNode, HttpStatus.BAD_REQUEST);	
    	}else if(!param.has("password") || param.get("password").asText().isEmpty()){
    		
    		errorNode.put("error ", "you must provide a valid password");  
    		return new ResponseEntity<ObjectNode>(errorNode, HttpStatus.BAD_REQUEST);	
    		
    	}else if(CustomerRepository.findByEmailPassword(param.get("email").asText(), param.get("password").asText()).isEmpty()) {
    		errorNode.put("error ", "There is already an account with this email!");     
    		return new ResponseEntity<ObjectNode>(errorNode, HttpStatus.BAD_REQUEST);	    		
    	}
    	
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @PostMapping("/Customer")
    public Customer createCustomer(@Valid @RequestBody Customer Customer) {
    	    	        
    	if(CustomerRepository.findByEmail(Customer.getEmail()) != null)
    		throw new CustomValidationException("Customer", "email", "There is already an account with this email!");    	
    	
        return CustomerRepository.save(Customer);
    }

}
