package com.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Title: DynamicProxyTime
 * @Author: Awan
 * @Date Created in 2018/11/9 14:25
 */
public class DynamicProxyTime implements InvocationHandler {
	/**
	 * 声明目标对象
	 */
	private Object targetObject;

	public DynamicProxyTime(Object targetObject) {
		this.targetObject = targetObject;
	}
	/**
	 *  获取动态代理对象
	 * @return 真正的代理对象
	 */
	public Object newProxyInstance() {
		Class<?> targetClass = targetObject.getClass();
		return Proxy.newProxyInstance(
				targetClass.getClassLoader(),
				targetClass.getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long start = System.nanoTime();
		System.out.println("开始执行");
		//通过反射，调用目标对象的方法
		Object result = method.invoke(targetObject, args);
		System.out.println("执行结束，耗时：" + (System.nanoTime() - start) + "纳秒");
		System.out.println();
		return result;
	}
}
