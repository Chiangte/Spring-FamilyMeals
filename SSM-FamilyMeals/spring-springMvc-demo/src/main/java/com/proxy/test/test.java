package com.proxy.test;

import com.proxy.cglibProxy.CglibProxyTime;
import com.proxy.dao.MyInterface;
import com.proxy.dao.impl.MyInterfaceImpl;
import com.proxy.dynamicproxy.DynamicProxyTime;
import com.proxy.statics.StaticProxyTime;

/**
 * @Title: test
 * @Author: Awan
 * @Date Created in 2018/11/9 13:58
 */
public class test {
	static void test1() {
//		MyInterface myInterface = new MyInterfaceImpl();
//		myInterface.add();
//		myInterface.remove();
	}

	static void test2() {
		MyInterface myInterface = new StaticProxyTime();
		myInterface.add();
		myInterface.remove();
	}

	static void test3() {
		DynamicProxyTime dynamicProxyTime = new DynamicProxyTime(new MyInterfaceImpl());
		MyInterface myInterface = (MyInterface)dynamicProxyTime.newProxyInstance();
		myInterface.add();
		myInterface.remove();
		myInterface.count();
	}
	// aspectj
	static void test4() {
		CglibProxyTime cglibProxyTime = new CglibProxyTime(new MyInterfaceImpl());
		MyInterfaceImpl myInterface = (MyInterfaceImpl)cglibProxyTime.newProxyInstance();
		myInterface.add();
		myInterface.remove();
		myInterface.count();
	}

	public static void main(String[] args) {
		test4();
	}
}
