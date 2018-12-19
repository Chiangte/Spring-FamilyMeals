/**
 * 
 */
package com.borrow.mapper;

import com.borrow.pojo.Borrow;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
/**
 * @Author Awan
 * @Description //TODO 借阅数据访问接口
 * @Date Created in 19:06 2018/12/3
 */
public interface BorrowMapper {

	/**
	 * 按会员ID查询借阅总数
	 * @param id 会员ID
	 * @return 借阅总数
	 */
	long countByMidAndReturnDateIsNull(@Param("id") Long id);
	
	/**
	 * 按会员ID删除借阅信息
	 * @param id 会员ID
	 * @return 删除影响数据库表的行数
	 */
	int removeByMid(@Param("id") Long id);

	/**
	 * 按图书ID查询借阅总数
	 * @param id 图书ID
	 * @return 借阅总数
	 */
	long countByBidAndReturnDateIsNull(Long id);

	/**
	 * 按图书ID删除借阅信息
	 * @param id 图书ID
	 * @return 删除影响数据库表的行数
	 */
	int removeByBid(Long id);

	
	/**
	 * 按书名或会员姓名关键字分页查询借阅信息
	 * @param keyword 书名或会员姓名关键字
	 * @return 借阅集合
	 */
	List<Borrow> findByLikeName(@Param("keyword") String keyword);

	/**
	 * 按借阅ID删除借阅信息
	 * @param id 借阅ID
	 * @return 删除影响的数据库表的行数
	 */
	int remove(@Param("id") Long id);

	/**
	 * 按借阅ID修改归还时间
	 * @param id 借阅ID
	 * @param returnDate 归还时间
	 * @return 修改影响的数据库表的行数
	 */
	int update(@Param("id") Long id, @Param("returnDate") Date returnDate);

	/**
	 * 添加借阅
	 * @param mid 会员ID
	 * @param borrowDate 借阅日期
	 * @param bookIds 图书ID数组
	 * @return 添加影响数据库表的行数
	 */
	int save(@Param("mid") Long mid, @Param("borrowDate") Date borrowDate, @Param("bookIds") Long[] bookIds);

}
