package com.borrow.mapper;

import com.borrow.pojo.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Awan
 * @Description //TODO 会员数据访问接口
 * @Date Created in 19:06 2018/12/3
 */
public interface MemberMapper {
	/**
	 * 分页查询会员
	 * @param keyword 姓名关键字
	 * @return 会员集合
	 */
	List<Member> findByLikeName(@Param("keyword") String keyword);

	/**
	 * 按会员ID和身份证号查询会员数量
	 * @param id 会员ID
	 * @param identityCard 身份证号
	 * @return 会员数量
	 */
	long countByIdentityCard(@Param("id") Long id, @Param("identityCard") String identityCard);

	/**
	 * 按会员ID和电话号码查询会员数量
	 * @param id 会员ID
	 * @param phone 电话号码
	 * @return 会员数量
	 */
	long countByPhone(@Param("id") Long id, @Param("phone") String phone);

	/**
	 * 保存会员
	 * @param member 会员对象
	 * @return 保存影响数据库表的行数
	 */
	int save(Member member);
	
	/**
	 * 查询会员
	 * @param id 会员ID
	 * @return 会员对象。如果会员ID不存在，返回null
	 */
	Member findById(@Param("id") Long id);

	/**
	 * 修改会员信息
	 * @param member 会员对象
	 * @return 修改影响数据库表的行数
	 */
	int update(Member member);

	/**
	 * 删除会员
	 * @param id 会员ID
	 * @return 删除影响数据库表的行数
	 */
	int remove(@Param("id") Long id);

	/**
	 * 按身份证号查询会员ID
	 * @param identityCard 身份证号
	 * @return 会员ID
	 */
	Long findIdByIdentityCard(@Param("identityCard") String identityCard);
}
