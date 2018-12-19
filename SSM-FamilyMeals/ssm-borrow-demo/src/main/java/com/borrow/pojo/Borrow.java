package com.borrow.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author Awan
 * @Description //TODO 借阅 实体层
 * @Date Created in 18:55 2018/12/3
 */
@Data
public class Borrow {
	/**
	 *  图书借阅ID
	 */
	private Long id;
	/**
	 * 会员
	 */
	private Member member;
	/**
	 * 图书
	 */
	private Book book;
	/**
	 * 借阅日期
	 */
	private Date borrowDate;
	/**
	 * 归还日期
	 */
	private Date returnDate;
}
