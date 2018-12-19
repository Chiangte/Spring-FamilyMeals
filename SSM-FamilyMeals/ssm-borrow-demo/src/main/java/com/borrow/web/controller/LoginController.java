/**
 * 
 */
package com.borrow.web.controller;

import com.borrow.pojo.User;
import com.borrow.service.UserService;
import com.borrow.web.util.Constants;
import com.borrow.web.util.WebUtils;
import com.borrow.web.vo.LoginVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @Author Awan
 * @Description //TODO 登录控制器
 * @Date Created in 15:13 2018/12/4
 **/
@Controller
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1324016644143681025L;
	
	private final Logger LOG = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * 导航到登陆页面
	 * @param loginVo
	 * @return
	 */
	@GetMapping("login")
	public String login(LoginVo loginVo) {
		LOG.info("导航到登录页面");
		return "login";
	}

	/**
	 * @Author Awan
	 * @Description //TODO 执行登录
	 * @Date 17:53 2018/12/5
	 * @Param [loginVo, errors, model]
	 * @return java.lang.String
	 **/
	@PostMapping("login")
	public String doPost(@Valid LoginVo loginVo, Errors errors, Model model) {
		LOG.info("执行登录");
		
		//1.验证框架验证是否通过
		if (errors.hasErrors()) {
			return "login";
		}
		
		//2.判断验证码是否正确
		String genKaptcha = WebUtils.getSession(KAPTCHA_SESSION_KEY);
		if (!loginVo.getKaptcha().equalsIgnoreCase(genKaptcha)) {
			LOG.warn("登录失败：验证码错误");
			model.addAttribute(Constants.MSG, "验证码错误");
			return "login";
		}
		
		//3.登录
		User loginUser = userService.getUserByUsername(loginVo.getUsername());
		
		//4.判断、记录日志、转向
		String msg;
		if (null == loginUser) {
			msg = "用户名不存在";
		} else if (loginUser.getPassword().equals(loginVo.getPassword())) {
			WebUtils.setSession(Constants.LOGIN_USER, loginUser);
			LOG.info("用户名" + loginVo.getUsername() + "登录成功");
			return "redirect:home";
		} else {
			msg = "密码错误";
		}
		
		LOG.warn("登录失败：" + msg);
		model.addAttribute(Constants.MSG, msg);
		return "login";
	}
}
