package com.yash.training.tms.service;

import javax.ejb.Local;

import com.yash.training.tms.domain.User;
import com.yash.training.tms.exception.PasswordNotFoundException;
import com.yash.training.tms.exception.UserNotFoundException;

@Local
public interface UserServiceBeanLocal {

	public User authenticate(String userName, String password) throws PasswordNotFoundException, UserNotFoundException;

	public void saveUser(User user) throws UserNotFoundException;

}
