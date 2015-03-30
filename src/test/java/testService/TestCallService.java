package testService;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DO.EmailDO;
import service.CallService;

public class TestCallService {

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
	public void inValidtest() {
		EmailDO data = new EmailDO(null,null,null, null);
		boolean result = CallService.sendMail(data);
		
		assertFalse(result);
	}
	
	@Test
	public void Validtest() {
		EmailDO data = new EmailDO(null,"abc@gmail.com","hello", "hello");
		boolean result = CallService.sendMail(data);
		
		assertTrue(result);
	}


}
