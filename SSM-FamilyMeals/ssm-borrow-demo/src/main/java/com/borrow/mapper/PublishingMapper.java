/**
 * 
 */
package com.borrow.mapper;

import com.borrow.pojo.Publishing;

import java.util.List;

/**
 * @Author Awan
 * @Description //TODO 出版社数据访问接口
 * @Date 19:06 2018/12/3
 */
public interface PublishingMapper {

	/**
	 * 查询所有出版社
	 * @return 出版社集合，即使没有查询到任何出版社，返回集合长度0
	 */
	List<Publishing> findAll();

}
