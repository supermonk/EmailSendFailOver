package DO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Class is to handle the input data provided by user.
 * @author nbidari
 *
 */
public class EmailDO {
	
	/**
	 * Regex pattern for email, Trick for failOver.
	 */
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;
	
	/**
	 * to : destination Email id
	 */
	private String to;
	/**
	 * from : Source Email id or Service
	 */
	private String from;
	/**
	 * Subject of the email
	 */
	private String subject;
	/**
	 * Content of the email
	 */
	private String text;
	
	public EmailDO(){
		
	}
	
	/**
	 * Constructor for creating the object
	 * @param from
	 * @param to
	 * @param subject
	 * @param text
	 */
	public EmailDO(String from, String to, String subject, String text){
		if(to!=null &&!to.isEmpty()){
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(to);
		// Internal condition of checking Mail regex, if did not match then 
		// destination is set to null and failOver happens.
		if(matcher.matches()){
			this.to =to;
		}else{
			this.to = null;
		}
		}
		
		this.from = from;
		this.subject = subject;
		this.text = text;
	}
	

	// Getter, Setter, Overriding hashCode and Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailDO other = (EmailDO) obj;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
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
	
}