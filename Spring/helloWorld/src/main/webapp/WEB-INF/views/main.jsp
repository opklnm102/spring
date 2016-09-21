<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>페이지 선택</h1>

	<form action="/hello/list" method="get">

		페이지 번호<input type="text" name="page" value="1"> </br>
		<input type="radio" name="size" value="5" checked> 5건 <br>
		<input type="radio" name="size" value="10"> 10건 <br>
			<input type="radio" name="size" value="15"> 15건 <br>

		<input type="submit" value="출력">

	</form>
</body>
</html>




