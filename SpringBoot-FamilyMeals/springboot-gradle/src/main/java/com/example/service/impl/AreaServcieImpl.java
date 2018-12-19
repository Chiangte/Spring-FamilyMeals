package com.example.service.impl;

import com.example.dao.AreaMapper;
import com.example.entity.Area;
import com.example.service.AreaServcie;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Awan
 * @Description:
 * @Date Created in 18:34  2018/11/23
 */
@Service
public class AreaServcieImpl implements AreaServcie, Serializable {

	private AreaMapper areaMapper;

	@Autowired
	public  AreaServcieImpl(AreaMapper areaMapper){
		this.areaMapper = areaMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return areaMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Area record) {
		return areaMapper.insert(record);
	}


	//@Transactional
	@Override
	public int insertSelective(Area record) {

		/*
		 * 	    record.setAddress("test");
		 * 		record.setPostalcode(88888);
		 * 		record.setType(3);
		 * 	//以下是不推荐的处理
		 *         int i=0;
		 *         try {
		 *             i =
		 *             i = areaMapper.insertSelective(record);
		 *         }catch (Exception e){
		 *             logger.error("AreaServiceImpl insertSelective error："+e.getMessage());
		 *         }*/

		/**
		 *                 上面的插入操作失败出错了 不会回滚
		 * 	          因为把错误捕捉了，事物没检测到异常就不会回滚。
		 * 	         在catch里加throw new RuntimeException(). 就能回滚
		 * 		可是那么多业务方法每个设计修改的操作都加，代码繁琐,怎么进行处理呢？
		 * 	 		在这里用到上面的AOP切入处理，错误不用管，直接抛，抛到控制层进行处理，这样的话，接口调用的时候，
		 * 	 		出错了，接口不会什么都不返回，而是会返回给你错误代码，以及错误信息，便于开发人员查错。
		 */
		return areaMapper.insertSelective(record);
	}

	@Override
	public Area selectByPrimaryKey(Integer id) {
		return areaMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Area record) {
		return areaMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Area record) {
		return areaMapper.updateByPrimaryKey(record);
	}

	/**
	 *  分页核心代码
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<Area> selectAreaAll(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		//直接查询全部，分页插件代替做分页，无需担心性能问题自动补上limit;
		List<Area> areaList = areaMapper.selectAreaAll();
		return areaList;
	}
}
