package com.david.springmvc.restful.handlers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.david.springmvc.restful.dao.DepartmentDao;
import com.david.springmvc.restful.dao.EmployeeDao;
import com.david.springmvc.restful.entities.Employee;

@Controller
public class EmployeeHandler {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmentDao;
	@RequestMapping("/emps")
	public String list(Map<String,Object> map){
		map.put("employees", employeeDao.getAll());
		return "list";
	}
	@RequestMapping(value="/emp",method=RequestMethod.GET)
	public String input(Map<String,Object> map){
		map.put("depts", departmentDao.getDepartments());
		map.put("employee", new Employee());
		return "input";
	}
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String addEmployee(Employee emp){
		System.out.println(emp);
		employeeDao.save(emp);
		//重定向到列表也面
		return "redirect:/emps";
	}
}
