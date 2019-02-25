<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>SUCCESS PAGE!</h4>
	time:${ requestScope.time }<br>
	names:${ requestScope.names }<br>
	email:${ requestScope.email }<br>
	request user:${ requestScope.user }<br>
	session user:${ sessionScope.user }<br>
	request school:${ requestScope.school }<br>
	session school:${ sessionScope.school }<br>
</body>
</html>