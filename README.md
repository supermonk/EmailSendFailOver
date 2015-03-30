Mailing Services Used
1.	SendGrid 
a.	Propertyfile : sendgrid.properties
b.	ClassName : SendGridMail.java
c.	Test  : TestSendGrid
d.	
2.	MailGun
a.	Propertyfile:mailgun.properties.
b.	ClassName : MailGun.java
c.	Test  : TestMailGun
JSP
1.	Email.Jsp
a.	Accepts user input
2.	End.jsp
a.	Displays result
b.	If successful also displays which service used.
Controllers
1.	/sendMail  -- tied to application
a.	Recives the user input and directs the request towars CallService.sendMail class
b.	Returns the result in Model
2.	/sendJsonMail  -- Rest POST call
a.	If passed proper JSON object of type EmailDO then directs towards CallService.sendMail class
b.	Returns JSON contained result and type of service
3.	/Email
a.	Tied to Email.jsp

FailOver
1.	The project says to try failover between two services, we can achieve by following ways
a.	Send “grid” in subject to send mail via SendGrid
i.	If we are currently using MailGun the application changes over its service to SendGrid
b.	Send “gun” in subject to send via MailGun
i.	If we are currently using SendGrid the application changes over its service to MailGun
c.	In case we have more than one services we can implement something close to Observer design Pattern. While maintain a list of Services in an Array


