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
	private static final String SQL_SELECT_PRODCTNAME = "SELECT product_id, p.name, price, c.name cname FROM products p JOIN categories c ON p.category_id = c.id where p.name like ? OR c.name like ?";
	
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
	
	/*
	 * public List<Product> find(Product pd){ String productName = ""; if
	 * (ParamUtil.isNullOrEmpty(productName)) { return findAll(); }
	 * 
	 * List<Product> pdList = new ArrayList<>();
	 * 
	 * try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_PRODCTNAME)){
	 * 
	 * ResultSet rs = stmt.executeQuery();
	 * 
	 * while(rs.next()) { pdList.add(new Product(rs.getInt("product_id"),
	 * rs.getString("name"), rs.getInt("price"), rs.getString("cname"))); } }catch
	 * (SQLException e) { e.printStackTrace(); } return pdList; }
	 */
	
	public List<Product> findByName(String p){
		List<Product> list = new ArrayList<>();
		
		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_PRODCTNAME)){
			stmt.setString(1, "%" + p + "%");
			stmt.setString(2, "%" + p + "%");
			
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				list.add(new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("cname")));
			}
		}catch (SQLException e) { 
			e.printStackTrace();
		}
	return list;
	}
}