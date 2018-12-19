package com.ljt.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: User
 * @Author: Awan
 * @Date Created in 2018/11/7 19:54
 */
@Data
public class User implements Serializable {
	private Long id;
	private String username;
	private String name;
	private String password;
	private String phone;
}
