package DO;


/**
 * This Class is to handle the input data provided by user.
 * @author nbidari
 *
 */
public class EmailForm {
	/**
	 * to : destination Email id
	 */
	private String to;
	/**
	 * from : Source Email id or Service
	 */
	
	private String subject;
	/**
	 * Content of the email
	 */
	private String text;
	
	
	
	
	
	

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "EmailForm [to=" + to + ", subject=" + subject + ", text="
				+ text + "]";
	}
	
	
	
	
	
	
	
	
}