package com.borrow.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borrow.mapper.UserMapper;
import com.borrow.pojo.User;
import com.borrow.service.UserService;
/**
 * @Author Awan
 * @Description //TODO 管理员业务访问接口实现类
 * @Date Created in 20:40 2018/12/3
 */
@Service
public class UserServiceImpl implements UserService {
	private final Logger LOG = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByUsername(String username) {
		LOG.info("按用户名" + username + "查询管理员");
		return userMapper.findByUsername(username);
	}
}
