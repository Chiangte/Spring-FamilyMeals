/**
 * 
 */
package com.borrow.web.listener;

import com.borrow.pojo.User;
import com.borrow.web.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author Awan
 * @Description //TODO HttpSession销毁监听器
 * @Date 13:40 2018/12/4
 **/
@WebListener
public class HttpSessionDestroyedListener implements HttpSessionListener {
	private final Logger LOG = Logger.getLogger(HttpSessionDestroyedListener.class);

	/**
	 * @Author Awan
	 * @Description //TODO HttpSession被销毁时执行
	 * @Date 13:41 2018/12/4
	 * @Param [se]
	 * @return void
	 **/
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//1.获取当前正销毁的session对象
		HttpSession session = se.getSession();
		//2.获取登录用户对象
		User loginUser = (User)session.getAttribute(Constants.LOGIN_USER);
		if (loginUser != null) {
			//3.获取销毁方式
			Object sessionDestroyedType = session.getAttribute(Constants.SESSION_DESTROYED_TYPE);
			//4.记录注销日志
			LOG.info(String.format("用户名%s已注销，注销方式：%s", 
				loginUser.getUsername(),
				Boolean.TRUE.equals(sessionDestroyedType) ? "主动销毁" : "被动销毁"
			));
		}
	}
}