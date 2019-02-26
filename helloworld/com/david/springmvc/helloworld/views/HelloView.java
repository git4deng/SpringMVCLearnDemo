package com.david.springmvc.helloworld.views;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
@Component
public class HelloView implements View{

	public String getContentType() {
		
		return "text/html";
	}

	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.getWriter().write("hello view,time:"+new Date());
		
	}

}
