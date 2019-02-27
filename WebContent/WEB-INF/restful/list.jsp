<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--
	springMVC处理静态资源：
	1.优雅的 REST 风格的资源URL 不希望带 .html 或 .do 等后缀。若将 DispatcherServlet 请求映射配置为 /，则 Spring MVC 将捕获
	WEB 容器的所有请求，包括静态资源的请求， SpringMVC 会将他们当成一个普通请求处理，因找不到对应处理器将导致错误
	2.可以在 SpringMVC 的配置文件中配置 <mvc:default-servlethandler/> 的方式解决静态资源的问题
-->

<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>

<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			/*a 标签的请求是get,此处利用js手法将a标签href属性值赋给form标签的action,再利用form的post提交转换为DELETE请求
				从而实现了restful风格的请求
			*/
			var href=$(this).attr("href");
			$("form").attr("action",href).submit();
			return false;
		});
	});
</script>
<title>员工信息列表</title>
</head>
<body>
	
	
	<c:if test="${empty requestScope.employees }">
	 无任何员工信息！
	</c:if>
	<c:if test="${!empty requestScope.employees }">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>ID</th>
				<th>LASTNAME</th>
				<th>EMAIL</th>
				<th>GENDER</th>
				<th>DEPARTMENT</th>
				<th>EDIT</th>
				<th>DELETE</th>
			</tr>
			<c:forEach items="${requestScope.employees }" var="emp">
				<tr>
					<td>${emp.id}</td>
					<td>${emp.lastName}</td>
					<td>${emp.email}</td>
					<td>${emp.gender==0?'女':'男'}</td>
					<td>${emp.department.departmentName}</td>
					<td><a href="emp/${emp.id}">编辑</a></td>
					<td><a href="emp/${emp.id}" class="delete">删除</a></td>
				</tr>
			
			</c:forEach>
		</table>
	</c:if>
	<!--此处处理主要是配合hiddenHttpMethodFilter将post请求转换为delete请求-->
	<form action="" method="post">
		<input type="hidden" name="_method" value="DELETE">
	</form>
	<br><br>
	<a href="emp">新增员工</a>
	
</body>
</html>