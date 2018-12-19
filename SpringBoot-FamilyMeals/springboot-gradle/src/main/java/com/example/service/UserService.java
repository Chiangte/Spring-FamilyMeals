package com.example.service;

import com.example.entity.User;

import java.util.List;

/**
 * @Author: Awan
 * @Description: User
 * @Date Created in 16:06  2018/11/27
 */
public interface UserService {
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	String deleteByPrimaryKey(Integer id);

	/**
	 * 创建
	 * @param record
	 * @return
	 */
	int insertSelective(User record);

	/**
	 *  单个查询
	 * @param id
	 * @return
	 */
	User selectByPrimaryKey(Integer id);

	/**
	 *  更新
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(User record);

	/**
	 * 查询全部
	 * @return
	 */
	List<User> getUserAll(Integer pageNum, Integer pageSize);

}
