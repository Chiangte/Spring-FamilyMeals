/**
 * 
 */
package com.borrow.service;

import com.borrow.pojo.User;

/**
 * @Author Awan
 * @Description //TODO 管理员业务访问接口
 * @Date  Created in 20:38 2018/12/3
 */
public interface UserService {
	/**
	 * 按用户名查询管理员
	 * @param username 用户名
	 * @return 管理员对象。如果用户名不存在，返回null
	 */
	User getUserByUsername(String username);
}
