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
 * @RequestMapping ע�����
 * @author david
 *
 */
/*
 *  @SessionAttributes ���˿���ͨ��������ָ����Ҫ�ŵ��Ự�е������⣬
 *  ������ͨ��ģ�����ԵĶ�������ָ����Щģ��������Ҫ�ŵ��Ự��
 *  ����ע�⣺SessionAttributesֻ�ܷ������ϣ������ܷ��ڷ�����
 *  @SessionAttributes(value={��user1��, ��user2��},types={Dept.class})
 */
@SessionAttributes(value="user",types={String.class})
@RequestMapping("/springmvc")
@Controller
public class RequestMappingTest {
	private static final String SUCCESS="success";
	/**
	 * 1.@RequestMapping ���˿������η����⻹����������
	 * 2.�ඨ�崦���ṩ����������ӳ����Ϣ������� WEB Ӧ�õĸ�Ŀ¼�����������ṩ��һ����ϸ��ӳ����Ϣ��������ඨ�崦�� URL��
	 * 	���ඨ�崦δ��ע @RequestMapping���򷽷�����ǵ� URL �����WEB Ӧ�õĸ�Ŀ¼
	 * @return
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping(){
		System.out.println("test requestMapping");
		return SUCCESS;
	}
	
	/**
	 * ���ã�ʹ��method ����ָ�����󷽷�
	 * @return
	 */
	@RequestMapping(value ="/testMethod",method=RequestMethod.POST)
	public String testMethod(){
		System.out.println("���󷽷��Ĺ���");
		return SUCCESS;
	}
	/**
	 * �˽⣺ʹ��params������headers���Ӿ�ȷ��ӳ������params��headers֧�ּ򵥵ı��ʽ��
	 * @return
	 */
	@RequestMapping(value ="/testParamsAndHeader",params={"username","age!=10"},headers={"Accept-Language=zh-CN,zh;q=0.9,en;q=0.8,und;q=0.7"})
	public String testParamsAndHeader(){
		System.out.println("��������������˺�����ͷ���ˣ�");
		return SUCCESS;
	}
	/**
	 * �˽⣺
	 * Ant �����Դ��ַ֧�� 3 ��ƥ�����
	 *  ?��ƥ���ļ����е�һ���ַ�
	 *  *��ƥ���ļ����е������ַ�
	 *  **��** ƥ����·��
	 * @return
	 */
	@RequestMapping(value="/testAntUrl/*/abc")
	public String testAntUrl(){
		System.out.println("ant ��������·��");
		return SUCCESS;
	}
	/**
	 * @PathVariable ������ӳ��URL�е�ռλ����Ŀ�귽���Ĳ�����
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id){
		System.out.println(id);
		return SUCCESS;
	}
	/**
	 * rest����URL��
	 * ��CRUDΪ�������� POST
	 * �޸� PUT
	 * ��ȡ��GET
	 * ɾ����DELETE
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
	 * �˴�ͨ���������¹�������POST����ת��ΪPUT��DELETE����
	 * <filter>
	 * 	<filter-name>hiddenHttpMethodFilter</filter-name>
	 * 	<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
	 * </filter>
	 * <filter-mapping>
	 * <filter-name>hiddenHttpMethodFilter</filter-name>
	 * <!--������������-->
	 * <url-pattern>/*</url-pattern>
	 * </filter-mapping>
	 * �����ڷ�������ʱ��Ҫ ����������������ʹ��  �磺<input type="hidden" name="_method" value="DELETE">
	 * name��valueΪ�̶�ֵ
	 * <form action="springmvc/testRest/1" method="post">
	 * 	<input type="hidden" name="_method" value="DELETE">
	 * 	<input type="submit" value="Test Rest DELETE">
	 * </form>
	 * 
	 * ʹ��@PathVariable ע��õ�Ŀ�귽���еĲ���
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
	 * @RequestParam ��ӳ���������������valueֵ��Ϊ��������Ĳ�������required��Ϊ�Ƿ���룬����defaultValueָ��Ĭ��ֵ
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
	 * @RequestHeader:�÷�ͬ@RequestParam������ӳ������ͷ�������˽�
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
	 * �˽⣺@CookieValue ӳ��һ��cookieֵ����@RequestParam����
	 * @param sessionId
	 * @return
	 */
	@RequestMapping(value="/testCookieValue")
	public String testCookieValue(@CookieValue(value="JSESSIONID") String sessionId){
		System.out.println("@Accept-Language��sessionId"+sessionId);
		return SUCCESS;
	}
	/**
	 * Spring MVC �ᰴ����������� POJO �����������Զ�ƥ�䣬�Զ�Ϊ�ö����������ֵ��֧�ּ�������
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/testPojo")
	public String testPojo(User user){
		System.out.println(user);
		return SUCCESS;
	}
	/**
	 * ����ʹ��Servletԭ����API��ΪĿ�귽���Ĳ��������Խ������²�����
	 * HttpServletRequest��HttpServletResponse��HttpSession��java.security.Principal��Locale��InputStream
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
	 * Ŀ�귽���ķ���ֵ����ʱModelAndView���ͣ����п��԰�����ͼ��ģ����Ϣ
	 * springmvc���ModelAndView��model�е����ݷ��뵽request������С�
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/testModelAndView")
	public ModelAndView testModelAndView(){
		ModelAndView mav=new ModelAndView(SUCCESS);
		//���ģ�����ݵ�ModelAndView��
		mav.addObject("time", new Date());
		return mav;
	}
	/**
	 * ������������Ϊ Map �� Model ���ͣ�Spring MVC �Ὣ����ģ�͵����ô��ݸ���Щ��Ρ��ڷ������ڣ�
	 * �����߿���ͨ�������ζ�����ʵ�ģ���е��������ݣ�Ҳ������ģ��������µ���������
	 * Ŀ�귽���������Map���͵Ĳ�����Ҳ������Model���ͻ�ModelMap���͵Ĳ���
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
	 * @SessionAttributes ���˿���ͨ��������ָ����Ҫ�ŵ��Ự�е������⣬
	 * ������ͨ��ģ�����ԵĶ�������ָ����Щģ��������Ҫ�ŵ��Ự��
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
