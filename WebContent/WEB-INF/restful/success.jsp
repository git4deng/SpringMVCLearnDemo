<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>上传成功！</h4>
	文件名字：${filename }<br>
	文件存储路径：${path }
	请点击<a href="filedownload?path=${path }&fileName=${filename }">${filename }</a>下载！

</body>
</html>