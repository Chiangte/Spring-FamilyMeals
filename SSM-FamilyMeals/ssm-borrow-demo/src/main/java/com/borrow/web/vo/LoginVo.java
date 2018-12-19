package com.borrow.web.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author Awan
 * @Description //TODO 登录业务对象
 * 是存活在业务层的，是业务逻辑使用的，它存活的目的就是为数据提供一个生存的地方。
 * @Date Created in  14:10 2018/12/4
 */
@Data
public class LoginVo {
	@NotBlank(message="用户名不能为空")
	/**
	 * 用户名
	 */
	private String username;
	@NotBlank(message="密码不能为空")
	/**
	 *  密码
	 */
	private String password;
	@NotBlank(message="验证码不能为空")
	/**
	 * 验证码
	 */
	private String kaptcha;
}
