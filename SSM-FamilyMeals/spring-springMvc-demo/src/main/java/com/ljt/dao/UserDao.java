package com.ljt.dao;

import com.ljt.entity.User;

import java.util.List;

/**
 * @Title: UserDao
 * @Author: Awan
 * @Date Created in 2018/11/7 20:34
 */
public interface UserDao {
	int save(User user);
	int remove(Long id);
	int update(User user);
	User findById(Long id);
	List<User> findAll();
	long count();
}
