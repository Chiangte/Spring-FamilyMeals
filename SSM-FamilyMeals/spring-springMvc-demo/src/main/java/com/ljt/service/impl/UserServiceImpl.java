package com.ljt.service.impl;

import com.ljt.dao.BlogDao;
import com.ljt.dao.UserDao;
import com.ljt.entity.User;
import com.ljt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: UserServiceImpl
 * @Author: Awan 逻辑业务层
 * @Date Created in 2018/11/7 20:45
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;

	@Override
	public boolean addUser(User user) {
		return userDao.save(user) == 1;
	}

	@Override
	public boolean deleteUserById(Long id) {
		blogDao.removeByUserId(id);
		int i = 5;
		if (i > 0){
			throw new RuntimeException("故意的");
		}
		return userDao.remove(id) == 1;
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.update(user) == 1;
	}

	@Override
	public User getUserById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> getUserAll() {
		return userDao.findAll();
	}

	@Override
	public long getUserCount() {
		return userDao.count();
	}
}
