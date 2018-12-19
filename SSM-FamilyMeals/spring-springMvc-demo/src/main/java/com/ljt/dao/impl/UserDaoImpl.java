package com.ljt.dao.impl;

import com.ljt.dao.UserDao;
import com.ljt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Title: UserDaoImpl
 * @Author: Awan
 * @Date Created in 2018/11/7 20:35
 */
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(User user) {
		String sql = "insert into t_user values(null, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,
				user.getUsername(),
				user.getPassword(),
				user.getName(),
				user.getPhone());
	}

	@Override
	public int remove(Long id) {
		String sql = "delete from t_user where id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int update(User user) {
		String sql = "update t_user set username = ?, password = ?, name = ?, phone = ? where id = ?";
		return jdbcTemplate.update(sql,
				user.getUsername(),
				user.getPassword(),
				user.getName(),
				user.getPhone(),
				user.getId());
	}

	@Override
	public User findById(Long id) {
		String sql = "select id, username, password, name, phone from t_user where id = ?";
		//做法一
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getLong("id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setName(rs.getString("name"));
					user.setPhone(rs.getString("phone"));
					return user;
				}
				return null;
			}
		}, id);
	}

	@Override
	public List<User> findAll() {
		String sql = "select id, username, password, name, phone from t_user";
		//做法一
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public long count() {
		String sql = "select count(1) from t_user";
		return jdbcTemplate.queryForObject(sql, Long.class);
	}
}
