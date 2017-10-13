<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
Daily History
	<table border="5px">
	<tr>
			<th>Id</th>
			<th>Rate</th>
			<th>amountOfCur</th>
			<th>amountOfPln</th>
			<th>income</th>
			<th>Currency</th>
			<th>Type</th>
			<th>Date</th>
		</tr>
	<c:forEach items="${list}" var="boo">
		
		<tr>
			<td>${boo.id}</td>
			<td>${boo.rate}</td>
			<td>${boo.amountOfCur}</td>
			<td>${boo.amountOfPln}</td>
			<td><c:set var="balance" value="${boo.income}"/>
	<fmt:formatNumber type="number" maxFractionDigits = "2" value="${balance}"/></td>
			<td>${boo.curr.name}</td>
			<td>${boo.type.name}</td>
			<td>${boo.date}</td>
		</tr>
	</c:forEach>
	</table>
	<c:set var="balance" value="${total}"/>
	<h1><fmt:formatNumber type="number" maxFractionDigits = "2" value="${balance}"/></h1>

	
	
	<form method="post">
		 <select name="name" >
          <c:forEach var="cur" items="${curr}" >
            <option value=${cur.name}>${cur.name}</option>
          </c:forEach>
        </select>
		 <input	type="submit" value="Submit">
	</form>
	
	<INPUT Type="BUTTON" Value="Homepage" Onclick="window.location.href='http://localhost:8080/Spring01hibernate/'">
	
	 
	
	
	
	
	
	
	
	
</body>
</html>