<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
<link rel="stylesheet" href="css/style.css" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<img id="logo" src="${pageContext.request.contextPath}/images/logo.png" />


	<table>
		<tr>
			<td><div class="col-md-3">
			<div class="square-service-block">
					<a href='addCurr'>
						<div class="ssb-icon">
							<i class="fa fa-plus" aria-hidden="true"></i>
						</div>
						<h2 class="ssb-title">New CURRENCY</h2>

					</a>
				</div></td>
			<td><div class="col-md-3">
			<div class="square-service-block">
					<a href='history'>
						<div class="ssb-icon">
							<i class="fa fa-calendar" aria-hidden="true"></i>
						</div>
						<h2 class="ssb-title">HISTORY</h2>

					</a>
				</div></td>
			<td><div class="col-md-3">
			<div class="square-service-block">
					<a href='transaction'>
						<div class="ssb-icon">
							<i class="fa fa-usd" aria-hidden="true"></i>
						</div>
						<h2 class="ssb-title">New transaction</h2>

					</a>
				</div></td>

		</tr>
		<tr>
			<td><div class="col-md-3">
			<div class="square-service-block">
					<a href='addPLN'>
						<div class="ssb-icon">
							<i class="fa fa-money" aria-hidden="true"></i>
						</div>
						<h2 class="ssb-title">ADD PLN</h2>

					</a>
				</div></td>
			<td><div class="col-md-3">
			<div class="square-service-block">
					<a href='dailySummary'>
						<div class="ssb-icon">
							<i class="fa fa-history" aria-hidden="true"></i>
						</div>
						<h2 class="ssb-title">DAILY SUMMARY</h2>

					</a>
				</div></td>
			<td><div class="col-md-3">
			<div class="square-service-block">
					<a href='delete'>
						<div class="ssb-icon">
							<i class="fa fa-minus" aria-hidden="true"></i>
						</div>
						<h2 class="ssb-title">DELETE</h2>

					</a>
				</div></td>

		</tr>
	</table>















</body>
</html>