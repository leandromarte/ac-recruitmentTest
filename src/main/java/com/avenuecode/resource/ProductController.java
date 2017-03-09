package com.avenuecode.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.avenuecode.dao.ImageDao;
import com.avenuecode.dao.ProductDao;
import com.avenuecode.domain.Image;
import com.avenuecode.domain.Product;

@Path("product")
public class ProductController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response responseGet() {
		ProductDao dao = null;
		try {
		dao = new ProductDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Product> list = dao.getList();
		return Response.ok(list).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response responseGetById(@PathParam("id") long id) {
		ProductDao dao = new ProductDao();
		Product prod = dao.findById(id);
		return Response.ok(prod).build();
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/subproducts")
	public Response responseGetSubprodsById(@PathParam("id") long id) {
		ProductDao dao = new ProductDao();

		List<Product> subproducts = dao.getSubproductsFromProductId(id);
		
		return Response.ok(subproducts).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/images")
	public Response responseGetImagesById(@PathParam("id") long id) {
		ImageDao dao = new ImageDao();

		List<Image> images = dao.getImagesFromProductId(id);
		
		return Response.ok(images).build();
	}	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response responsePost(Product productToInsert) {
		Response response = null;
		
		try {
			new ProductDao().save(productToInsert);
			response = Response.created(new URI("/product/" + productToInsert.getId().toString())).build();
		} catch (Exception e) {
			response = Response.serverError().build();
		}
		
		return response;
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response responsePut(@PathParam("id") long id, Product newProduct) {
		Response response = null;
		Product productFromRepo = null;
		ProductDao productDao = new ProductDao();
		
		try {
			productFromRepo = productDao.findById(id);

			if (newProduct.getName() != null) {
				productFromRepo.setName(newProduct.getName());
			}
			
			if (newProduct.getDescription() != null) {
				productFromRepo.setDescription(newProduct.getDescription());
			}
			
			productDao.save(productFromRepo);
			response = Response.ok().build();
		} catch (Exception e) {
			response = Response.serverError().build();
		}
		
		return response;
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response responseDelete(@PathParam("id") long id) {
		Response response = null;
		
		try {
			new ProductDao().delete(id);
			response = Response.ok().build();
		} catch (Exception e) {
			response = Response.serverError().build();
		}
		
		return response;
	}
	
	
	
}
