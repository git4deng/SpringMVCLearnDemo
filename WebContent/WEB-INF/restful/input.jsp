<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增员工</title>
</head>
<body>
	<!--  
		1. WHY 使用 form 标签呢 ?
		可以更快速的开发出表单页面, 而且可以更方便的进行表单值的回显
		2. 注意:
		可以通过 modelAttribute 属性指定绑定的模型属性,
		若没有指定该属性，则默认从 request 域对象中读取 command 的表单 bean
		如果该属性值也不存在，则会发生错误。
	-->
	<form:form action="${pageContext.request.contextPath }/emp" method="POST" modelAttribute="employee">
		<%-- *表示所有的错误信息都显示在这里 --%>
		<form:errors path="*"></form:errors><br>
		
		<%--path 对应html的form标签的name属性 --%>
		<c:if test="${employee.id==null }">
			LastName:<form:input path="lastName"/><br>
			<%--放在对应的字段位置，path等于字段name属性，直接显示对应的错误信息--%>
			<form:errors path="lastName"></form:errors><br>
		</c:if>
		<c:if test="${employee.id!=null }">
			<form:hidden path="id"/>
			<%--注意此处：不能使用<form:hidden标签，因为modelAttribute对应的bean中没有_method这个属性
			<form:form中如果存在modelAttribute对应bean中没有是属性，会抛出异常，因为<form:form>方便属性的回显	
			--%>
			<input type="hidden" name="_method" value="PUT">
		</c:if>
		
		Email:<form:input path="email"/><br>
		<form:errors path="email"></form:errors><br>
		<%
			Map<String,String> genders=new HashMap<String,String>();
			genders.put("1", "男");
			genders.put("0", "女");
			request.setAttribute("genders", genders);
		%>
		Gender:<form:radiobuttons path="gender" items="${genders}"/><br>
		Department:<form:select path="department.id" items="${depts }" itemLabel="departmentName" itemValue="id"></form:select><br>
		<!--  
			1. 数据类型转换
			2. 数据类型格式化
			3. 数据校验. 
			1). 如何校验 ? 注解 ?
			①. 使用 JSR 303 验证标准
			②. 加入 hibernate validator 验证框架的 jar 包
			③. 在 SpringMVC 配置文件中添加 <mvc:annotation-driven />
			④. 需要在 bean 的属性上添加对应的注解
			⑤. 在目标方法 bean 类型的前面添加 @Valid 注解
			2). 验证出错转向到哪一个页面 ?
			注意: 需校验的 Bean 对象和其绑定结果对象或错误对象时成对出现的，它们之间不允许声明其他的入参
			3). 错误消息 ? 如何显示, 如何把错误消息进行国际化
		-->
		Birth:<form:input path="birth"/><br>
		<form:errors path="birth"></form:errors><br>
		salary:<form:input path="salary"/><br>
		<input type="submit" value="提交">
	</form:form>
	
	<!--自定义类型转换器验证-->
	<form action="testConversionServiceConverer" method="POST">
		<!-- lastname-email-gender-department.id 例如: GG-gg@atguigu.com-0-105 -->
		Employee: <input type="text" name="employee"/>
		<input type="submit" value="Submit"/>
	</form>
	<br><br>
</body>
</html>