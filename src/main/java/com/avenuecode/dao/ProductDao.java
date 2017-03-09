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

	public List<Product> getListWith(boolean returnSubproducts, boolean returnImages) {
		StringBuffer strQuery = prepareWithQuery(returnSubproducts, returnImages);		
		
		return this.getList(strQuery.toString());
	}
	
	public List<Product> getListByIdWith(boolean returnSubproducts, boolean returnImages, Long id) {
		StringBuffer strQuery = prepareWithQuery(returnSubproducts, returnImages);		
		strQuery.append(" where prod.id = " + id.toString());
		return this.getList(strQuery.toString());
	}

	private StringBuffer prepareWithQuery(boolean returnSubproducts, boolean returnImages) {
		StringBuffer strQuery = new StringBuffer("from Product prod ");
		
		if (returnSubproducts) {
			strQuery.append("join prod.subproducts sub ");
		}
		
		if (returnImages) {
			strQuery.append("join prod.images img ");
		}
		return strQuery;
	}
}
