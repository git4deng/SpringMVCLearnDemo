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
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	/**
	 * 1.有 @ModelAttribute 标记的方法，会在每个目标方法执行之前被springmvc调用
	 * 2.@ModelAttribute 注解也可以来修饰目标方法POJO类型的入参，其value属性值有如下作用：
	 * 1).springMVC会使用value属性值在 implicitModel 中查找对应的对象，若存在则会直接传入到目标方法的参数中。
	 * 2).springMVC会以value为key,POJO类型的对象为value存入到request中
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map){
		System.out.println("ModelAttribute.....");
		if(id!=null){
			//模拟从数据库中获取一个对象，然后把这个对象放在Map里面
			User user=new User(1, "david", "123456", "xxx@xxx.xxx", 20);
			System.out.println("从数据库中获取一个对象！"+user);
			map.put("user", user);
		}
	}
	/**
	 * 运行流程：
	 * 1.执行@ModelAttribute注解修饰的方法，从数据库中获取对象，把对象放入Map中。键为user
	 * 2.springMVC从map中取出user对象，并把表单请求参数赋给该user对应的属性
	 * 3.springMvc把上述对象传入目标方法的参数。
	 * 注意：在ModelAttribute 修饰的方法中，放入到map时的键需要和目标方法入参类型的第一个字母小写的字符串一致  即:User-->user
	 * 
	 * SpringMVC 确定目标方法 POJO 类型入参的过程
	 * 1. 确定一个 key:
	 * 1). 若目标方法的 POJO 类型的参数木有使用 @ModelAttribute 作为修饰, 则 key 为 POJO 类名第一个字母的小写
	 * 2). 若使用了  @ModelAttribute 来修饰, 则 key 为 @ModelAttribute 注解的 value 属性值. 
	 * 2. 在 implicitModel 中查找 key 对应的对象, 若存在, 则作为入参传入
	 * 1). 若在 @ModelAttribute 标记的方法中在 Map 中保存过, 且 key 和 1 确定的 key 一致, 则会获取到. 
	 * 3. 若 implicitModel 中不存在 key 对应的对象, 则检查当前的 Handler 是否使用 @SessionAttributes 注解修饰, 
	 * 若使用了该注解, 且 @SessionAttributes 注解的 value 属性值中包含了 key, 则会从 HttpSession 中来获取 key 所
	 * 对应的 value 值, 若存在则直接传入到目标方法的入参中. 若不存在则将抛出异常. 
	 * 4. 若 Handler 没有标识 @SessionAttributes 注解或 @SessionAttributes 注解的 value 值中不包含 key, 则
	 * 会通过反射来创建 POJO 类型的参数, 传入为目标方法的参数
	 * 5. SpringMVC 会把 key 和 POJO 类型的对象保存到 implicitModel 中, 进而会保存到 request 中. 
	 * 
	 * 源代码分析的流程
	 * 1. 调用 @ModelAttribute 注解修饰的方法. 实际上把 @ModelAttribute 方法中 Map 中的数据放在了 implicitModel 中.
	 * 2. 解析请求处理器的目标参数, 实际上该目标参数来自于 WebDataBinder 对象的 target 属性
	 * 1). 创建 WebDataBinder 对象:
	 * ①. 确定 objectName 属性: 若传入的 attrName 属性值为 "", 则 objectName 为类名第一个字母小写. 
	 * *注意: attrName. 若目标方法的 POJO 属性使用了 @ModelAttribute 来修饰, 则 attrName 值即为 @ModelAttribute 
	 * 的 value 属性值 
	 * 
	 * ②. 确定 target 属性:
	 * 	> 在 implicitModel 中查找 attrName 对应的属性值. 若存在, ok
	 * 	> *若不存在: 则验证当前 Handler 是否使用了 @SessionAttributes 进行修饰, 若使用了, 则尝试从 Session 中
	 * 获取 attrName 所对应的属性值. 若 session 中没有对应的属性值, 则抛出了异常. 
	 * 	> 若 Handler 没有使用 @SessionAttributes 进行修饰, 或 @SessionAttributes 中没有使用 value 值指定的 key
	 * 和 attrName 相匹配, 则通过反射创建了 POJO 对象
	 * 
	 * 2). SpringMVC 把表单的请求参数赋给了 WebDataBinder 的 target 对应的属性. 
	 * 3). *SpringMVC 会把 WebDataBinder 的 attrName 和 target 给到 implicitModel. 
	 * 近而传到 request 域对象中. 
	 * 4). 把 WebDataBinder 的 target 作为参数传递给目标方法的入参. 
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/testModelAtrribute")
	public String testModelAtrribute(User user){
		System.out.println("修改："+user);
		return SUCCESS;
	}
	@RequestMapping(value="/testViewAndViewResolver")
	public String testViewAndViewResolver(){
		System.out.println("testViewAndViewResolver");
		return SUCCESS;
	}
	/**
	 * 验证自定义视图
	 * @return
	 */
	@RequestMapping("/testView")
	public String testView(){
		System.out.println("testView");
		return "helloView";
	}
	
	@RequestMapping("/testRedirect")
	public String testRedirect(){
		System.out.println("testRedirect");
		return "redirect:/redirect.jsp";
	}
}
