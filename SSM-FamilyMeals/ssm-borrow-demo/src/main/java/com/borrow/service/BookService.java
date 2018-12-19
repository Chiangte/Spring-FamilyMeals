/**
 * 
 */
package com.borrow.service;

import com.borrow.pojo.Book;
import com.github.pagehelper.PageInfo;

/**
 * @Author Awan
 * @Description //TODO 图书业务访问接口
 * @Date Created in 20:37 2018/12/3
 */
public interface BookService {
	/**
	 * 分页查询图书
	 * @param pageNum 页码
	 * @param pageSize 页大小
	 * @param keyword 书名关键字
	 * @return 图书集合
	 */
	PageInfo<Book> getBookList(Integer pageNum, Integer pageSize, String keyword);

	/**
	 * 按图书ID删除图书
	 * @param id 图书ID
	 * @return 删除是否成功。true-成功，false-失败
	 */
	boolean deleteBookById(Long id);

	/**
	 * 检查书名是否唯一
	 * @param id 图书ID
	 * @param name 书名
	 * @return 是否唯一。true-唯一，false-不唯一
	 */
	boolean isUniqueByName(Long id, String name);

	/**
	 * 添加图书
	 * @param book 图书对象
	 * @return 添加是否成功。true-成功，false-失败
	 */
	boolean addBook(Book book);

	/**
	 * 按图书ID获取图书对象
	 * @param id 图书ID
	 * @return 图书对象，如果ID不存在，返回null
	 */
	Book getBookById(Long id);

	/**
	 * 修改图书
	 * @param book 图书对象
	 * @return 修改是否成功。true-成功，false-失败
	 */
	boolean updateBook(Book book);

	/**
	 * 分页条件查询允许借阅的图书数据
	 * @param pageNum 页码
	 * @param pageSize 页大小
	 * @param keyword 图书名称关键字
	 * @return 图书分页对象
	 */
	PageInfo<Book> getAllowBorrowBookList(Integer pageNum, Integer pageSize, String keyword);
}
