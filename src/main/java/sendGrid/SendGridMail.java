package sendGrid;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import mailgun.MailGun;

import org.apache.log4j.LogManager;

import DO.EmailDO;
import DO.Mail;

import com.sendgrid.SendGrid;


public class SendGridMail implements Mail {

	/**
	 * Property File Reading for Key and Password.
	 */
	private static Properties properties = new Properties();
	/**
	 * Property file location
	 */
	private static final String PROP_LOC = "./sendgrid.properties";
	
	 
	/**
	 * singleton instance
	 */
	private static volatile SendGridMail sendGridMail = null;

	/**
	 *  Logging
	 */
	private static final org.apache.log4j.Logger logger = LogManager.getLogger(MailGun.class);

	/**
	 * In case of any failure to send message 
	 the flag will be set so that next messages
	 will not try this method.
	 */
	private boolean currentStatus = true;
	
	// Email and sengrid properties
	private SendGrid.Email email = null;
	private SendGrid sendgrid = null;

	private static void loadProperties() {

		InputStream inputStream = null;
		try{
			inputStream = new FileInputStream(PROP_LOC);
			properties.load(inputStream);
			logger.debug("Properties Loaded");
		}catch(Exception e){
			System.out.println(e);
			logger.error(e.getLocalizedMessage(), e.getCause());
		}

	}
	 
		
	// Constructor
	private SendGridMail(){
		loadProperties();
		sendgrid = new SendGrid(properties.getProperty("name"), properties.getProperty("password"));
		email = new SendGrid.Email();
		email.setFrom(properties.getProperty("from"));
		logger.info("Properties Read" + properties.getProperty("name"));
	}


	public static SendGridMail getInstance(){
		if(sendGridMail == null){
			synchronized (SendGridMail.class) {
				if(sendGridMail == null){
					sendGridMail = new SendGridMail();
				}
			}
		}
		logger.info(SendGridMail.class + " getInstance()");
		return sendGridMail;
	}

	@Override
	public boolean checkStatus() {
		return currentStatus;
	}

	@Override
	public boolean sendEmail(EmailDO input) {
		currentStatus = sendEmailHelper(input);
		return currentStatus;
	}

	public boolean sendEmailHelper(EmailDO input){

		if(input.getTo()==null){

			return false;
		}
		// failOver condition.
		if(input.getSubject().toLowerCase().contains("gun")){
			return false;
		}
		email.addTo(input.getTo());
		email.setSubject(input.getSubject());
		email.setText(input.getText());
		SendGrid.Response response = null;

		try {
			response = sendgrid.send(email);
			System.out.println(response.getMessage());
			System.out.println(response.getStatus());
		}
		catch (Exception e) {
			logger.debug(e.getMessage(),e.getCause());
			return false;
		} 
		logger.info(response.getStatus());
		logger.debug(response.getStatus() +input.toString());
		return response==null?false:response.getStatus() ;

	}


}
