package com.david.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FirstInterceptor implements HandlerInterceptor {
	/**
	 * 渲染视图之后被调用. 释放资源
	 */
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object obj, Exception e)
			throws Exception {
		System.out.println("FirstInterceptor.afterCompletion");

	}
	
	/**
	 * 调用目标方法之后, 但渲染视图之前. 
	 * 可以对请求域中的属性或视图做出修改. 
	 */
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object obj, ModelAndView m)
			throws Exception {
		System.out.println("FirstInterceptor.postHandle");

	}
	/**
	 * 该方法在目标方法之前被调用.
	 * 若返回值为 true, 则继续调用后续的拦截器和目标方法. 
	 * 若返回值为 false, 则不会再调用后续的拦截器和目标方法. 
	 * 
	 * 可以考虑做权限. 日志, 事务等. 
	 */
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
		System.out.println("FirstInterceptor.preHandle");
		return true;
	}

}
