package com.borrow.web.controller;

import com.borrow.web.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author Awan
 * @Description //TODO 注销控制器
 * @Date Created in 15:15 2018/12/4
 */
@Controller
public class LogoutController {
	/**
	 * 注销
	 * @param session
	 * @return
	 */
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.setAttribute(Constants.SESSION_DESTROYED_TYPE, true);
		session.invalidate();
		return "redirect:login";
	}
}
