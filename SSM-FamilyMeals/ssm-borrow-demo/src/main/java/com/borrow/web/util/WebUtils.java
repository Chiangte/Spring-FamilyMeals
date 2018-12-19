package com.borrow.web.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;

/**
 * @Author Awan
 * @Description //TODO  常量工具类
 * @Date Created in  13:42 2018/12/4
 */
public abstract class WebUtils extends org.springframework.web.util.WebUtils {
	public static void setSession(String name, Object value) {
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		if (ra != null) {
			ra.setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getSession(String name) {
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		if (ra != null) {
			return (T)ra.getAttribute(name, RequestAttributes.SCOPE_SESSION);
		}
		return null;
	}
	
	
	public static void removeSession(String... names) {
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		if (ra != null) {
			for (String name : names) {
				ra.removeAttribute(name, RequestAttributes.SCOPE_SESSION);
			}
		}
	}
	
	public static void sessionToRequest(String name) {
		sessionToRequest(name, true);
	}
	
	public static void sessionToRequest(String name, boolean isRemove) {
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		if (ra != null) {
			Object value = ra.getAttribute(name, RequestAttributes.SCOPE_SESSION);
			if (value != null) {
				ra.setAttribute(name, value, RequestAttributes.SCOPE_REQUEST);
				if (isRemove) {
					ra.removeAttribute(name, RequestAttributes.SCOPE_SESSION);
				}
			}
		}
	} 
	
	public static void setRequest(String name, Object value) {
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		if (ra != null) {
			ra.setAttribute(name, value, RequestAttributes.SCOPE_REQUEST);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getRequest(String name) {
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		if (ra != null) {
			return (T)ra.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
		}
		return null;
	}
	
	public static <T> T getValue(String name, T value, T defaultValue) {
		if (value == null) {
			value = getSession(name);
			if (value == null) {
				value = defaultValue;
			}
		}
		return value;
	}

	public static String getRealPath(String path) throws FileNotFoundException {
        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        return org.springframework.web.util.WebUtils.getRealPath(servletContext, path);
	}
}
