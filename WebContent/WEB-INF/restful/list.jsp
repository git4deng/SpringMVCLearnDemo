<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
					<td>${emp.lastName}emp.id</td>
					<td>${emp.email}</td>
					<td>${emp.gender==0?'女':'男'}</td>
					<td>${emp.department.departmentName}</td>
					<td>编辑</td>
					<td>删除</td>
				</tr>
			
			</c:forEach>
		</table>
	</c:if>
	
</body>
</html>