package com.woniu.dao.impl;

import java.util.List;

import com.woniu.dao.IUserDAO;
import com.woniu.po.User;
import com.woniu.util.BaseDAO;

public class UserDAOImpl implements IUserDAO {
	
	BaseDAO<User> a = new BaseDAO<>();

	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		String sql = "insert into user values(null,?,?)";
		Object[] objs = {u.getUname(),u.getUpwd()};
		a.update(sql, objs);

	}

	@Override
	public void delUser(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from user where uid=?";
		Object[] objs = {id};
		a.update(sql, objs);
	}

	@Override
	public void updUser(User u) {
		// TODO Auto-generated method stub
		String sql = "update user set uname=?,upwd=? where uid=?";
		Object[] objs = {u.getUname(),u.getUpwd(),u.getUid()};
		a.update(sql, objs);
	}

	@Override
	public User findOne(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from user where uid=?";
		Object[] objs = {id};
		List<User> list = a.select(sql, objs, User.class);
		return list.size()==0?null:list.get(0);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		String sql = "select * from user";
		Object[] objs = {};
		List<User> list = a.select(sql, objs, User.class);
		return list;
	}

}
