package com.skip.vanhack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skip.vanhack.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByStoreId(long store_id);
	
	@Query("SELECT c FROM Cousine c WHERE c.name like %?1%")
	List<Product> findByName(String name);
}
