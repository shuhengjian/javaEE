package com.musicbar.core.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.musicbar.core.annotation.LoggerAnnotation;

@Aspect
@Component
/**
 * 日志打印切面类
 * @author Administrator
 *
 */
public class LogAspect {
	private Logger log4j = Logger.getLogger(LogAspect.class);
	
	 @Pointcut("execution(public * com.musicbar.second.*.controller.*.*(..))")
	 /**
	  * 设置切入点
	  */
	 public void webPointCut(){}
	 
	 @Before("webPointCut()")
	 /**
	  * 前置通知器
	  * @param joinPoint
	  * @throws Throwable
	  */
	 public void deBefore(JoinPoint joinPoint) throws Throwable {
		 ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		 if(attributes != null) {
			 HttpServletRequest request = attributes.getRequest();
			 log4j.info("浏览器输入的网址=URL : " + request.getRequestURL().toString());
			 log4j.info("IP : " + request.getRemoteAddr());
		 }
		 Method m = ((MethodSignature)joinPoint.getSignature()).getMethod();//目标方法
		 LoggerAnnotation log = m.getDeclaredAnnotation(LoggerAnnotation.class);//取得日志的注解
		 if(log != null) {
			 String begin = log.begin();
			 log4j.info("业务方法获得的参数=ARGS : " + Arrays.toString(joinPoint.getArgs()));
			 log4j.info("方法 :" + ((MethodSignature)joinPoint.getSignature()).getMethod() + "开始");
			 log4j.info(begin);
		 }
	 }
	 
	@AfterReturning(returning = "ret", pointcut = "webPointCut()")
	/**
	 * 方法返回值
	 * @param ret
	 * @throws Throwable
	 */
    public void doAfterReturning(Object ret) throws Throwable {
		log4j.info("方法的返回值 : " + ret);
    }
	
	@After("webPointCut()")
	/**
	 * 后置通知
	 * @param jp
	 */
    public void after(JoinPoint jp){
		 Method m = ((MethodSignature)jp.getSignature()).getMethod();//目标方法
		 LoggerAnnotation log = m.getDeclaredAnnotation(LoggerAnnotation.class);//取得日志的注解
		 if(log != null) {
			 String end = log.end();
			 log4j.info("业务方法获得的参数=ARGS : " + Arrays.toString(jp.getArgs()));
			 log4j.info("方法 :" + ((MethodSignature)jp.getSignature()).getMethod() + "结束");
			 log4j.info(end);
		 }
    }
}
