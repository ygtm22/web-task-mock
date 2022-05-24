package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dbexam.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService{
	public List<Product> find(Product pd) {
		List<Product> pdList = new ArrayList<>();
		
		try (Connection con = DbUtil.getConnection()){
			ProductDao productDao = new ProductDao(con);
			pdList = productDao.find(pd);
		} catch (Exception e) {
            e.printStackTrace();
        }
		return pdList;
	}
}