package testService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mailgun.MailGun;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DO.EmailDO;

public class TestMailGun {

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
		boolean result = MailGun.getInstance().sendEmail(data);
		
		assertFalse(result);
	}
	
	@Test
	public void testValid() {
		EmailDO data = new EmailDO(null,"abc@gmail.com","hello", "hello");
		boolean result = MailGun.getInstance().sendEmail(data);
		
		assertTrue(result);
	}
	
	@Test
	public void testFailOver() {
		EmailDO data = new EmailDO(null,"abc@gmail.com","grid", "hello");
		boolean result = MailGun.getInstance().sendEmail(data);
		System.out.println(result);
		assertFalse(result);
	}
}
