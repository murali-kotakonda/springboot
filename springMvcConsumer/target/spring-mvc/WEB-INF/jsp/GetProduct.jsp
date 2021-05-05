<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="menu.jsp"/> <br/>

	<h3>Get Product</h3>
	${msg}
	
	<form action="GetProductById1" method="get">
		Enter Id:
		<input type="text" name="id" id="">
		</br>
		<input type="submit" name="submit" value="Submit">
	</form>

</body>
</html>