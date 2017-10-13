<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style2.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.error {
	background-color: red;
	color: white;
}
</style>
</head>
<body>
	<h1>Transaction</h1>
	<form:form method="post" modelAttribute="transaction">

		<table  >
			<tr>
				<td>Currency:</td>
				<td><form:select path="curr" itemLabel="name" itemValue="name"
						items="${currs}" /> <form:errors path="curr" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Type:</td>
				<td><form:select path="type" itemLabel="name" itemValue="name"
						items="${types}" /> <form:errors path="type" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td>Rate:</td>
				<td><form:input type="number" path="rate" /> <form:errors
						path="rate" cssClass="error" /></td>

			</tr>
			<tr>
				<td>AmountOfCurr:</td>
				<td><form:input type="number" path="amountOfCur" /> <form:errors
						path="amountOfCur" cssClass="error" /></td>

			</tr>
			<tr>
				<td>AmountOfPLN:</td>
				<td><form:input type="number" path="amountOfPln" />
				<form:errors path="amountOfPln" cssClass="error" /></td>

			</tr>
		</table>




		<input type="submit" value="Save">
	</form:form>
</body>
</html>