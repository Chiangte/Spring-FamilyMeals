package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Awan
 * @Description:
 * @Date Created in 16:10  2018/11/27
 */
@Service
public class UserServiceImpl implements UserService {

	private final  static Logger   LOGGER = LoggerFactory.getLogger(UserService.class);
	private final UserMapper userMapper;
	@Autowired
	public  UserServiceImpl (UserMapper userMapper){
		this.userMapper = userMapper;
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Transactional
	@Override
	public String deleteByPrimaryKey(Integer id) {
		LOGGER.info("UserServiceImpl deleteByPrimaryKey id =>" + id);
		User user =   userMapper.selectByPrimaryKey(id);
		String result;
		if (user == null){
			result = "用户ID[" + id + "]找不到！";
		}else {
			result = String.valueOf(userMapper.deleteByPrimaryKey(id));
		}
		return  result;
	}

	/**
	 * 创建
	 * @param record
	 * @return
	 */
	@Override
	public int insertSelective(User record) {
		LOGGER.info("UserServiceImpl insertSelective record=>"+record.toString());
		return userMapper.insertSelective(record);
	}

	/**
	 * 单个查询
	 * @param id
	 * @return
	 */
	@Override
	public User selectByPrimaryKey(Integer id) {
		LOGGER.info("UserServiceImpl selectByPrimaryKey id=>    "+id);
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 更新
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKeySelective(User record) {
		LOGGER.info("UserServiceImpl updateByPrimaryKeySelective record=>"+record.toString());
		return userMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 查询全部
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<User> getUserAll(Integer pageNum, Integer pageSize) {
		LOGGER.info("UserServiceImpl getUseerAll pageNum=>  "+pageNum+" =>pageSize=>    "+pageSize);
		PageHelper.startPage(pageNum,pageSize);
		List<User> userList  = userMapper.getUserAll();
		LOGGER.info("UserServiceImpl getUserAll userList=>  "+userList.size());
		return userList;
	}
}
