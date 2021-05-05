<%@taglib 
uri="http://www.springframework.org/tags/form" 
prefix="form"%>


<html>
<head>
</head>

<body>
<jsp:include page="menu.jsp"/> <br/>

<h2>Add new Product</h2>
<font color="red">${msg }</font>
 
 
<form:form method="post" action="addpdt">
	<table>
	<tr>
		<td><form:label path="articleId">Article Id</form:label></td>
		<td><form:input path="articleId" /></td>
	</tr>
	<tr>
		<td><form:label path="title">Title</form:label></td>
		<td><form:input path="title" /></td>
	</tr>
	<tr>
		<td><form:label path="category">Category</form:label></td>
		<td><form:input path="category" /></td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="submit" value="AddProducts"/>
		</td>
	</tr>
</table>	
	
</form:form>
</body>
</html>
 