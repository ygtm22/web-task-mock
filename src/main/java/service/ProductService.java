package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dbexam.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService{
	public List<Product> findAll(){
		List<Product> pdList = new ArrayList<>();
		
		try (Connection con = DbUtil.getConnection()){
			ProductDao productDao = new ProductDao(con);
			pdList = productDao.findAll();
		} catch (Exception e) {
            e.printStackTrace();
        }
		return pdList;
	}
	
	public List<Product> findByName(String p) {
		List<Product> pdList = new ArrayList<>();
		
		try (Connection con = DbUtil.getConnection()){
			ProductDao productDao = new ProductDao(con);
			pdList = productDao.findByName(p);
		} catch (Exception e) {
            e.printStackTrace();
        }
		return pdList;
	}
	
	 public int register(Product product) {
	        try (Connection conn = DbUtil.getConnection()) {
	            ProductDao productDao = new ProductDao(conn);
	            return productDao.insert(product);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return 0;
	    }
	 
	 public Product productId(Integer productId) {
	        try (Connection conn = DbUtil.getConnection()) {
	            ProductDao productDao = new ProductDao(conn);
	            return productDao.findByproductId(productId);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return null;
	    }
	 
	 public int pdDelete(Product product) {
	        try (Connection conn = DbUtil.getConnection()) {
	            ProductDao productDao = new ProductDao(conn);
	            return productDao.delete(product);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return 0;
	    }
	 
	 public int pdUpdate(Product product, Integer productId) {
	        try (Connection conn = DbUtil.getConnection()) {
	            ProductDao productDao = new ProductDao(conn);
	           return productDao.update(product, productId);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return 0;
	        }
	    }
	 
	 public Product id(Integer id) {
		 try (Connection conn = DbUtil.getConnection()) {
	            ProductDao productDao = new ProductDao(conn);
	            return productDao.findById(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return null;
	 }
	
	
	  public Product findByCount(int product) { 
		  try (Connection con =DbUtil.getConnection()){ 
			  ProductDao productDao = new ProductDao(con); 
			  return productDao.findByCount(product); 
		} catch (Exception e) {
			e.printStackTrace();
	  } 
		  return null; 
		  }
	 
}