package com.borrow.pojo;

import lombok.Data;
/**
 * @Author Awan
 * @Description //TODO 管理员实体层
 * @Date Created in 19:00 2018/12/3
 */
@Data
public class User {
	/**
	 * 管理员ID
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 联系电话
	 */
	private String phone;
}
