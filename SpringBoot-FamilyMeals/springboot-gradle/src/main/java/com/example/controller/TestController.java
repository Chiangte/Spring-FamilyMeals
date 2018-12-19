package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这里的@RestController相当于 @ResponseBody+@Controller
 * 使用@RestController 相当于使每个方法都加上了 @ResponseBody 注解
 * @Author: Awan
 * @Description:
 * @Date Created in 15:27  2018/11/26
 */
@RestController
public class TestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	/**
	 * @Author Awan
	 * @Description  TODO 这里的@GetMapping相当于@RequestMapping(value = "/hello", method = RequestMethod.GET)
	 * @Date 11:28 2018/11/21
	 * @Param []
	 * @return java.lang.String
	 **/
	@GetMapping("hello")
	public String hello() throws  InterruptedException {
		LOGGER.info("Welcome to the AWan JudoMall");
		//Thread.sleep(5000);
		return "i love you";
	}
}
