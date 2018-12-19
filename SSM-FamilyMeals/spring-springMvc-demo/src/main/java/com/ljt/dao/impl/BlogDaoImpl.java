package com.ljt.dao.impl;

import com.ljt.dao.BlogDao;
import com.ljt.entity.Blog;
import com.ljt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Title: BlogDaoImpl
 * @Author: Awan
 * @Date Created in 2018/11/7 20:35
 */
@Repository
public class BlogDaoImpl implements BlogDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(Blog blog) {
		String sql = "insert into t_blog values(null, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, 
				blog.getTitle(), 
				blog.getContent(), 
				blog.getUser().getId(),
				new java.sql.Timestamp(blog.getCreateTime().getTime()));
	}

	@Override
	public int remove(Long id) {
		String sql = "delete from t_blog where id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int removeByUserId(Long uid) {
		String sql = "delete from t_blog where uid = ?";
		return jdbcTemplate.update(sql, uid);
	}

	@Override
	public int update(Blog blog) {
		String sql = "update t_blog set title = ?, content = ?, author = ? where id = ?";
		return jdbcTemplate.update(sql, 
				blog.getTitle(), 
				blog.getContent(), 
				blog.getUser().getId(),
				blog.getId());
	}

	@Override
	public Blog findById(Long id) {
		String sql = "select b.id bid, title, content, createTime,u.id uid, name from t_blog b inner join t_user u on u.id = b.id where b.id = ?";

		//1
		return jdbcTemplate.query(sql, new ResultSetExtractor<Blog>() {
			@Override
			public Blog extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Blog blog = new Blog();
					blog.setId(rs.getLong("id"));
					blog.setTitle(rs.getString("title"));
					blog.setContent(rs.getString("content"));
					blog.setCreateTime(rs.getTimestamp("createTime"));
					User user = new User();
					user.setId(rs.getLong("uid"));
					user.setName(rs.getString("name"));
					blog.setUser(user);
					return blog;
				}
				return null;
			}
		}, id);

		// 2
		//List<Blog> blogs =jdbcTemplate.query(sql,new BeanPropertyRowMapper(Blog.class) , id );
		//return blogs.size() > 0 ? blogs.get(0) : null;

		/*3 此方法若没有查询到结果 就会报错 必须返回一个结果 如果事有接口的情况下也蛮不错的*/
		//return  jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Blog>(Blog.class),id);

	}

	@Override
	public List<Blog> findAll() {
		String sql = "select b.id bid, title, content, createTime,u.id uid, name from t_blog b inner join t_user u on u.id = b.id";

		//1
		//return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Blog>(Blog.class));

		//2
		//List<Blog> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Blog.class));
		//return query;

		//3
		//return  jdbcTemplate.query(sql,new RowMapperResultSetExtractor<Blog>(new BeanPropertyRowMapper<>()));

		return jdbcTemplate.query(sql, new RowMapper<Blog>() {
			@Override
			public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
				Blog blog = new Blog();
				blog.setId(rs.getLong("bid"));
				blog.setTitle(rs.getString("title"));
				blog.setContent(rs.getString("content"));
				blog.setCreateTime(rs.getTimestamp("createTime"));
				User user = new User();
				user.setId(rs.getLong("uid"));
				user.setName(rs.getString("name"));
				blog.setUser(user);
				return blog;
			}
		});

	}

	@Override
	public long count() {
		String sql = "select count(1) from t_blog";
		return jdbcTemplate.queryForObject(sql, Long.class);
	}
}
