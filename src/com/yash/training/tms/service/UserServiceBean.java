package com.yash.training.tms.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.yash.training.tms.domain.User;
import com.yash.training.tms.exception.PasswordNotFoundException;
import com.yash.training.tms.exception.UserNotFoundException;
import com.yash.training.tms.exception.UserNotSaveException;
import com.yash.training.tms.util.util;

/**
 * Session Bean implementation class UserServiceBean
 */
@Stateless
@LocalBean
public class UserServiceBean implements UserServiceBeanLocal {

  User user=new User();

@Override
public User authenticate(String userName, String password) throws PasswordNotFoundException,UserNotFoundException{
	String sql="Select userName from user where userName='"+userName+"'";
		ResultSet resultSet=util.select(sql);
		try{
			if(resultSet.next()){
				sql="SELECT * FROM USER where userName='"+userName+"' and password='"+password+"'";
				resultSet=util.select(sql);
				if(resultSet.next()){
					user.setUser_id(resultSet.getInt(1));
					user.setName(resultSet.getString(2));
					user.setContact(resultSet.getString(3));
					user.setEmail(resultSet.getString(4));
					user.setUserName(userName);
					user.setPassword(password);
					user.setRole(resultSet.getInt(7));
					user.setStatus(resultSet.getInt(8));
					user.setDesignation(resultSet.getInt(9));
				}else{
					throw new PasswordNotFoundException();
				}
			}else{
				throw new UserNotFoundException();
			}
		}catch(SQLException e){
		
		}
			
		
	return user;
}

@Override
public void saveUser(User user){
		String sql = "INSERT INTO USER (NAME,contact,email,userName,PASSWORD,role,STATUS,designation)VALUES('"
				+ user.getName() + "','" 
				+ user.getContact() + "','" 
				+ user.getEmail() + "','" 
				+ user.getUserName()+ "','" 
				+ user.getPassword()+ "',"
				+user.getRole()+","
				+1+","
				+user.getDesignation()+ ")";
		System.out.println(sql);
		if(user.getDesignation()==0){
			throw new UserNotSaveException();
		}
		else{
			util.Update(sql);
		}
}
   
   
}
