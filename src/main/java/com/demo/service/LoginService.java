package com.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.dao.ConnectionUtils;
import com.demo.domain.User;

/**
 * 判断用户 和 注册
 * 
 * @author Administrator
 *
 */

public class LoginService {

	/**
	 * 判断用户是否合法
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public Boolean isIegal(User user) {

		try {
			Connection connection = ConnectionUtils.geConnection();

			String sql = "select * from  tab_user where username=?";

			PreparedStatement prepareStatement = connection
					.prepareStatement(sql);
			prepareStatement.setString(1, user.getName());

			ResultSet resultSet = prepareStatement.executeQuery();

			if (resultSet.next()) {
				// 查询到了结果
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String pwd = resultSet.getString(3);

				if (pwd.equals(user.getPassword())) {
					return true;
				}
			}

			resultSet.close();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public void regist(User user) {
		try {
			Connection connection = ConnectionUtils.geConnection();
			String sql = "inset into tb_user (username,password) values(?,?)";
			PreparedStatement prepareStatement = connection
					.prepareStatement(sql);

			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getPassword());

			prepareStatement.execute();
			
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
