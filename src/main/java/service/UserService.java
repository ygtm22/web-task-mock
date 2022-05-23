package service;

import java.sql.Connection;

import dbexam.UserDao;
import entity.User;
import util.DbUtil;

public class UserService{
	public User authentication(String login_id, String pass) {
		try (Connection con = DbUtil.getConnection()){
			UserDao userDao = new UserDao(con);
			User user = userDao.findByIdAndPass(login_id, pass);
			
			return user;
		} catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
}