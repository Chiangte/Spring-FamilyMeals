package com.borrow.service.impl;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borrow.mapper.BookMapper;
import com.borrow.mapper.BorrowMapper;
import com.borrow.pojo.Book;
import com.borrow.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Author Awan
 * @Description //TODO 图书业务访问接口实现类
 * @Date Created in 20:39 2018/12/3
 */
@Service
public class BookServiceImpl implements BookService {

	private Logger LOG = Logger.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private BorrowMapper borrowMapper;

	@Override
	public PageInfo<Book> getBookList(Integer pageNum, Integer pageSize, String keyword) {
		LOG.info("分页查询图书集合，页码：" + pageNum + "，页大小：" + pageSize + "，书名关键字：" + keyword);
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(bookMapper.findByLikeName(keyword));
	}
	
	@Override
	public boolean deleteBookById(Long id) {
		int rows = 0;
		long count = borrowMapper.countByBidAndReturnDateIsNull(id);
		if (count > 0) {
			LOG.info("图书ID：" + id + "被借阅尚未归还，不能删除");
			return false;
		}
		
		LOG.info("图书ID：" + id + "没有被借阅尚未归还，可以删除");
		//先删除借阅信息
		borrowMapper.removeByBid(id);
		//再删除会员信息
		rows = bookMapper.remove(id);
		return rows == 1;
	}
	
	@Override
	public boolean isUniqueByName(Long id, String name) {
		LOG.info("按ID：" + id + "，书名：" + name + "查询是否唯一");
		return bookMapper.countByName(id, name) == 0;
	}
	
	@Override
	public boolean addBook(Book book) {
		LOG.info("添加图书。");
		return bookMapper.save(book) == 1;
	}
	
	@Override
	public Book getBookById(Long id) {
		LOG.info("按ID：" + id + "查询图书");
		return bookMapper.findById(id);
	}
	
	@Override
	public boolean updateBook(Book book) {
		LOG.info("修改图书。");
		return bookMapper.update(book) == 1;
	}

	@Override
	public PageInfo<Book> getAllowBorrowBookList(Integer pageNum, Integer pageSize, String keyword) {
		LOG.info("分页查询允许借阅的图书集合，页码：" + pageNum + "，页大小：" + pageSize + "，书名关键字：" + keyword);
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(bookMapper.findAllowByName(keyword));
	}
}
