package com.avenuecode.dao;

import java.util.List;

import com.avenuecode.domain.Product;

public class ProductDao extends GenericDao<Product, Long> {
	public ProductDao() {
		super(Product.class);
	}
	
	public void deleteAll() {
		for (Product p: this.getList("from Product")) {
			this.delete(p.getId());
		}
	}

	public List<Product> getList() {
		return this.getList("from Product");
	}
	
	public List<Product> getSubproductsFromProductId(Long id) {
		return this.getList("from Product p where p.parent.id = " + id.toString());
	}
}
