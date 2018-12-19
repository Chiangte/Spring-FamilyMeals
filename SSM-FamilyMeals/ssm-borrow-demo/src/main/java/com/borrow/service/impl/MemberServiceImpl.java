package com.borrow.service.impl;

import com.borrow.mapper.BorrowMapper;
import com.borrow.mapper.MemberMapper;
import com.borrow.pojo.Member;
import com.borrow.service.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
/**
 * @Author Awan
 * @Description //TODO 会员业务访问接口实现类
 * @Date Created in 20:39 2018/12/3
 */
@Service
public class MemberServiceImpl implements MemberService {
	private final Logger LOG = Logger.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private BorrowMapper borrowMapper;
	
	@Override
	public PageInfo<Member> getMemberList(Integer pageNum, Integer pageSize, String keyword) {
		LOG.info("分页查询会员集合，页码：" + pageNum + "，页大小：" + pageSize + "，会员姓名关键字：" + keyword);
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(memberMapper.findByLikeName(keyword));
	}

	@Override
	public Member getMemberById(Long id) {
		LOG.info("按ID：" + id + "查询会员");
		return memberMapper.findById(id);
	}
	
	@Override
	public boolean isUniqueByIdentityCard(Long id, String identityCard) {
		LOG.info("按ID：" + id + "，身份证号：" + identityCard + "查询是否唯一");
		return memberMapper.countByIdentityCard(id, identityCard) == 0;
	}
	
	@Override
	public boolean isUniqueByPhone(Long id, String phone) {
		LOG.info("按ID：" + id + "，电话号码：" + phone + "查询是否唯一");
		return memberMapper.countByPhone(id, phone) == 0;
	}
	
	@Override
	public boolean updateMember(Member member) {
		LOG.info("修改会员，会员ID：" + member.getId() + "，姓名：" + member.getName() + "，身份证号：" + member.getIdentityCard() + "，电话：" + member.getPhone());
		return memberMapper.update(member) == 1;
	}
	
	@Override
	public boolean deleteMemberById(Long id) {
		int rows = 0;
		long count = borrowMapper.countByMidAndReturnDateIsNull(id);
		if (count > 0) {
			LOG.info("会员ID：" + id + "有借阅的图书未归还，不能删除");
			return false;
		}
		
		LOG.info("会员ID：" + id + "没有借阅的图书未归还，可以删除");
		borrowMapper.removeByMid(id);
		//再删除会员信息
		rows = memberMapper.remove(id);
		return rows == 1;
	}
	
	@Override
	public boolean addMember(Member member) {
		LOG.info("添加会员。");
		member.setCreateTime(new Date());
		return memberMapper.save(member) == 1;
	}
}
