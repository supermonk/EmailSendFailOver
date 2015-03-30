package mailgun;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.LogManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import DO.EmailDO;
import DO.Mail;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;


@Configuration
@ComponentScan("hello.*")
public  class MailGun implements Mail{

	/**
	 * Property File Reading for Key and Password.
	 */
	private static Properties properties = new Properties();
	/**
	 * Property file location
	 */
	private static final String PROP_LOC = "./mailgun.properties";

	/**
	 * singleton instance
	 */
	private static volatile MailGun mailGun = null;

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

	private static Client client = null;

	private static WebResource webResource = null;

	private static MultivaluedMapImpl formData = null;


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

	private MailGun(){
		loadProperties(); 
		
		client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api",
				properties.getProperty("key")));
		webResource =
				client.resource("https://api.mailgun.net/v3/sandbox"+properties.getProperty("token")+".mailgun.org/messages");
		formData = new MultivaluedMapImpl();
		formData.add("from", "Mailgun Sandbox <postmaster@sandbox"+properties.getProperty("token")+".mailgun.org>");
		logger.info("Properties Read"+MailGun.class);
	}


	public static MailGun getInstance(){
		if(mailGun == null){
			synchronized (MailGun.class) {
				if(mailGun == null){
					mailGun = new MailGun();

				}
			}
		}
		logger.info(MailGun.class + " getInstance()");
		return mailGun;
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
		if(input.getSubject().toLowerCase().contains("grid")){
			return false;
		}

		formData.add("to", input.getTo());
		formData.add("subject", input.getSubject());
		formData.add("text", input.getText());

		ClientResponse result = null;
		try {
			result= webResource.type(MediaType.APPLICATION_FORM_URLENCODED ).
					post(ClientResponse.class, formData);

		}
		catch (Exception e) {
			logger.debug(e.getMessage(),e.getCause());
			return false;
		} 
		logger.info(result.getStatus());
		logger.debug(result.getStatus() +input.toString());

		// Check if result is null ,else check if succesfful by HTTP Code
		return result==null?false:(result.getStatus()==200);
	}


}
