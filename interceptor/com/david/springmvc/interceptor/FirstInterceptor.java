package com.david.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FirstInterceptor implements HandlerInterceptor {
	/**
	 * ��Ⱦ��ͼ֮�󱻵���. �ͷ���Դ
	 */
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object obj, Exception e)
			throws Exception {
		System.out.println("FirstInterceptor.afterCompletion");

	}
	
	/**
	 * ����Ŀ�귽��֮��, ����Ⱦ��ͼ֮ǰ. 
	 * ���Զ��������е����Ի���ͼ�����޸�. 
	 */
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object obj, ModelAndView m)
			throws Exception {
		System.out.println("FirstInterceptor.postHandle");

	}
	/**
	 * �÷�����Ŀ�귽��֮ǰ������.
	 * ������ֵΪ true, ��������ú�������������Ŀ�귽��. 
	 * ������ֵΪ false, �򲻻��ٵ��ú�������������Ŀ�귽��. 
	 * 
	 * ���Կ�����Ȩ��. ��־, �����. 
	 */
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
		System.out.println("FirstInterceptor.preHandle");
		return true;
	}

}
