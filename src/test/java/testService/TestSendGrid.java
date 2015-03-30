package testService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sendGrid.SendGridMail;
import DO.EmailDO;

public class TestSendGrid {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInValid() {
		EmailDO data = new EmailDO(null,null,null, null);
		boolean result = SendGridMail.getInstance().sendEmail(data);
		
		assertFalse(result);
	}
	
	@Test
	public void testValid() {
		EmailDO data = new EmailDO(null,"abc@gmail.com","hello", "hello");
		boolean result = SendGridMail.getInstance().sendEmail(data);
		
		assertTrue(result);
	}
	
	@Test
	public void testFailOver() {
		EmailDO data = new EmailDO(null,"abc@gmail.com","gun", "hello");
		boolean result = SendGridMail.getInstance().sendEmail(data);
		System.out.println(result);
		assertFalse(result);
	}
}
