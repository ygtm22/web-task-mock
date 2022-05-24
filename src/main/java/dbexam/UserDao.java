package dbexam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entity.User;
import util.ParamUtil;

public class UserDao {
	private static final String SQL_SELECT_ID_AND_PASS = "SELECT * FROM users WHERE login_id = ? AND password = ? ";
	private static final String SQL_SELECT_PRODUCT_TABLE = "SELECT * FROM products";
	
	Connection con;
	
	public UserDao(Connection con) {
		this.con = con;
	}
	
	public User findByIdAndPass(String loginId, String password) {
		try (PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ID_AND_PASS)){
			stmt.setString(1, loginId);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return new User(rs.getString("login_id"), rs.getString("password"), rs.getString("name"));
			}else {
				return null;
			}
			
		}catch (SQLException e) { 
			e.printStackTrace();
        }
		return null;
	}
}