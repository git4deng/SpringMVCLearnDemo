package com.david.springmvc.restful.handleExcpetion;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlers {
	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView handleArithmeticException(Exception e){
		
		System.out.println("Å×³öÒì³££º"+this.getClass().getName()+"====>"+e.getMessage());
		ModelAndView mav=new ModelAndView("error");
		mav.addObject("error", e.getMessage());
		return mav;
	}
}
