<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
</head>

<body>
<jsp:include page="menu.jsp"/> <br/>

<h2>Update Product</h2>

${msg }
<form:form method="post" action="updateProduct1">
		
	<table>
	<tr>
		<td><form:label path="articleId">ArticleId</form:label></td>
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
			<input type="submit" value="Update Product"/>
		</td>
	</tr>
</table>	
	
</form:form>
</body>
</html>
