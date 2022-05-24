package dbexam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import util.ParamUtil;
import entity.Product;

public class ProductDao {
	private static final String SQL_SELECT_ALL = "SELECT product_id, p.name, price, c.name cname FROM products p JOIN categories c ON p.category_id = c.id order by product_id";
	private static final String SQL_SELECT_PRODCTNAME = "SELECT product_id, p.name, price, c.name cname FROM products p JOIN categories c ON p.category_id = c.id where p.name = ? or c.name = ?";
	
	Connection con;
	
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	public List<Product> findAll() {
		List<Product> list = new ArrayList<>();
		
		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ALL)){
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("cname")));
			}
		}catch (SQLException e) { 
			e.printStackTrace();
	    }
		return list;
	}
	
	public List<Product> find(Product pd){
		ArrayList<Object> paramList = new ArrayList<>();
		
		
		String productName = null;
		String categoryName = null;
		
		if (pd != null) {
			productName = pd.getProductName();
			categoryName = pd.getCategoryName();
		}
		
		System.out.println(productName);
	
		if (ParamUtil.isNullOrEmpty(productName)) {
			return findAll();
		}

		if(!ParamUtil.isNullOrEmpty(productName)) {
			paramList.add(productName);
		}
		
		if(!ParamUtil.isNullOrEmpty(categoryName)) {
			paramList.add(categoryName);
		}
		
		List<Product> pdList = new ArrayList<>();
		
		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_PRODCTNAME)){
			for (int i = 0; i < paramList.size(); i++) {
                stmt.setObject(i + 1, paramList.get(i));
            }
			
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				pdList.add(new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("cname")));
			}
		}catch (SQLException e) { 
			e.printStackTrace();
		}
	return pdList;
	}
}