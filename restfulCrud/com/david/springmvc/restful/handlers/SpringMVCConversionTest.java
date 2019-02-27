package com.david.springmvc.restful.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.david.springmvc.restful.dao.EmployeeDao;
import com.david.springmvc.restful.entities.Employee;

/**
 * springMVC 自定义转换器 Conversion
 * @author david
 *
 */
@Controller
public class SpringMVCConversionTest {
	@Autowired
	private EmployeeDao employeeDao;
	@RequestMapping("testConversionServiceConverer")
	public String testConversionServiceConverer(@RequestParam("employee")Employee employee){
		System.out.println(employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
}
