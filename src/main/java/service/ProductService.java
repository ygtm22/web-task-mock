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
}