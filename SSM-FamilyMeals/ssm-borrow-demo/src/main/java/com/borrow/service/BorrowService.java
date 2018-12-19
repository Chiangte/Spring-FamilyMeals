/**
 * 
 */
package com.borrow.service;

import com.borrow.pojo.Borrow;
import com.github.pagehelper.PageInfo;

/**
 * @Author Awan
 * @Description //TODO 借阅业务访问接口
 * @Date  Created in 20:37 2018/12/3
 */
public interface BorrowService {
	/**
	 * 按书名或会员姓名分页查询借阅信息
	 * @param pageNum 页码
	 * @param pageSize 页大小
	 * @param keyword 书名或会员姓名关键字
	 * @return 借阅集合
	 */
	PageInfo<Borrow> getBorrowList(Integer pageNum, Integer pageSize, String keyword);

	/**
	 * 按借阅ID删除借阅信息
	 * @param id 借阅ID
	 * @return 删除是否成功。true-成功，false-失败
	 */
	boolean deleteBorrowById(Long id);

	/**
	 * 按借阅ID修改归还时间
	 * @param id 借阅ID
	 * @return 修改是否成功。true-成功，false-失败
	 */
	boolean updateBorrowById(Long id);

	/**
	 * 添加图书借阅信息
	 * @param identityCard 身份证号
	 * @param bookIds 图书ID数组
	 * @return 添加是否成功。true-成功，false-失败
	 */
	boolean addBorrow(String identityCard, Long[] bookIds);

}
