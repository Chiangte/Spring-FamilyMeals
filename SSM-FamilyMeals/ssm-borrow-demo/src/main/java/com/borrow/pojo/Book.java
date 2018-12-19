package com.borrow.pojo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Awan
 * @Description //TODO Book实体层
 * @Date Created in 18:52 2018/12/3
 */
@Getter
@Setter
public class Book {
	/**
	 * 图书ID
	 */
	private Long id;
	/**
	 * 图书名称
	 */
	private String name;
	/**
	 * 图书作者
	 */
	private String author;
	/**
	 * 图书价格
	 */
	private BigDecimal price;
	/**
	 * 出版社
	 */
	private Publishing publishing;
	/**
	 * 出版日期
	 */
	private Date createDate;
	/**
	 * 封面图片
	 */
	private String cover;
	/**
	 * 图书摘要
	 */
	private String summary;
	@Override
	public String toString() {
		return "{\"id\": " + id + ", \"name\": \"" + name + "\", \"author\": \"" + author + "\", \"price\": " + price + "}";
	}
}
