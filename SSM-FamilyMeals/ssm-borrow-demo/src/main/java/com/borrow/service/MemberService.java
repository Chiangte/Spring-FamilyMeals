/**
 * 
 */
package com.borrow.service;

import com.borrow.pojo.Member;
import com.github.pagehelper.PageInfo;

/**
 * @Author Awan
 * @Description //TODO 会员业务访问接口
 * @Date  Created in 20:38 2018/12/3
 */
public interface MemberService {
	/**
	 * 分页查询会员
	 * @param pageNum 页码
	 * @param pageSize 页大小
	 * @param keyword 姓名关键字
	 * @return 会员集合
	 */
	PageInfo<Member> getMemberList(Integer pageNum, Integer pageSize, String keyword);
	
	/**
	 * 查询会员
	 * @param id 会员ID
	 * @return 会员对象。如果会员ID不存在，返回null
	 */
	Member getMemberById(Long id);

	/**
	 * 检查身份证号是否唯一
	 * @param id 会员ID
	 * @param identityCard 身份证号
	 * @return 是否唯一。true-唯一，false-不唯一
	 */
	boolean isUniqueByIdentityCard(Long id, String identityCard);

	/**
	 * 检查电话号码是否唯一
	 * @param id 会员ID
	 * @param phone 电话号码
	 * @return 是否唯一。true-唯一，false-不唯一
	 */
	boolean isUniqueByPhone(Long id, String phone);
	
	/**
	 * 修改会员
	 * @param member 会员对象
	 * @return 修改是否成功。true-成功，false-失败
	 */
	boolean updateMember(Member member);

	/**
	 * 删除会员
	 * @param id 会员ID
	 * @return 删除是否成功。true-成功，false-失败，当前会员有借阅的图书未归还
	 */
	boolean deleteMemberById(Long id);

	/**
	 * 添加会员
	 * @param member 会员对象
	 * @return 添加是否成功。true-成功，false-失败
	 */
	boolean addMember(Member member);
}
