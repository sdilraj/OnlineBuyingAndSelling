package com.portal.service;

import com.portal.bean.User;

public interface UserService {

	public int createUser(User user);

	public User getUser(String emailId);

	public int updateUser(User user);

	public int deleteUser(String emailId);

}
