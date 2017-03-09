package com.avenuecode.dao;

import java.util.List;

import com.avenuecode.domain.Image;

public class ImageDao extends GenericDao<Image, Long> {
	public ImageDao() {
		super(Image.class);
	}
	
	public List<Image> getList() {
		return this.getList("from Image");
	}

	public List<Image> getImagesFromProductId(Long id) {
		return this.getList("from Image where product.id = " + id.toString());
	}	
}
