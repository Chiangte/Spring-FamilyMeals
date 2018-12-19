package com.example.service;

import com.example.entity.Area;

import java.util.List;

/**
 * @Author: Awan
 * @Description:
 * @Date Created in 18:34  2018/11/23
 */
public interface AreaServcie {
	int deleteByPrimaryKey(Integer id);

	int insert(Area record);

	int insertSelective(Area record);

	Area selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Area record);

	int updateByPrimaryKey(Area record);

	List<Area> selectAreaAll(Integer pageNum, Integer pageSize);
}
