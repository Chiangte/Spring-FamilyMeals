package com.example.dao;

import com.example.entity.Area;

import java.util.List;

/**
 * @Author Awan
 * @Description //TODO
 * @Date 19:16 2018/11/27
 **/
public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    /**
     * 查询全部
     * @return
     */
    List<Area> selectAreaAll();
}