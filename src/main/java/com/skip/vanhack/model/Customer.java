package com.skip.vanhack.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    private long id;	

    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
	private String address;

	@Temporal(TemporalType.TIMESTAMP)    
	@Column(updatable = false)
	private Date creation;

	@Column(nullable = false)
	private String password;
		
	public Customer() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreation() {
		return this.creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}