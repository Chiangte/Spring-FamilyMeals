package com.ljt.service.impl;

import com.ljt.dao.BlogDao;
import com.ljt.entity.Blog;
import com.ljt.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Title: UserServiceImpl
 * @Author: Awan 逻辑业务层
 * @Date Created in 2018/11/7 20:48
 */
@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogDao blogDao;
	
	@Override
	public boolean addBlog(Blog blog) {
		blog.setCreateTime(new Date());
		return blogDao.save(blog) == 1;
	}

	@Override
	public boolean deleteBlogById(Long id) {
		return blogDao.remove(id) == 1;
	}

	@Override
	public boolean updateBlog(Blog blog) {
		return blogDao.update(blog) == 1;
	}

	@Override
	public Blog getBlogById(Long id) {
		return blogDao.findById(id);
	}

	@Override
	public List<Blog> getBlogAll() {
		return blogDao.findAll();
	}

	@Override
	public long getBlogCount() {
		return blogDao.count();
	}

}
