package com.david.springmvc.restful.handlers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.david.springmvc.restful.dao.DepartmentDao;
import com.david.springmvc.restful.dao.EmployeeDao;
import com.david.springmvc.restful.entities.Employee;

@Controller
public class EmployeeHandler {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmentDao;
	/**
	 * ��ȡ�б����
	 * @param map
	 * @return
	 */
	@RequestMapping("/emps")
	public String list(Map<String,Object> map){
		map.put("employees", employeeDao.getAll());
		return "list";
	}
	/**
	 * ��ȡ��ӵ�ҳ��
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.GET)
	public String input(Map<String,Object> map){
		map.put("depts", departmentDao.getDepartments());
		map.put("employee", new Employee());
		return "input";
	}
	/**
	 * ��Ӳ���
	 * ��ʽ��ת��������Ϣ��������BindingResult������
	 * @param emp
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String addEmployee(Employee emp,BindingResult results){
		System.out.println(emp);
		if(results.getErrorCount()>0){
			System.out.println("�����ˣ�");
		}
		List<FieldError> fieldErrors = results.getFieldErrors();
		for(FieldError e:fieldErrors){
			System.out.println(e.getField()+":"+e.getDefaultMessage());
		}
		employeeDao.save(emp);
		//�ض����б�Ҳ��
		return "redirect:/emps";
	}
	/**
	 * ɾ������
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
	public String deleteEmployee(@PathVariable("id") Integer id){
		
		employeeDao.delete(id);
		//�ض����б�Ҳ��
		return "redirect:/emps";
	}
	/**
	 * ��ȡ�༭��ҳ��
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	public String initEdit(Map<String,Object> map,@PathVariable("id") Integer id){
		
		map.put("depts", departmentDao.getDepartments());
		map.put("employee",employeeDao.get(id));
		
		return "input";
	}
	
	/**
	 * ��Ӳ����޸��ύ����
	 * @param emp
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	public String editEmployee(Employee emp){
		System.out.println(emp);
		employeeDao.save(emp);
		//�ض����б�Ҳ��
		return "redirect:/emps";
	}
	@ModelAttribute
	public void getEmployee(Map<String,Object> map,@RequestParam(value="id",required=false) Integer id){
		if(id!=null){
			map.put("employee",employeeDao.get(id));
		}
	}
}
