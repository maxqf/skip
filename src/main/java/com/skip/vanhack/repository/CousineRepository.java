package com.skip.vanhack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skip.vanhack.model.Cousine;

@Repository
public interface CousineRepository extends JpaRepository<Cousine, Long> {
	
	@Query("SELECT c FROM Cousine c WHERE c.name like %?1%")
	public List<Cousine> findByName(String name);
}
