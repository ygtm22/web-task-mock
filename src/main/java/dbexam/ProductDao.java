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
	private static final String SQL_SELECT_PRODCTNAME = "SELECT product_id, p.name, price, c.name cname FROM products p JOIN categories c ON p.category_id = c.id where p.name like ? OR c.name like ? order by product_id";
	private static final String SQL_COUNT = "SELECT count(product_id) FROM products";
	private static final String SQL_INSERT = "INSERT INTO products (product_id, name, price, category_id, description) VALUES(?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_PRODUCT_ID = "SELECT p.id id, product_id, p.name, price, c.name cname, description FROM products p JOIN categories c ON p.category_id = c.id WHERE product_id = ? order by product_id";
	private static final String SQL_DELETE = "DELETE FROM products WHERE product_id = ?";
	private static final String SQL_UPDATE = "UPDATE products SET product_id = ?, name = ?, price = ?, category_id = ?, description = ? WHERE product_id = ? ";
	private static final String SQL_SELECT_ID = "SELECT p.id, product_id, p.name, price, c.name cname, description FROM products p JOIN categories c ON p.category_id = c.id WHERE p.id = ?";


	Connection con;

	public ProductDao(Connection con) {
		this.con = con;
	}

	public List<Product> findAll() {
		List<Product> list = new ArrayList<>();

		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ALL)) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"),
						rs.getString("cname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Product> findByName(String p) {
		List<Product> list = new ArrayList<>();

		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_PRODCTNAME)) {
			stmt.setString(1, "%" + p + "%");
			stmt.setString(2, "%" + p + "%");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"),
						rs.getString("cname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int insert(Product product) {
	    try (PreparedStatement stmt = con.prepareStatement(SQL_INSERT)) {
	    	stmt.setInt(1, product.getProductId());
	    	stmt.setString(2, product.getProductName());
	        stmt.setInt(3, product.getPrice());
	        stmt.setInt(4, product.getCategoryId());
	        stmt.setString(5, product.getDescription());

	        return stmt.executeUpdate();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		return 0;
	}
	
	public Product findByproductId(Integer productId) {
		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_PRODUCT_ID)) {
			stmt.setInt(1, productId);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Product(rs.getInt("id"), rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("cname"), rs.getString("description"));
			}
			
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		return null;
	}
	
	
	public int delete(Product product) {
	    try (PreparedStatement stmt = con.prepareStatement(SQL_DELETE)) {
	    	stmt.setInt(1, product.getProductId());
			/*
			 * stmt.setString(2, product.getProductName()); stmt.setInt(3,
			 * product.getPrice()); stmt.setInt(4, product.getCategoryId());
			 * stmt.setString(5, product.getDescription());
			 */
	        return stmt.executeUpdate();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		return 0;
	}
	
	public int update(Product product, Integer productId) {
	    try (PreparedStatement stmt = con.prepareStatement(SQL_UPDATE)) {
	    	stmt.setInt(1, product.getProductId());
	    	stmt.setString(2, product.getProductName());
	        stmt.setInt(3, product.getPrice());
	        stmt.setInt(4, product.getCategoryId());
	        stmt.setString(5, product.getDescription());
	        stmt.setInt(6, product.getProductId());

	        System.out.println(stmt.executeUpdate());
	        
	        return stmt.executeUpdate();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    	return 0;
	    }
	    
	}
	
	 public Product findById(Integer id){
		 try (PreparedStatement stmt =con.prepareStatement(SQL_SELECT_ID)) { 
			 stmt.setInt(1, id);
	 
			 ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Product(rs.getInt("id"), rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("cname"), rs.getString("description"));
			}
	 		} catch (SQLException e) { 
	 			e.printStackTrace();
	 		} 
	 		return null; 
	 	}
	
	
	
	  public Product findByCount(int productId) { 
		  try (PreparedStatement stmt = con.prepareStatement(SQL_COUNT)) { 
			  stmt.setInt(1, productId);
	  
			  ResultSet rs = stmt.executeQuery();
	  
			  if (rs.next()) {
					return new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("cname"), rs.getString("description"));
				}
	  
	  } catch (SQLException e) { 
		  e.printStackTrace(); 
	  }
	  
		  return null; 
	  }
	 
}