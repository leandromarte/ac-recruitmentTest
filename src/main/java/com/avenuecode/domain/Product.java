package com.avenuecode.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Image> images;
	
	@ManyToOne
	@JoinColumn(name = "parent_product_id")
	private Product parent;
	
	@JsonIgnore
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> subproducts;
	
	public Product() {
	}
	
	public Product(String name, String description) {
		this.name = name;
		this.description = description;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Image> getImages() {
		return images;
	}


	public void addImage(Image img) {
		if (this.images == null) {
			this.images = new ArrayList<Image>();
		}
		this.images.add(img);
	}


	public Product getParent() {
		return parent;
	}


	public void setParent(Product parent) {
		this.parent = parent;
	}
	
}
