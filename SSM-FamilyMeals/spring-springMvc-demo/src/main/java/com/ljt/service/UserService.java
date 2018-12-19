package com.ljt.service;

import com.ljt.entity.User;

import java.util.List;

/**
 * @Title: UserService
 * @Author: Awan
 * @Date Created in 2018/11/7 20:43
 */
public interface UserService {
	/**
	 * 添加 user
	 * @param user
	 * @return
	 */
	boolean addUser(User user);

	/**
	 *  根据ID删除user
	 * @param id
	 * @return
	 */
	boolean deleteUserById(Long id);

	/**
	 * 更新 user
	 * @param user
	 * @return
	 */
	boolean updateUser(User user);

	/**
	 * 根据ID查询所有user
	 * @param id
	 * @return
	 */
	User getUserById(Long id);

	/**
	 * 查询所有user
	 * @return
	 */
	List<User> getUserAll();

	/**
	 * 查询user数量
	 * @return
	 */
	long getUserCount();
}
