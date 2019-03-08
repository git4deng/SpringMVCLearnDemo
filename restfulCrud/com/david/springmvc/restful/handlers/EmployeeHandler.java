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
	 * 获取列表操作
	 * @param map
	 * @return
	 */
	@RequestMapping("/emps")
	public String list(Map<String,Object> map){
		map.put("employees", employeeDao.getAll());
		return "list";
	}
	/**
	 * 获取添加的页面
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
	 * 添加操作
	 * 1.格式化转换出错信息都保存在BindingResult对象中
	 * 2.在目标方法 bean 类型的前面添加 @Valid 注解启用数据校验
	 * 3.提示消息的国际化
	 * 当一个属性校验失败后，校验框架会为该属性生成 4 个消息代码，这些代码以校验注解类名为前缀，结合modleAttribute、属性名及属性类型名生成多
	 * 个对应的消息代码：例如 User 类中的 password 属性标准了一个 @Pattern 注解，当该属性值不满足 @Pattern 所定义的规则时, 就会产生以
	 * 下 4个错误代码：
	 * 	Pattern.user.password
	 * 	Pattern.password
	 * 	Pattern.java.lang.String
	 * 	Pattern
	 * 详情见国际化配置文件：
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
			System.out.println("出错了！");
			List<FieldError> fieldErrors = results.getFieldErrors();
			for(FieldError e:fieldErrors){
				System.out.println(e.getField()+":"+e.getDefaultMessage());
			}
			map.put("depts", departmentDao.getDepartments());
			//map.put("employee", new Employee());
			//若验证出错了，则转向定制的页面
			return "input";
		}
		
		employeeDao.save(emp);
		//重定向到列表也面
		return "redirect:/emps";
	}
	/**
	 * 删除操作
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
	public String deleteEmployee(@PathVariable("id") Integer id){
		
		employeeDao.delete(id);
		//重定向到列表也面
		return "redirect:/emps";
	}
	/**
	 * 获取编辑的页面
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
	 * 添加操作修改提交数据
	 * @param emp
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	public String editEmployee(Employee emp){
		System.out.println(emp);
		employeeDao.save(emp);
		//重定向到列表也面
		return "redirect:/emps";
	}
	@ModelAttribute
	public void getEmployee(Map<String,Object> map,@RequestParam(value="id",required=false) Integer id){
		if(id!=null){
			map.put("employee",employeeDao.get(id));
		}
	}
	/**
	 * @ResponseBody 返回json字符串
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testJson(){
		return employeeDao.getAll();
	}
	/**
	 * 可以利用 @RequestBody 实现文件上传
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
	 * 可以利用 ResponseEntity<T> 实现文件下载
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
