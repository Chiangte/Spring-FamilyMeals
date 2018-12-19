package com.borrow.pojo;

import lombok.Data;

import java.util.Date;
/**
 * @Author Awan
 * @Description //TODO 会员实体层
 * @Date Created in 18:56 2018/12/3
 */
@Data
public class Member {
	/**
	 * 会员ID
	 */
	private Long id;
	/**
	 * 会员姓名
	 */
	private String name;
	/**
	 * 会员身份证号
	 */
	private String identityCard;
	/**
	 * 会员电话
	 */
	private String phone;
	/**
	 * 会员创建时间
	 */
	private Date createTime;
}
