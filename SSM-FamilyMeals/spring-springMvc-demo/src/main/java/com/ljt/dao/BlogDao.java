package com.ljt.dao;

import com.ljt.entity.Blog;

import java.util.List;

public interface BlogDao {
	int save(Blog blog);
	int remove(Long id);
	int removeByUserId(Long uid);
	int update(Blog blog);
	Blog findById(Long id);
	List<Blog> findAll();
	long count();


}
