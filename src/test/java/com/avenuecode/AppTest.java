package com.avenuecode;

import org.junit.Test;

import com.avenuecode.dao.ImageDao;
import com.avenuecode.dao.ProductDao;
import com.avenuecode.domain.Image;
import com.avenuecode.domain.Product;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
    @Test
    public void testShouldCreateProduct() {
    	ProductDao prodDao = new ProductDao();
    	ProductDao prodDaoAfter = new ProductDao(); // How can i ignore cache?
    	prodDao.deleteAll();
    	int countProducts = 0;
		countProducts = prodDao.getList().size();
    	
    	assertTrue(countProducts == 0);
    	
    	Product p = new Product("product1", "Product 1 description");
    	prodDao.save(p);
    	
    	countProducts = prodDaoAfter.getList().size();
    	
    	assertTrue(countProducts == 1);
    }
    
    @Test
    public void testShouldCreateProductAndImage() {
    	ProductDao prodDao = new ProductDao();
    	ProductDao prodDaoAfter = new ProductDao();
    	ImageDao imageDao = new ImageDao();
    	ImageDao imageDaoAfter = new ImageDao(); // current session got a cache?
    	prodDao.deleteAll();
    	int countProducts = 0;
		countProducts = prodDao.getList().size();
		
		int countImages = 0;
		countImages = imageDao.getList().size();
    	
    	assertTrue(countProducts == 0);
    	assertTrue(countImages == 0);
    	
    	Product p = new Product("product1", "Product 1 description");
    	
    	Image i1 = new Image("name1", "type1");
    	Image i2 = new Image("name2", "type2");
    	Image i3 = new Image("name3", "type3");

    	p.addImage(i1);
    	p.addImage(i2);
    	p.addImage(i3);
    	
    	prodDao.save(p);
    	
    	countProducts = prodDaoAfter.getList().size();
    	countImages = imageDaoAfter.getList().size();
    	
    	assertTrue(countProducts == 1);
    	assertTrue(countImages == 3);
    }    
    
    @Test
    public void testShouldDeleteProductAndImages() {
    	ProductDao prodDao = new ProductDao();
    	ProductDao prodDaoAfter = new ProductDao();
    	prodDao.deleteAll();
    	
    	prodDao.save(new Product("Product 1", "Product description"));
    	
    	Product product = prodDao.getList().get(0);
    	prodDao.delete(product.getId());
    	assertTrue(prodDaoAfter.getList().size() == 0);
    	
    }
    
}
