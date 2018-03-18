package com.skip.vanhack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skip.vanhack.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	List<Customer> findByEmail(String email);
	
	@Query("SELECT c FROM Customer c WHERE c.email= ?1 and c.password = ?2")
	public List<Customer> findByEmailPassword(String email, String password);
	
}
