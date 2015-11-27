package com.yash.training.tms.bean;


import javax.ejb.EJB;

import com.yash.training.tms.domain.User;
import com.yash.training.tms.exception.UserNotFoundException;
import com.yash.training.tms.service.UserServiceBeanLocal;

public class Registration {
	private String name;
	private String contact;
	private String email;
	private String userName;
	private String password;
	private int designation;
	private User user=new User();
	
	@EJB
	private UserServiceBeanLocal userBean;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDesignation() {
		return designation;
	}
	public void setDesignation(int designation) {
		this.designation = designation;
	}
	
	public String save(){
		user.setName(name);
		user.setContact(contact);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setDesignation(designation);
		user.setStatus(1);
		user.setRole(1);
		
			try {
				userBean.saveUser(user);
			} catch (UserNotFoundException e) {
				return "login.xhtml?errormsg=User Not Registered & faces-redirect=true";
			}
		
		
		return "login.xhtml?errormsg=Registered SuccessFully & faces-redirect=true";
	}
}
