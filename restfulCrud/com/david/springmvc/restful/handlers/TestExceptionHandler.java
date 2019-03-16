package com.david.springmvc.restful.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.david.springmvc.restful.exception.UserNameNotMatchPasswordException;
/**
 * 
 * @author david
 *
 */
@Controller
public class TestExceptionHandler {
	@RequestMapping("testExceptionHandlerExcption")
	public String testExceptionHandlerExcption(@RequestParam("i") Integer i){
		System.out.println("result:"+10/i);
		
		return "success";
	}
	
	
	/**
	 * 1. 在 @ExceptionHandler 方法的入参中可以加入 Exception 类型的参数, 该参数即对应发生的异常对象
	 * 2. @ExceptionHandler 方法的入参中不能传入 Map. 若希望把异常信息传导页面上, 需要使用 ModelAndView 作为返回值
	 * 3. @ExceptionHandler 方法标记的异常有优先级的问题. 
	 * 4. @ControllerAdvice: 如果在当前 Handler 中找不到 @ExceptionHandler 方法来出来当前方法出现的异常, 
	 * 则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常. 
	 */
//	@ExceptionHandler({ArithmeticException.class})
//	public ModelAndView handleArithmeticException(Exception e){
//		
//		System.out.println("抛出异常："+e.getMessage());
//		ModelAndView mav=new ModelAndView("error");
//		mav.addObject("error", e.getMessage());
//		return mav;
//	}
	/**
	 * 异常的优先级try catch优先级保持一致，但是在handler标记的异常处理只能处理当前handler抛出的异常，可以通过配置全局的异常处理
	 * @param e
	 * @return
	 */
//	@ExceptionHandler({RuntimeException.class})
//	public ModelAndView handleRuntimeException(Exception e){
//		
//		System.out.println("抛出异常："+e.getMessage());
//		ModelAndView mav=new ModelAndView("error");
//		mav.addObject("error", e.getMessage());
//		return mav;
//	}
	
	@RequestMapping("testResponseStatusExceptionResolver")
	public String testResponseStatusExceptionResolver(@RequestParam("i") Integer i){
		if(i==13){
			throw new UserNameNotMatchPasswordException();
		}
		System.out.println("testResponseStatusExceptionResolver................");
		return "success";
	}
}
