package com.david.springmvc.helloworld.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	/**
	 * 1.ʹ��@RequestMappingע����ӳ�������URL
	 * 2.����ֵ��ͨ����ͼ����������Ϊʵ�ʵ�������ͼ������InternalResourceViewResolver
	 * ��ͼ�������������½�����prefix+returnVal+suffix,ͨ�������ķ�ʽ�õ�ʵ�ʵ�������ͼ��Ȼ��
	 * ����ת������
	 * ��Ŀ��ҳ��λ�ã�/WEB-INF/helloworld/success.jsp
	 * @return
	 */
	@RequestMapping("hello")
	public String hello(){
		System.out.println("hello world!");
		return "success";
	}
}
