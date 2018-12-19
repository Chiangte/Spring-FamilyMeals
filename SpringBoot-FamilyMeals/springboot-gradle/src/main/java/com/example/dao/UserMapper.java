package com.example.dao;

import com.example.entity.User;

import java.util.List;

/**
 * @Author Awan
 * @Description //TODO
 * @Date 19:15 2018/11/27
 **/
public interface UserMapper {
    /**
     *  删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *   增加
     * @param record
     * @return
     */
    int insert(User record);

    /**
     *  增加
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     *  查询
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *  更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     *  查询所有
     * @return
     */
    List<User> getUserAll();
}