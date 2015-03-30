package service;

import mailgun.MailGun;

import org.apache.log4j.LogManager;

import sendGrid.SendGridMail;
import DO.EmailDO;
import DO.Mail;

public class CallService {
	
	//get the singleton instance
	private static Mail mailStrategy = SendGridMail.getInstance();;
	
	/**
	 *  Logging
	 */
	private static final org.apache.log4j.Logger logger = LogManager.getLogger(CallService.class);
	
	public static boolean sendMail(EmailDO data){
		// try sending email with first stragey
		logger.info("trying via"+ mailStrategy.getClass());
		boolean result = mailStrategy.sendEmail(data);
		// if it fails try other.
		if(result==false){
			// if else trying to switch between Options
			if(mailStrategy.getClass()==MailGun.class){
				mailStrategy = SendGridMail.getInstance();
				logger.info("trying via"+ mailStrategy.getClass());
				boolean attempt = mailStrategy.sendEmail(data);
				
				if(attempt==false){
					logger.error("Error  both systems are down");
					return false;
				}else{
					logger.info("sent via"+ mailStrategy.getClass());
					return true;
				}
			}else{
				logger.info("trying via"+ mailStrategy.getClass());
				mailStrategy = MailGun.getInstance();
				boolean attempt = mailStrategy.sendEmail(data);
				if(attempt==false){
					logger.error("Error  both systems are down");
					return false;
				}else{
					logger.info("sent via"+ mailStrategy.getClass());
					return true;
				}
			}
		}
		logger.info("sent via"+ mailStrategy.getClass());
		return result;
	}

	public static Mail getMailStrategy() {
		return mailStrategy;
	}
 


}
