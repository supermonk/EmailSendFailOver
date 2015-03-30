<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="js/find.js" type="text/javascript"></script>



<link rel="stylesheet" href="css/style.css">
</head>
<body>

 

<span id ="result"></span>

	<div id="contact-form" class="clearfix">
		<h1>Mailing Service!</h1>



		<ul id="errors" class="">

		</ul>


		<form name="emailForm" action="/sendMail" method="post">

			<!-- Name -->
			<label for="name">Name: <span class="required">*</span></label> <input
				type="text" id="name" name="name" value="" placeholder="Naren "
				required="required" autofocus="autofocus" />

			<!-- To Email Address -->
			<label for="email">Email Address: <span class="required">*</span></label>

			<input type="email" id="to" name="to" value=""
				placeholder="abc.def@gmail.com"
				required="required" />

			<!-- Subject -->
			<label for="name">Subject: <span class="required">*</span></label> <input
				type="text" id="subject" name="subject" value=""
				placeholder="hellow " required="required" autofocus="autofocus" />

			<!-- Message -->
			<label for="message">Email Message: <span class="required">*</span></label>
			<textarea id="message" name="text" placeholder="Enter your text here"
				required="required" data-minlength="20"></textarea>
				<!-- CurrentService -->
				<label for="message">${service}  </label></b>
				<label>To Change service / failover add below text in SUBJECT</label></b>
				<label>'gun' for MailGUN 'grid' for SendGrid</label>
			<!-- Submit -->

			<span id="loading"></span> <input type="submit" style="width:150px;margin-left:140px" value="Submit"
				id="submit-button" />

		</form>
	</div>

</body>
</html>