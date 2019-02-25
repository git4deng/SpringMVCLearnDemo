package com.david.springmvc.helloworld.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	/**
	 * 1.使用@RequestMapping注解来映射请求的URL
	 * 2.返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver
	 * 视图解析器会做如下解析：prefix+returnVal+suffix,通过这样的方式得到实际的物理视图，然后
	 * 会做转发操作
	 * 即目标页面位置：/WEB-INF/helloworld/success.jsp
	 * @return
	 */
	@RequestMapping("hello")
	public String hello(){
		System.out.println("hello world!");
		return "success";
	}
}
