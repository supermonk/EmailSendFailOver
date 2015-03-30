<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>END</title>

<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="contact-form" class="clearfix">
		<FORM>
			<h1>Mailing Service!</h1>
			<label for="result">	${resultString}  </label>
			<label for="service">	${service}  </label>
				<span id="loading"></span> <input type="submit" style="width:150px;margin-left:140px" value="Back with History"
				onClick="history.go(-1);return true;"id="submit-button" />
				
		</FORM>
		<form name="emailForm" action="/Email" method="get">
			<span id="loading"></span> <input type="submit"   style="width:150px;margin-left:140px"value="Back" id="sub"/>
			
		</form>
	</div>
</body>
</html>



