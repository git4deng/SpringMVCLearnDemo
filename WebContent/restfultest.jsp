<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index page</title>
</head>
<body>

	<a href="emps">获取所有员工信息列表</a><br>
	
	<a href="testJson">验证返回json字符串</a><br>
	<form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
		File:<input type="file" name="file"><br>
		Desc:<input type="text" name="desc"><br>
		<input type="submit" value="submit"><br>
	</form>
	<a href="testResponseEntity">ResponseEntity制作文件下载</a><br>
</body>
</html>