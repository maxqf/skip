package com.skip.vanhack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skip.vanhack.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	
	/*@Query("SELECT c FROM Cousine c WHERE c.name like %?1%")
	public List<Cousine> findByName(String name);*/
}
