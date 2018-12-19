package com.proxy.statics;

import com.proxy.dao.MyInterface;

/**
 * @Title: StaticProxyTime
 * @Author: Awan
 * @Date Created in 2018/11/9 14:06
 */
public class StaticProxyTime implements MyInterface {

	private MyInterface myInterface = null;  // new MyInterfaceImpl();
	@Override
	public void add() {
		long start = System.nanoTime();
		System.out.println("开始执行");
		myInterface.add();
		System.out.println("执行结束，耗时：" + (System.nanoTime() - start) + "纳秒");
	}

	@Override
	public void remove() {
		long start = System.nanoTime();
		System.out.println("开始执行");
		myInterface.remove();
		System.out.println("执行结束，耗时：" + (System.nanoTime() - start) + "纳秒");
	}

	@Override
	public void update() {
		long start = System.nanoTime();
		System.out.println("开始执行");
		myInterface.update();
		System.out.println("执行结束，耗时：" + (System.nanoTime() - start) + "纳秒");
	}

	@Override
	public long count() {
		long start = System.nanoTime();
		System.out.println("开始执行");
		long r = myInterface.count();
		System.out.println("执行结束，耗时：" + (System.nanoTime() - start) + "纳秒");
		return r;
	}
}
