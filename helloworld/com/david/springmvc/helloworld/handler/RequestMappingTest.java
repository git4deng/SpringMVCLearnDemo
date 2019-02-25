package com.david.springmvc.helloworld.handler;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.david.springmvc.helloworld.entities.User;

/**
 * @RequestMapping 注解详解
 * @author david
 *
 */
/*
 *  @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外，
 *  还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中
 *  另外注意：SessionAttributes只能放在类上，而不能放在方法上
 *  @SessionAttributes(value={“user1”, “user2”},types={Dept.class})
 */
@SessionAttributes(value="user",types={String.class})
@RequestMapping("/springmvc")
@Controller
public class RequestMappingTest {
	private static final String SUCCESS="success";
	/**
	 * 1.@RequestMapping 除了可以修饰方法外还可以修饰类
	 * 2.类定义处：提供初步的请求映射信息。相对于 WEB 应用的根目录，方法处：提供进一步的细分映射信息。相对于类定义处的 URL。
	 * 	若类定义处未标注 @RequestMapping，则方法处标记的 URL 相对于WEB 应用的根目录
	 * @return
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping(){
		System.out.println("test requestMapping");
		return SUCCESS;
	}
	
	/**
	 * 常用：使用method 属性指定请求方法
	 * @return
	 */
	@RequestMapping(value ="/testMethod",method=RequestMethod.POST)
	public String testMethod(){
		System.out.println("请求方法的过滤");
		return SUCCESS;
	}
	/**
	 * 了解：使用params参数和headers更加精确的映射请求。params和headers支持简单的表达式。
	 * @return
	 */
	@RequestMapping(value ="/testParamsAndHeader",params={"username","age!=10"},headers={"Accept-Language=zh-CN,zh;q=0.9,en;q=0.8,und;q=0.7"})
	public String testParamsAndHeader(){
		System.out.println("测试请求参数过滤和请求头过滤！");
		return SUCCESS;
	}
	/**
	 * 了解：
	 * Ant 风格资源地址支持 3 种匹配符：
	 *  ?：匹配文件名中的一个字符
	 *  *：匹配文件名中的任意字符
	 *  **：** 匹配多层路径
	 * @return
	 */
	@RequestMapping(value="/testAntUrl/*/abc")
	public String testAntUrl(){
		System.out.println("ant 风格的请求路径");
		return SUCCESS;
	}
	/**
	 * @PathVariable 可以来映射URL中的占位符到目标方法的参数中
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id){
		System.out.println(id);
		return SUCCESS;
	}
	/**
	 * rest风格的URL。
	 * 以CRUD为例，新增 POST
	 * 修改 PUT
	 * 获取：GET
	 * 删除：DELETE
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.GET)
	public String testRestGet(@PathVariable("id") Integer id){
		System.out.println("GET:"+id);
		return SUCCESS;
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/testRest",method=RequestMethod.POST)
	public String testRestPost(){
		System.out.println("POST:");
		return SUCCESS;
	}
	
	/**
	 * 此处通过配置如下过滤器将POST请求转换为PUT和DELETE请求
	 * <filter>
	 * 	<filter-name>hiddenHttpMethodFilter</filter-name>
	 * 	<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
	 * </filter>
	 * <filter-mapping>
	 * <filter-name>hiddenHttpMethodFilter</filter-name>
	 * <!--过滤所有请求-->
	 * <url-pattern>/*</url-pattern>
	 * </filter-mapping>
	 * 另外在发送请求时需要 发送隐藏域参数配合使用  如：<input type="hidden" name="_method" value="DELETE">
	 * name和value为固定值
	 * <form action="springmvc/testRest/1" method="post">
	 * 	<input type="hidden" name="_method" value="DELETE">
	 * 	<input type="submit" value="Test Rest DELETE">
	 * </form>
	 * 
	 * 使用@PathVariable 注解得到目标方法中的参数
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.PUT)
	public String testRestPostPut(@PathVariable("id") Integer id){
		System.out.println("PUT:"+id);
		return SUCCESS;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.DELETE)
	public String testRestPostDelete(@PathVariable("id") Integer id){
		System.out.println("DELETE:"+id);
		return SUCCESS;
	}
	/**
	 * @RequestParam 来映射请求参数，其中value值即为请求参数的参数名，required即为是否必须，另外defaultValue指定默认值
	 * @param username
	 * @param age
	 * @return
	 */
	@RequestMapping(value="/testRequestParam")
	public String testRequestParam(@RequestParam(value="username") String username,
			@RequestParam(value="age",required=false,defaultValue="0") Integer age){
		System.out.println("RequestParam:username---"+username+",age---"+age);
		return SUCCESS;
	}
	/**
	 * @RequestHeader:用法同@RequestParam，作用映射请求头参数，了解
	 * @param username
	 * @param age
	 * @return
	 */
	@RequestMapping(value="/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language") String al){
		System.out.println("@Accept-Language---"+al);
		return SUCCESS;
	}
	/**
	 * 了解：@CookieValue 映射一个cookie值，与@RequestParam类似
	 * @param sessionId
	 * @return
	 */
	@RequestMapping(value="/testCookieValue")
	public String testCookieValue(@CookieValue(value="JSESSIONID") String sessionId){
		System.out.println("@Accept-Language：sessionId"+sessionId);
		return SUCCESS;
	}
	/**
	 * Spring MVC 会按请求参数名和 POJO 属性名进行自动匹配，自动为该对象填充属性值。支持级联属性
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/testPojo")
	public String testPojo(User user){
		System.out.println(user);
		return SUCCESS;
	}
	/**
	 * 可以使用Servlet原生的API作为目标方法的参数，可以接受如下参数：
	 * HttpServletRequest，HttpServletResponse，HttpSession，java.security.Principal，Locale，InputStream
	 * OutputStream,Reader,Writer
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/testServletAPI")
	public void testServletAPI(HttpServletRequest req,HttpServletResponse res,Writer writer) throws IOException{
		System.out.println("testServletAPI:"+req+","+res);
		writer.write("hello springmvc!");
		//return SUCCESS;
	}
	/**
	 * 目标方法的返回值可以时ModelAndView类型，其中可以包含视图和模型信息
	 * springmvc会把ModelAndView的model中的数据放入到request域对象中。
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/testModelAndView")
	public ModelAndView testModelAndView(){
		ModelAndView mav=new ModelAndView(SUCCESS);
		//添加模型数据到ModelAndView中
		mav.addObject("time", new Date());
		return mav;
	}
	/**
	 * 如果方法的入参为 Map 或 Model 类型，Spring MVC 会将隐含模型的引用传递给这些入参。在方法体内，
	 * 开发者可以通过这个入参对象访问到模型中的所有数据，也可以向模型中添加新的属性数据
	 * 目标方法可以添加Map类型的参数，也可以是Model类型或ModelMap类型的参数
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/testMap")
	public String testMap(Map<String,Object> map){
		map.put("names", Arrays.asList("Tom","jerry","Mike"));
		map.put("email", "974037139@qq.com");
		return SUCCESS;
	}
	
	/**
	 * @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外，
	 * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/testSessionAttributes")
	public String testSessionAttributes(Map<String,Object> map){
		User user=new User();
		user.setUsername("david");
		user.setAge(21);
		map.put("user", user);
		map.put("school", "sssss");
		return SUCCESS;
	}

}
