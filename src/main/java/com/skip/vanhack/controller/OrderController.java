package com.skip.vanhack.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.skip.vanhack.exception.ResourceNotFoundException;
import com.skip.vanhack.model.Order;
import com.skip.vanhack.model.OrderItem;
import com.skip.vanhack.model.Product;
import com.skip.vanhack.repository.OrderItemRepository;
import com.skip.vanhack.repository.OrderRepository;
import com.skip.vanhack.repository.ProductRepository;
import com.skip.vanhack.repository.StoreRepository;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Autowired
	OrderRepository OrderRepository;
	
	@Autowired
	OrderItemRepository OrderItemRepository;
	
	@Autowired
	ProductRepository ProductRepository;
	
	@Autowired
	StoreRepository StoreRepository;

	@GetMapping("/Order/{orderId}")
	public Optional<Order> findById(@PathVariable(value = "orderId") long orderId) {
		
		return OrderRepository.findById(orderId);
	}
	
	@PostMapping("/Order")
	public Order createOrder(@Valid @RequestBody Order order) {
		
		for (OrderItem orderItem : order.getOrderItems()) {
			
			Product product = ProductRepository.findById(orderItem.getProductId());
			
			if(product != null) {
				//Fecth product's price stored previously
				orderItem.setPrice(product.getPrice());
				
				orderItem.setTotal(orderItem.getPrice() * orderItem.getQuantity());
				
				OrderItemRepository.save(orderItem);
			}			
		}
		
		return OrderRepository.save(order);
	}
		
    @PutMapping("/Order/{id}/Cancel")
    public Order cancelOrder(@PathVariable(value = "id") long orderId) {

        Order order = OrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        order.setStatus("Cancelled");
        
        Order updatedOrder = OrderRepository.save(order);
        
        return updatedOrder;
    }
    
	@GetMapping("/Order/{id}/Status")
	public ResponseEntity<?> getStatus(@PathVariable(value = "id") long orderId) {
		
		 Order order = OrderRepository.findById(orderId)
	                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
		
		 ObjectNode nodeStatus = JsonNodeFactory.instance.objectNode();
		 
		 nodeStatus.put("status", order.getStatus());
		 
		 return new ResponseEntity<ObjectNode>(nodeStatus, HttpStatus.OK);
	}
	
	@GetMapping("/Order")
	public List<Order> getAllOrder() {
		return OrderRepository.findAll();
	}
		
}
