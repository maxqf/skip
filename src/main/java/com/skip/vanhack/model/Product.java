package com.skip.vanhack.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private long storeId;
	
	private String name;

	private String description;

	private double price;

	public Product() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

//	public List<Orderitem> getOrderitems() {
//		return this.orderitems;
//	}
//
//	public void setOrderitems(List<Orderitem> orderitems) {
//		this.orderitems = orderitems;
//	}
//
//	public Orderitem addOrderitem(Orderitem orderitem) {
//		getOrderitems().add(orderitem);
//		orderitem.setProduct(this);
//
//		return orderitem;
//	}
//
//	public Orderitem removeOrderitem(Orderitem orderitem) {
//		getOrderitems().remove(orderitem);
//		orderitem.setProduct(null);
//
//		return orderitem;
//	}

}