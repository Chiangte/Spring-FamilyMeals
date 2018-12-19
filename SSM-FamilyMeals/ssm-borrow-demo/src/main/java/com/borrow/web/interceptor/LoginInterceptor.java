/**
 * 
 */
package com.borrow.web.interceptor;

import com.borrow.web.util.Constants;
import lombok.Setter;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查过滤器
 * @author Y2T115
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Setter
	private String redirectUrl;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object loginUser = request.getSession().getAttribute(Constants.LOGIN_USER);
		if (null == loginUser) {
			if (redirectUrl == null) {
				redirectUrl = "/login";
			} else if (!redirectUrl.startsWith("/")) {
				redirectUrl = "/" + redirectUrl;
			}
			/**
			 * 如果请求的资源地址不在排除的集合中，且用户未登录，重定向到指定路径的资源
			 */
			//获取协议
			String path = request.getScheme()
					//获取服务器名称(IP地址)
					+ "://" + request.getServerName()
					//获取端口号
					+ ":" + request.getServerPort()
					//获取应用上下文路径
					+ request.getContextPath()
					+ redirectUrl;
			//例如：http://localhost:80/com-book/login
			response.sendRedirect(path);
			return false;
		}
		return true;
	}
}
