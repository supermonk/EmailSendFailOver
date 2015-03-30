package DO;


/**
 * Interface which declares basic functionality for all Mail Delivery clients
 * @author nbidari
 *
 */
public interface Mail {
	/**
	 * Method to send email
	 * @param email
	 * @return
	 */
	public boolean sendEmail(EmailDO email);
	/**
	 * This method is for API call's
	 * @param json
	 * @return
	 */
	//public boolean sendEmailJson(JsonObject json);
	/**
	 *  additional flag to check the health of Mailing Service    
	 * @return
	 */
	public boolean checkStatus();
}
