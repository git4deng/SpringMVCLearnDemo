<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${!empty path }">
	<h4>上传成功！</h4>
		文件名字：${filename }<br>
		文件存储路径：${path }<br>
		请点击<a href="filedownload?path=${path }&fileName=${filename }">${filename }</a>下载！
	</c:if>
	
	<c:if test="${ !empty filenames }">
	<h4>上传成功！</h4>
		<c:forEach items="${requestScope.filenames }" var="name">
		文件名字：${name}<br>
		</c:forEach>
	</c:if>
</body>
</html>