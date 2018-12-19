/**
 * 
 */
package com.borrow.service;

import com.borrow.pojo.Publishing;

import java.util.List;

/**
 * @Author Awan
 * @Description //TODO 出版社业务访问接口
 * @Date  Created in 20:38 2018/12/3
 */
public interface PublishingService {

	/**
	 * 查询所有出版社
	 * @return 出版社集合，即使没有查询任何出版社，返回集合长度为0
	 */
	List<Publishing> getPublishingAll();
}
