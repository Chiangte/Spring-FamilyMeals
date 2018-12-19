package com.ljt.aop;

import org.aopalliance.intercept.Joinpoint;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Title: LogAop
 * @Author: Awan
 * @Date Created in 2018/11/9 21:10
 */
@Component
@Aspect     // 告诉spring这是个增强类
public class LogAop {
	private static  final Logger LOGGER = Logger.getLogger(LogAop.class);

	/**
	 * 定义切面
	 */
	@Pointcut("execution(* com.ljt.service..*(..))")
	public void pointcut(){
	}
	/**
	 * 前置增强
	 * @param jp
	 */
	@Before("pointcut()")
	public  void before(JoinPoint jp){
			LOGGER.info("前置增强；调用"+jp.getTarget().getClass()+"类的"
					+jp.getSignature().getName()+"，参数是："+ Arrays.asList(jp.getArgs()));
	}

	/**
	 * 后置增强
	 * @param jp
	 * @param object
	 */
	@AfterReturning(pointcut = "pointcut()", returning =  "object")
	public void afterReturning(JoinPoint jp, Object object){
			LOGGER.info("后置增强：调用"+jp.getTarget().getClass()+"类的"
					+jp.getSignature().getName()+"，参数是:"+Arrays.asList(jp.getArgs())
					+"返回值："+object);
	}

	/**
	 * 异常增强
	 * @param jp
	 */
	@AfterThrowing(pointcut = "pointcut()", throwing = "e")
	public void afterThrowing(JoinPoint jp,Throwable e){
		LOGGER.info("最终增强，调用："+jp.getTarget().getClass()+"类的："
				+jp.getSignature().getName()
				+"参数："+ Arrays.asList(jp.getArgs())
				+"异常："+e);
	}

	/**
	 * 最终增强
	 * @param jp
	 */
	@After("pointcut()")
	public void after(JoinPoint jp){
		LOGGER.info("最终增强，调用："+jp.getTarget().getClass()+"类的："
					+jp.getSignature().getName()
					+"参数："+ Arrays.asList(jp.getArgs()));
	}

	/**
	 * 环绕增强
	 * @param jp
	 * @throws Throwable
	 */
	@Around("pointcut()")
	public void around(ProceedingJoinPoint jp) throws Throwable {
			LOGGER.info("环绕增强-前置：调用："+jp.getTarget().getClass()+"类的："
					+jp.getSignature().getName()
					+"参数："+ Arrays.asList(jp.getArgs()));
		try {
				Object result = jp.proceed(jp.getArgs());
			LOGGER.info("环绕增强-后置：调用："+jp.getTarget().getClass()+"类的："
					+jp.getSignature().getName()
					+"参数："+ Arrays.asList(jp.getArgs())
					+"返回值是："+ result);
		}catch (Throwable e){
			LOGGER.info("环绕增强-异常：调用："+jp.getTarget().getClass()+"类的："
					+jp.getSignature().getName()
					+"参数："+ Arrays.asList(jp.getArgs()));
		}finally{
			LOGGER.info("环绕增强-最终：调用："+jp.getTarget().getClass()+"类的："
					+jp.getSignature().getName()
					+"参数："+ Arrays.asList(jp.getArgs()));
		}
	}
}
