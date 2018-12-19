package com.ljt.web.controller;

import com.ljt.entity.User;
import com.ljt.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Title: UserController
 * @Author: Awan
 * @Date Created in 2018/11/7 20:49
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@PostMapping("add")
	public boolean add(User user) {
		LOGGER.info("ADD()....");
		return userService.addUser(user);
	}

	@PostMapping("delete/{id}")
	public boolean delete(@PathVariable Long id) {
		return userService.deleteUserById(id);
	}

	@PostMapping("update")
	public boolean update(User user) {
		return userService.updateUser(user);
	}

	@PostMapping("get/{id}")
	public User get(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PostMapping("all")
	public List<User> all() {
		return userService.getUserAll();
	}

	@PostMapping("count")
	public long count() {
		return userService.getUserCount();
	}
}
