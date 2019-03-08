package com.david.springmvc.restful.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 * 1.��ʽ��ת��������Ϣ��������BindingResult������
	 * 2.��Ŀ�귽�� bean ���͵�ǰ����� @Valid ע����������У��
	 * 3.��ʾ��Ϣ�Ĺ��ʻ�
	 * ��һ������У��ʧ�ܺ�У���ܻ�Ϊ���������� 4 ����Ϣ���룬��Щ������У��ע������Ϊǰ׺�����modleAttribute�����������������������ɶ�
	 * ����Ӧ����Ϣ���룺���� User ���е� password ���Ա�׼��һ�� @Pattern ע�⣬��������ֵ������ @Pattern ������Ĺ���ʱ, �ͻ������
	 * �� 4��������룺
	 * 	Pattern.user.password
	 * 	Pattern.password
	 * 	Pattern.java.lang.String
	 * 	Pattern
	 * ��������ʻ������ļ���
	 * NotEmpty.employee.lastName=^^lastName\u4E0D\u80FD\u4E3A\u7A7A\u3002
	 * Email.employee.email=\u90AE\u7BB1\u5730\u5740\u4E0D\u80FD\u4E3A\u7A7A\u54DF\u3002
	 * Past.employee.birth=\u751F\u65E5\u5FC5\u987B\u5C0F\u4E8E\u5F53\u524D\u7CFB\u7EDF\u65F6\u95F4\u3002
	 * @param emp
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String addEmployee(@Valid Employee emp,BindingResult results,Map<String,Object> map){
		System.out.println(emp);
		if(results.getErrorCount()>0){
			System.out.println("�����ˣ�");
			List<FieldError> fieldErrors = results.getFieldErrors();
			for(FieldError e:fieldErrors){
				System.out.println(e.getField()+":"+e.getDefaultMessage());
			}
			map.put("depts", departmentDao.getDepartments());
			//map.put("employee", new Employee());
			//����֤�����ˣ���ת���Ƶ�ҳ��
			return "input";
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
	/**
	 * @ResponseBody ����json�ַ���
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testJson(){
		return employeeDao.getAll();
	}
	/**
	 * �������� @RequestBody ʵ���ļ��ϴ�
	 * @param body
	 * @return
	 */
	@ResponseBody
	@RequestMapping
	public String testHttpMessageConverter(@RequestBody String body){
		
		System.out.println("body:"+body);
		return "hello"+new Date();
	}
	/**
	 * �������� ResponseEntity<T> ʵ���ļ�����
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
		ServletContext servletContext = session.getServletContext();
		InputStream in = servletContext.getResourceAsStream("/files/12345.txt");
		byte[] body=new byte[in.available()];
		in.read(body);
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=abc.txt");
		HttpStatus statusCode=HttpStatus.OK;
		ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers,statusCode);
		return response; 
	}
}
