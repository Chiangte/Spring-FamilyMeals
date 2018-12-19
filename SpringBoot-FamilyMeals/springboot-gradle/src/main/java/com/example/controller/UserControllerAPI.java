package com.example.controller;

import com.example.beans.PageResultBean;
import com.example.beans.ResultBean;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Awan
 * @Description:
 * @Date Created in 14:27  2018/11/27
 */
@RestController
@RequestMapping("/user")
public class UserControllerAPI {

	/**
	 *  对于/user/api HTTP GET来请求获取全部用户
	 *
	 *  对于/user/api HTTP POST来创建用户
	 *
	 *  对于/user/api/1 HTTP GET请求来获取id为1的用户
	 *
	 *  对于/user/api/1 HTTP PUT请求来更新
	 *
	 *  对于/user/api/1 HTTP DELETE请求来删除id为1的用户
	 */
	private final UserService userService;
	@Autowired
	public  UserControllerAPI(UserService userService){
		this.userService = userService;
	}

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public PageResultBean<List<User>> getUserAll(PageResultBean page) {
		return new PageResultBean<>(userService.getUserAll(page.getPageNo(), page.getPageSize()));
	}

	@RequestMapping(value = "/api/{id}", method = RequestMethod.GET)
	public ResultBean<User> getUserByPrimaryKey(@PathVariable("id") Integer id){
		return  new ResultBean<>(userService.selectByPrimaryKey(id));
	}
	@RequestMapping(value = "api/{id}",method = RequestMethod.PUT)
	public ResultBean<Integer> updateUserByPrimaryKey(@PathVariable("id") Integer id, User user ){
		user.setId(id);
		return new ResultBean<>(userService.updateByPrimaryKeySelective(user));
	}

	@RequestMapping(value = "/api/{id}",method = RequestMethod.DELETE)
	public ResultBean<String> deletePrimaryKey(@PathVariable("id") Integer id){
		return  new ResultBean<>(userService.deleteByPrimaryKey(id));
	}

	@RequestMapping(value = "/api", method = RequestMethod.POST)
	public ResultBean<Integer> createPrimaryKey(User user){
		return  new ResultBean<>(userService.insertSelective(user));
	}
}
