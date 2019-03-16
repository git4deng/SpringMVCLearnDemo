<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--helloworld验证 -->
	<a href="hello">HELLO</a><br>
	<!--requestMapping注解验证-->
	<a href="springmvc/testRequestMapping">RequestMappingTest</a><br>
	<!--requestMapping注解method方法参数验证-->
	<form action="springmvc/testMethod" method="post">
		<input type="submit" value="POST提交">
	</form><br>
	<!--参数和headers精确映射测试-->
	<a href="springmvc/testParamsAndHeader?username=david&age=20">参数映射</a><br>
	<!-- Ant 风格资源地址 -->
	<a href="springmvc/testAntUrl/xxx/abc">ant风格的Path</a><br>
	<!--@PathVariable 映射 URL 绑定的占位符  -->
	<a href="springmvc/testPathVariable/2">支持占位符</a><br>
	
	
	<!--HiddenHttpMethodFilter过滤器验证  -->
	<a href="springmvc/testRest/1">Test Rest GET</a><br>
	<form action="springmvc/testRest" method="post">
		<input type="submit" value="Test Rest POST">
	</form>
	<br>
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="DELETE">
	 	<input type="submit" value="Test Rest DELETE">
	</form>
	<br>
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="PUT">
	 	<input type="submit" value="Test Rest PUT">
	</form>
	<br>
	<!--验证RequestParam 获取请求参数 -->
	<a href="springmvc/testRequestParam?username=david&age=20">获取请求参数</a><br>
	<!--验证RequestHeader 获取请求参数 -->
	<a href="springmvc/testRequestHeader">获取RequestHeader请求参数</a><br>
	<!--验证CookieValue 获取请求参数 -->
	<a href="springmvc/testCookieValue">获取CookieValue</a><br>
	
	<!--Spring MVC 会按请求参数名和 POJO 属性名进行自动匹配，自动为该对象填充属性值。支持级联属性-->
	<form action="springmvc/testPojo" method="post">
		username:<input type="text" name="username"><br>
		password:<input type="password" name="password"><br>
		email:<input type="text" name="email"><br>
		age:<input type="text" name="age"><br>
		province:<input type="text" name="address.province"><br>
		city:<input type="text" name="address.city"><br>
		<input type="submit" value="testPojo">
	</form><br>
	<a href="springmvc/testServletAPI">testServletAPI</a><br>
	<a href="springmvc/testModelAndView">testModelAndView</a><br>
	<a href="springmvc/testMap">testMap</a><br>
	
	<a href="springmvc/testSessionAttributes">testSessionAttributes</a><br>
	
	<!--
	模拟修改操作：
	1.原始数据：1,david,123456,xxx@xxx.com,20
	2. 密码不能被修改
	3.表单回显模拟操作直接在表单填写对应的属性值
	-->
	<form action="springmvc/testModelAtrribute" method="post">
		<input type="hidden" name="id" value="1"><br>
		username:<input type="text" name="username" value="david"><br>
		<!-- password:<input type="password" name="password" value="123456"><br> -->
		email:<input type="text" name="email" value="xxx@xxx.com"><br>
		age:<input type="text" name="age" value="20"><br>
		<input type="submit" value="testModelAtrribute update">
	</form><br>
	<a href="springmvc/testViewAndViewResolver">testViewAndViewResolver</a><br>
	<a href="springmvc/testView">testView</a><br>
	<a href="springmvc/testRedirect">Test Redirect</a>
	
	
	<a href="testExceptionHandlerExcption?i=10">异常处理验证</a>
	
	<a href="testResponseStatusExceptionResolver?i=13">异常处理验证</a>
</body>
</html>