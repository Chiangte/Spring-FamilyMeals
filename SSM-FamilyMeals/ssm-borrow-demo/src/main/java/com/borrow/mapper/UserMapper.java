/**
 * 
 */
package com.borrow.mapper;

import com.borrow.pojo.User;


/**
 * @Author:  Awan
 * @Description: 管理员数据访问接口
 * @Date Created in 19:07 2018/12/3
 */
public interface UserMapper {
	/**
	 * 按用户名查询管理员
	 * @param username 用户名
	 * @return 管理员对象。如果用户名不存在，返回null
	 */
	User findByUsername(String username);
}
