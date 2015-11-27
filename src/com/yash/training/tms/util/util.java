package com.yash.training.tms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class util {
	private static Connection connection = null;

	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/trainingmanagementsystem";
		String user = "root";
		String password = "root";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			
		}
	}
	
	public static ResultSet select(String sql){
		Connection connection = util.getConnection();
		System.out.println(connection);
		ResultSet resultSet=null;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println(resultSet);
		} catch (SQLException e) {
			
			e.printStackTrace();
			disconnect();
		}
		
		return resultSet;

	}

	

	public static void Update(String sql) {
		Connection connection = getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			int result=preparedStatement.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
		disconnect();
		}
		disconnect();
	}
	
}
