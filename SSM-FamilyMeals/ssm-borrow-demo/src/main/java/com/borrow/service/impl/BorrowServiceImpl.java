package com.borrow.service.impl;

import com.borrow.mapper.BorrowMapper;
import com.borrow.mapper.MemberMapper;
import com.borrow.pojo.Borrow;
import com.borrow.service.BorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
 * @Author Awan
 * @Description //TODO 借阅业务访问接口实现类
 * @Date Created in 20:39 2018/12/3
 */
@Service
public class BorrowServiceImpl implements BorrowService {
	private Logger LOG = Logger.getLogger(BorrowServiceImpl.class);
	
	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public PageInfo<Borrow> getBorrowList(Integer pageNum, Integer pageSize, String keyword) {
		LOG.info("分页查询借阅集合，页码：" + pageNum + "，页大小：" + pageSize + "，书名或会员姓名关键字：" + keyword);
		PageHelper.startPage(pageNum, pageSize);
		
		List<Borrow> borrows = borrowMapper.findByLikeName(keyword);
		
		if (keyword != null && keyword.length() > 0) {
			for (Borrow borrow : borrows) {
				String bookName = borrow.getBook().getName().replace(keyword, "<b style=\"color:#F00\">" + keyword + "</b>");
				borrow.getBook().setName(bookName);
				
				String memberName = borrow.getMember().getName().replace(keyword, "<b style=\"color:#F00\">" + keyword + "</b>");
				borrow.getMember().setName(memberName);
			}
		}
		
		return new PageInfo<>(borrows);
	}

	@Override
	public boolean deleteBorrowById(Long id) {
		LOG.info("按ID：" + id + "删除借阅信息");
		return borrowMapper.remove(id) == 1;
	}
	
	@Override
	public boolean updateBorrowById(Long id) {
		LOG.info("按ID：" + id + "修改归还时间");
		return borrowMapper.update(id, new Date()) == 1;
	}
	
	@Override
	public boolean addBorrow(String identityCard, Long[] bookIds) {
		LOG.info("添加借阅：身份证号" + identityCard + "，图书ID：" + Arrays.asList(bookIds));
		Long id = memberMapper.findIdByIdentityCard(identityCard);
		return borrowMapper.save(id, new Date(), bookIds) == bookIds.length;
	}
}
