package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Awan
 * @Description: 类定义为切面类  打印日志
 * @Date Created in 15:21  2018/11/26
 */
@Aspect
@Component
public class AopTestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AopTestController.class);

	/**
	 * 定义一个切点
	 * 要在一个方法加上切入点，根据方法的返回的对象，方法名，修饰词来写成一个表达式或者是具体的名字
	 */
	// 写个切入点在所有返回对象为Area的方法
	//@Pointcut("execution(public com.example.entity.Area (..))")

	@Pointcut(value = " execution(public String hello(..))")
	public  void cutOffPoint(){ }

	/**
	 * 前置通知
	 * 在切入点开始处切入内容
	 */
	@Before("cutOffPoint()")
	public  void beforeTest(){
		//LOGGER.info("我在hello方法之前执行");
	}

	/**
	 *
	 * 后置通知
	 * 和前置通知相反，在切入点之后执行
	 *
	 * @remark 如果要扩展一些代码，在不需要动源代码的基础之上就可以进行拓展，美滋滋
	 */
	@After("cutOffPoint()")
	public void doAfter(){
		//LOGGER.info("我在hello方法之后执行");
	}


	/**
	 * 1.环绕通知可以项目做全局异常处理
	 * 2.日志记录
	 * 3.用来做数据全局缓存
	 * 4.全局的事物处理 等
	 */
	ThreadLocal<Long> startTime = new ThreadLocal<>();
	@Around("cutOffPoint()")
	public  Object doAround(ProceedingJoinPoint pjp){
		startTime.set(System.currentTimeMillis());
		//LOGGER.info("我是环绕通知！");
		Object object;
		try {
			object = pjp.proceed();
			LOGGER.info("执行返回值"+object);
			 LOGGER.info(pjp.getSignature().getName()+"方法执行耗时："+(System.currentTimeMillis() - startTime.get()));
		} catch (Throwable throwable) {
			object=throwable.toString();
		}
		return  object;
	}

	/**
	 * 切入点返回结果之后执行,也就是前置 后置 环绕 都执行完了，这个就执行了!!!
	 *     执行完请求可以做的!!!
	 * @param result
	 * @throws Throwable
	 *
	 * @remark 应用场景可以用来在订单支付完成之后就行二次的结果验证，
	 *          重要参数的二次校验，防止在方法执行中的时候参数被修改等等.....
	 */
	@AfterReturning(returning = "result", pointcut = "cutOffPoint()")
	public  void doAfterReturning(Object result) throws  Throwable{
		LOGGER.info("我是@AfterReturning,它们秀完了，该我上场了");
	}

	/**
	 * 在切入执行报错的时候执行的
	 * 在其它切入内容中随意整个错误出来，制造一个环境。
	 * @param e
	 */
	@AfterThrowing(throwing = "e",pointcut = "cutOffPoint()")
	public  void doAfterThrowing(Throwable e){
		LOGGER.info("我是@AfterThrowing,它们犯错，我来背锅。");
		LOGGER.info("错误信息："+e.getMessage());
	}

	public  static void main(String[] args){
		LOGGER.error("error级别日志");
		LOGGER.warn("warn级别日志");
		LOGGER.info("info级别日志");
	}

}


