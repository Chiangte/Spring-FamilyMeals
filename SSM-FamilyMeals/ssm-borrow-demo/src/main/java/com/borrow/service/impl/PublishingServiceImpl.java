package com.borrow.service.impl;

import com.borrow.mapper.PublishingMapper;
import com.borrow.pojo.Publishing;
import com.borrow.service.PublishingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author Awan
 * @Description //TODO 出版社业务访问接口实现类
 * @Date Created in 20:40 2018/12/3
 */
@Service
public class PublishingServiceImpl implements PublishingService {
	private final Logger LOG = Logger.getLogger(PublishingServiceImpl.class);
	
	@Autowired
	private PublishingMapper publishingMapper;
	
	@Override
	public List<Publishing> getPublishingAll() {
		LOG.info("查询所有出版社");
		List<Publishing> publishings = publishingMapper.findAll();
		LOG.info("出版社总数：" + publishings.size());
		return publishings;
	}
}
