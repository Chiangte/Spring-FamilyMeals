package com.proxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Title: CglibProxyTIme
 * @Author: Awan
 * @Date Created in 2018/11/9 19:00
 */
public class CglibProxyTime implements MethodInterceptor {
	private Object targetObject;

	public CglibProxyTime(Object targetObject) {
		this.targetObject = targetObject;
	}

	public Object newProxyInstance() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(targetObject.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		long start = System.nanoTime();
		System.out.println("开始执行");
		Object result = method.invoke(targetObject, args);
		System.out.println("执行结束，耗时：" + (System.nanoTime() - start) + "纳秒");
		return result;
	}
}
