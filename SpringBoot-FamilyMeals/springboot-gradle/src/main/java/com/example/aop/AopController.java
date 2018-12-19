package com.example.aop;

import com.example.beans.PageResultBean;
import com.example.beans.ResultBean;
import com.example.entity.UnloginException;
import com.sun.xml.internal.ws.api.model.CheckedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Awan
 * @Description:
 *  使用@Aspect注解将此类定义为切面类
 * @Date Created in 16:55  2018/11/26
 */
@Aspect
@Component
public class AopController {
	private static  final Logger LOGGER = LoggerFactory.getLogger(AopController.class);
	ThreadLocal<ResultBean> resultBeanThreadLocal = new ThreadLocal<>();
	ThreadLocal<PageResultBean<?>> pageResultBeanThreadLocal = new ThreadLocal<>();
	ThreadLocal<Long> start  = new ThreadLocal<>();

	/**
	 * Aop用在全局异常处理 啊啊啊啊啊
	 * 定义切入点拦截PageResultBean
	 */
	@Pointcut(value = "execution(public com.example.beans.PageResultBean *(..)))")
	public  void handlerPageResultBeanMethod(){}

	@Pointcut(value = "execution(public com.example.beans.ResultBean *(..)))")
	public void handlerResultBeanMethod(){}

	@Around("handlerPageResultBeanMethod()")
	public Object handlerPageResultBeanMethod(ProceedingJoinPoint pjp) {
		start.set(System.currentTimeMillis());
		try {
			pageResultBeanThreadLocal.set((PageResultBean<?>)pjp.proceed());
			LOGGER.info(pjp.getSignature() + " 方法执行耗时:  " + (System.currentTimeMillis() - start.get()));
		} catch (Throwable e) {
			ResultBean<?> resultBean = handlerException(pjp , e);
			pageResultBeanThreadLocal.set( new PageResultBean<>().setMsg(resultBean.getMsg()).setCode(resultBean.getCode()));
		}
		return pageResultBeanThreadLocal.get();
	}

	@Around("handlerResultBeanMethod()")
		public Object handlerResultBeanMethod(ProceedingJoinPoint pjp){
		start.set(System.currentTimeMillis());
		try{resultBeanThreadLocal.set((ResultBean<?>)pjp.proceed());
			LOGGER.info(pjp.getSignature()+" 方法执行耗时:    "+(System.currentTimeMillis()-start.get()));
		}catch(Throwable e){resultBeanThreadLocal.set(handlerException(pjp,e));}
		return resultBeanThreadLocal.get();}

	/**
	 *  封装异常消息，区分已知异常（自己抛出的）和 未知异常
	 * @param pjp
	 * @param e
	 * @return
	 */
	private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
		ResultBean<?> resultBean  = new PageResultBean();
		LOGGER.error(pjp.getSignature()+"error:", e);
		// 已知异常
		if (e instanceof CheckedException){
			resultBean.setMsg(e.getLocalizedMessage());
			resultBean.setCode(ResultBean.FAIL);
		}else  if (e instanceof UnloginException){
				resultBean.setMsg("Unlogin");
				resultBean.setCode(ResultBean.NO_LOGIN);
		}else {
			resultBean.setMsg(e.toString());
			resultBean.setCode(ResultBean.FAIL);
		}
		return  resultBean;
	}

}
