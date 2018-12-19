package com.ljt.web.controller;

import com.ljt.entity.Blog;
import com.ljt.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@PostMapping("add")
	public boolean add(Blog blog) {
		return blogService.addBlog(blog);
	}
	
	@PostMapping("delete/{id}")
	public boolean delete(@PathVariable Long id) {
		return blogService.deleteBlogById(id);
	}
	
	@PostMapping("update")
	public boolean update(Blog blog) {
		return blogService.updateBlog(blog);
	}
	
	@PostMapping("get/{id}")
	public Blog get(@PathVariable Long id) {
		return blogService.getBlogById(id);
	}
	
	@PostMapping("all")
	public List<Blog> all() {
		return blogService.getBlogAll();
	}
	
	@PostMapping("count")
	public long count() {
		return blogService.getBlogCount();
	}
}
