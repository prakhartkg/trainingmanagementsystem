package com.yash.training.tms.bean;



import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.yash.training.tms.domain.User;
import com.yash.training.tms.exception.PasswordNotFoundException;
import com.yash.training.tms.exception.UserNotFoundException;
import com.yash.training.tms.service.UserServiceBeanLocal;

@ManagedBean
@SessionScoped
public class LoginBean {
	private String userName;
	private String password;
	private boolean rememberMe;
	private List<String> menuList;
	@Resource User user;
	@EJB
	UserServiceBeanLocal userService;
	
	
	
	public List<String> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<String> menuList) {
		this.menuList = menuList;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
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
	
		
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String authenticate(){
		try{
			user=userService.authenticate(userName,password);
			
			/*
			menuList=getMenu(user);
			if(rememberMe){
				HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				Cookie cookie = new Cookie("userName",user.getUserName());
			}*/
			
			
			if(user.getDesignation()==1){
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
			
				return "success";
			}else if(user.getDesignation()==2){
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
				
				return "TrainerDashBoard.xhtml?faces-redirect=true";
			}else if(user.getDesignation()==3){
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
				
				return "TraineeDashBoard.xhtml?faces-redirect=true";
			}
			 
			
		}catch(PasswordNotFoundException e){
			return "login.xhtml?errormsg=Invalid Password & faces-redirect=true";
		}catch(UserNotFoundException e){
			return "login.xhtml?errormsg=Invalid UserName & faces-redirect=true";
		}
		
		return null;
	}
	

	
	public String logout(){
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "login.xhtml?errormsg=Logged out&faces-redirect=true";
	}

	
}
