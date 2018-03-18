package com.skip.vanhack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skip.vanhack.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	
	public List<Store> findByCousineId(long cousineId);
	
	@Query("SELECT c FROM Store c WHERE c.name like %?1%")
	List<Store> findByName(String name);
}
