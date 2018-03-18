package com.skip.vanhack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "store")
@EntityListeners(AuditingEntityListener.class)
public class Store {

	@Id
	private long id;
	
	@Column(nullable = false)
	public String name;
	
	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private long cousineId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCousineId() {
		return cousineId;
	}

	public void setCousineId(long cousineId) {
		this.cousineId = cousineId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
