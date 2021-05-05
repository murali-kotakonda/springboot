<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
<jsp:include page="menu.jsp"/> <br/>
 <h2>Showing Product ${p.articleId} </h2>
<table border="2" width="70%" cellpadding="2">  
<tr>
<th>ArticleId</th>
<th>Title</th>
<th>Category</th>
<th>Action</th>

</tr>  
	   <tr>  
	   <td>${p.articleId}</td>  
	   <td>${p.title}</td>  
	   <td>${p.category}</td>  
	   <td><a href='Updateproduct?id=${p.articleId}'>Edit</a></td>
   
   </table>  
  