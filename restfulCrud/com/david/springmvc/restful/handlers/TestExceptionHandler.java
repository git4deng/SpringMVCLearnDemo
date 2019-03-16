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
	 * 1. �� @ExceptionHandler ����������п��Լ��� Exception ���͵Ĳ���, �ò�������Ӧ�������쳣����
	 * 2. @ExceptionHandler ����������в��ܴ��� Map. ��ϣ�����쳣��Ϣ����ҳ����, ��Ҫʹ�� ModelAndView ��Ϊ����ֵ
	 * 3. @ExceptionHandler ������ǵ��쳣�����ȼ�������. 
	 * 4. @ControllerAdvice: ����ڵ�ǰ Handler ���Ҳ��� @ExceptionHandler ������������ǰ�������ֵ��쳣, 
	 * ��ȥ @ControllerAdvice ��ǵ����в��� @ExceptionHandler ��ǵķ����������쳣. 
	 */
//	@ExceptionHandler({ArithmeticException.class})
//	public ModelAndView handleArithmeticException(Exception e){
//		
//		System.out.println("�׳��쳣��"+e.getMessage());
//		ModelAndView mav=new ModelAndView("error");
//		mav.addObject("error", e.getMessage());
//		return mav;
//	}
	/**
	 * �쳣�����ȼ�try catch���ȼ�����һ�£�������handler��ǵ��쳣����ֻ�ܴ���ǰhandler�׳����쳣������ͨ������ȫ�ֵ��쳣����
	 * @param e
	 * @return
	 */
//	@ExceptionHandler({RuntimeException.class})
//	public ModelAndView handleRuntimeException(Exception e){
//		
//		System.out.println("�׳��쳣��"+e.getMessage());
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
