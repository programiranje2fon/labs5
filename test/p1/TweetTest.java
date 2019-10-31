package p1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TweetTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	private Tweet instance;

	@Before
	public void setUp() throws Exception {
		instance = new Tweet();
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
		System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	@Test (timeout = 2000)
	public void attribute_user() {
		assertEquals("The initial value is not 'unknown'", "unknown", instance.user);
	}
	
	@Test (timeout = 2000)
	public void attribute_tweet() {
		assertEquals("The initial value is not 'unknown'", "unknown", instance.tweet);
	}
	
	@Test (timeout = 2000)
	public void attribute_date() {
		GregorianCalendar dat = new GregorianCalendar();		
		assertEquals("The initial value is not the current date and time", dat.get(GregorianCalendar.YEAR), instance.date.get(GregorianCalendar.YEAR));
		assertEquals("The initial value is not the current date and time", dat.get(GregorianCalendar.MONTH), instance.date.get(GregorianCalendar.MONTH));
		assertEquals("The initial value is not the current date and time", dat.get(GregorianCalendar.DAY_OF_MONTH), instance.date.get(GregorianCalendar.DAY_OF_MONTH));
	}

	@Test (timeout = 2000)
	public void method_setUser() {
		instance.setUser("mike");
		
		assertEquals("When the argument 'mike' is passed, the attribute user does not have that value", "mike", instance.user);
	}

	@Test (timeout = 2000)
	public void method_setUser_Null() {
		instance.setUser(null);

		assertTrue("For the argument NULL, the method does not print ERROR to the console", outContent.toString().toLowerCase().contains("ERROR".toLowerCase()));
	}

	@Test (timeout = 2000)
	public void method_setUser_Unknown() {
		instance.setUser("unknown");

		assertTrue("For the argument NULL 'unknown', the method does not print ERROR to the console", outContent.toString().toLowerCase().contains("ERROR".toLowerCase()));
	}
	
	@Test (timeout = 2000)
	public void method_setTweet() {
		instance.setTweet("Good morning");
		
		assertEquals("For the argument 'Good morning', the attribute tweet doe not contain that String", "Good morning", instance.tweet);
		
	}

	@Test (timeout = 2000)
	public void method_setTweet_Null() {
		instance.setTweet(null);

		assertTrue("For the argument NULL, the method does not print ERROR to the console", outContent.toString().toLowerCase().contains("ERROR".toLowerCase()));
	}

	@Test (timeout = 2000)
	public void method_setTweet_TooLong() {
		instance.setTweet("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + 
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + 
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		assertTrue("When an argument with more than 140 letters is passed, the method does not print ERROR to the console", outContent.toString().toLowerCase().contains("ERROR".toLowerCase()));
	}

	@Test (timeout = 2000)
	public void method_countHash() {
		instance.setTweet("Good morning #sun #moring.");
		
		assertEquals("When the attribute tweet has the value 'Good morning #sun #moring.', the method does not return 2 for the number of tags", 2, instance.countHash());
	}

	@Test (timeout = 2000)
	public void method_countWords() {
		instance.setTweet("Good morning #sun #moring.");
		
		assertEquals("When the attribute tweet has the value 'Good morning #sun #moring.', the method does not return 4 for the number of words", 4, instance.countWords());
	}

	@Test (timeout = 2000)
	public void method_checkBirthday_True() {
		GregorianCalendar rodjendan = new GregorianCalendar();
		
		rodjendan.set(2001, rodjendan.get(GregorianCalendar.MONTH), rodjendan.get(GregorianCalendar.DAY_OF_MONTH));
		
		assertTrue("When the tweet date is set to the current day, if the passed argument is the current date, but with the year set to 2001, the method does not return true", instance.checkBirthday(rodjendan));
	}
	
	@Test (timeout = 2000)
	public void method_checkBirthday_False() {
		GregorianCalendar rodjendan = new GregorianCalendar();
		
		rodjendan.set(2004, 1, 29);
		
		assertFalse("When the tweet date is set to the current day, if the passed argument is 29.02.2004., the method does not return false", instance.checkBirthday(rodjendan));
	}
	
	@Test (timeout = 2000)
	public void method_checkBirthday_Null() {
		assertEquals("When passed argument is NULL, the method should return false", false, instance.checkBirthday(null));
	}
	
	@Test (timeout = 2000)
	public void method_checkBirthday_FutureDate() {
		GregorianCalendar rodjendan = new GregorianCalendar();
		
		rodjendan.set(rodjendan.get(GregorianCalendar.YEAR)+1 , rodjendan.get(GregorianCalendar.MONTH), rodjendan.get(GregorianCalendar.DAY_OF_MONTH));

		assertEquals("When argument passed is a date from the future, the method should return false", false, instance.checkBirthday(rodjendan));
	}

}
