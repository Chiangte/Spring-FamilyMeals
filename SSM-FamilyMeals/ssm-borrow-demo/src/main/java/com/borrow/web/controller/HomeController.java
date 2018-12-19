/**
 * 
 */
package com.borrow.web.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServlet;


/**
 * @Author Awan
 * @Description //TODO 主页控制器
 * @Date Created in 15:12 2018/12/4
 **/
@Controller
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = -2489752544448308799L;
	
	private final Logger LOG = Logger.getLogger(HomeController.class);

	/**
	 * 导航到主页面
	 * @return
	 */
	@GetMapping("home")
	public String home() {
		LOG.info("导航到主页面");
		return "home";
	}
}
