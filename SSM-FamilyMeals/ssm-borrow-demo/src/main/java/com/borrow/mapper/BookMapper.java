/**
 * 
 */
package com.borrow.mapper;

import com.borrow.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Awan
 * @Description //TODO 图书数据访问接口
 * @Date Created in 19:06 2018/12/3
 **/
public interface BookMapper {
	/**
	 * 按书名关键字模糊查询图书
	 * @param keyword 书名关键字
	 * @return 图书集合
	 */
	List<Book> findByLikeName(@Param("keyword") String keyword);

	/**
	 * 按ID删除图书
	 * @param id 图书ID
	 * @return 删除影响数据库表的行数
	 */
	int remove(@Param("id") Long id);

	/**
	 * 按图书ID和图书名称查询会员数量
	 * @param id 图书ID
	 * @param name 书名
	 * @return 图书数量
	 */
	long countByName(@Param("id") Long id, @Param("name") String name);

	/**
	 * 保存一个图书对象
	 * @param book 图书对象
	 * @return 保存影响数据库表的行数
	 */
	int save(Book book);

	/**
	 * 查询图书
	 * @param id 图书ID
	 * @return 图书对象。如果ID不存在，返回null
	 */
	Book findById(@Param("id") Long id);

	/**
	 * 修改一个图书对象
	 * @param book 图书对象
	 * @return 修改影响数据库表的行数
	 */
	int update(Book book);

	/**
	 * 按书名模糊查询允许借阅的图书集合
	 * @param keyword 书名关键字
	 * @return 图书集合
	 */
	List<Book> findAllowByName(@Param("keyword") String keyword);
}
