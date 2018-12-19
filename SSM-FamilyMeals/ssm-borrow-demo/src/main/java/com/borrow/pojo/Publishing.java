package com.borrow.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author Awan
 * @Description //TODO 出版社实体层
 * @Date Created in 18:58 2018/12/3
 */
@Getter @Setter
@NoArgsConstructor  //无参构造函数 注解
public class Publishing {
	/**
	 * 出版社ID
	 */
	private Long id;
	/**
	 * 出版社名
	 */
	private String name;
	
	public Publishing(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		//{"id": 1, "name": "人民教育出版社"}
		return "{\"id\": " + id + ", \"name\": \"" + name + "\"}";
	}
}
