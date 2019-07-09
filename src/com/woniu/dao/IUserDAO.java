package com.woniu.dao;

import java.util.List;

import com.woniu.po.User;

public interface IUserDAO {
	
	void addUser(User u);
	void delUser(int id);
	void updUser(User u);
	User findOne(int id);
	List<User> findAll();

}
