package com.borrow.web.controller;

import com.borrow.pojo.Publishing;
import com.borrow.service.PublishingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Awan
 * @Description //TODO 借阅控制器
 * @Date Created in 15:16 2018/12/4
 */
@Controller
public class PublishingController {
	private final Logger LOG = Logger.getLogger(PublishingController.class);
	
	@Autowired
	private PublishingService publishingService;
	
	@GetMapping("publishingList")
	@ResponseBody
	public List<Publishing> publishingList() {
		LOG.info("以JSON格式获取所有出版社数据");
		return publishingService.getPublishingAll();
	}
}
