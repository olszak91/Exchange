<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
	  $("#datepicker").datepicker({ dateFormat: "yy-mm-dd" }).val()
  
  } );
  
  $( function() {
	  $("#datepicker2").datepicker({ dateFormat: "yy-mm-dd" }).val()
	  } );
  </script>
</head>
<body>
	<h1>History</h1>
	<form method="post">
		
		From: <p>Date: <input type="text"   id="datepicker" name="date1"></p>
		To: <p>Date: <input type="text"  id="datepicker2" name="date2"></p>
		
		 <input	type="submit" value="Submit">
	</form>
	<INPUT Type="BUTTON" Value="Homepage" Onclick="window.location.href='http://localhost:8080/Spring01hibernate/'">
	
</body>
</html>