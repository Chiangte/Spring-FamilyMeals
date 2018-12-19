package com.ljt.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: User
 * @Author: Awan
 * @Date Created in 2018/11/7 19:54
 */
@Data
public class Blog implements Serializable {
	private Long id;
	private String title;
	private String content;
	private User user;
	private Date createTime;

}
