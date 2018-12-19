package com.ljt.service;

import com.ljt.entity.Blog;

import java.util.List;
/**
 * @Author Awan
 * @Description //TODO
 * @Date 12:52 2018/11/7
 * @Param
 * @return
 **/
public interface BlogService {
	/**
	 *  添加blog
	 * @param blog
	 * @return
	 */
	boolean addBlog(Blog blog);

	/**
	 *  根据ID删除blog
	 * @param id
	 * @return
	 */
	boolean deleteBlogById(Long id);

	/**
	 *  更新blog
	 * @param blog
	 * @return
	 */
	boolean updateBlog(Blog blog);

	/**
	 *  根据id查询blog
	 * @param id
	 * @return
	 */
	Blog getBlogById(Long id);

	/**
	 * 查询所有blog
	 * @return
	 */
	List<Blog> getBlogAll();

	/**
	 * 查询blog条数
	 * @return
	 */
	long getBlogCount();
}
