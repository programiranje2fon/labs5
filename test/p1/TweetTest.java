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
	public void atribut_user() {
		assertEquals("Pocetna vrednost nije 'unknown'", "unknown", instance.user);
	}
	
	@Test (timeout = 2000)
	public void atribut_tweet() {
		assertEquals("Pocetna vrednost nije 'unknown'", "unknown", instance.tweet);
	}
	
	@Test (timeout = 2000)
	public void atribut_date() {
		GregorianCalendar dat = new GregorianCalendar();		
		assertEquals("Pocetna vrednost nije trenutni date i vreme", dat.get(GregorianCalendar.YEAR), instance.date.get(GregorianCalendar.YEAR));
		assertEquals("Pocetna vrednost nije trenutni date i vreme", dat.get(GregorianCalendar.MONTH), instance.date.get(GregorianCalendar.MONTH));
		assertEquals("Pocetna vrednost nije trenutni date i vreme", dat.get(GregorianCalendar.DAY_OF_MONTH), instance.date.get(GregorianCalendar.DAY_OF_MONTH));
	}

	@Test (timeout = 2000)
	public void metoda_setUser() {
		instance.setUser("mika");
		
		assertEquals("Kad se unese 'mika' atribut user ne dobija tu vrednost", "mika", instance.user);
	}

	@Test (timeout = 2000)
	public void metoda_setUser_Null() {
		instance.setUser(null);

		assertTrue("Za unet NULL String NE ispisuje se rec ERROR na ekranu", outContent.toString().trim().equalsIgnoreCase("ERROR"));
	}

	@Test (timeout = 2000)
	public void metoda_setUser_Unknown() {
		instance.setUser("unknown");

		assertTrue("Za unet String 'unknown' NE ispisuje se rec ERROR na ekranu", outContent.toString().trim().equalsIgnoreCase("ERROR"));
	}
	
	@Test (timeout = 2000)
	public void metoda_setTweet() {
		instance.setTweet("Moja tweet");
		
		assertEquals("Kad se unese 'Moja tweet' atribut tweet ne dobija tu vrednost", "Moja tweet", instance.tweet);
		
	}

	@Test (timeout = 2000)
	public void method_setTweet_Null() {
		instance.setTweet(null);

		assertTrue("Za unet NULL String NE ispisuje se rec ERROR na ekranu", outContent.toString().trim().equalsIgnoreCase("ERROR"));
	}

	@Test (timeout = 2000)
	public void metoda_setTweet_TooLong() {
		instance.setTweet("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + 
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + 
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		assertTrue("Za unetu predugacku poruku NE ispisuje se rec ERROR na ekranu", outContent.toString().trim().equalsIgnoreCase("ERROR"));
	}

	@Test (timeout = 2000)
	public void metoda_countHash() {
		instance.setTweet("Moja tweet sa #jednim ili #dva hes taga.");
		
		assertEquals("Ako se unese tweet 'Moja tweet sa #jednim ili #dva hes taga.', ne prebrojava dva taga", 2, instance.countHash());
	}

	@Test (timeout = 2000)
	public void method_countWords() {
		instance.setTweet("Moja tweet sa #jednim ili #dva hes taga.");
		
		assertEquals("Ako se unese tweet 'Moja tweet sa #jednim ili #dva hes taga.', ne prebrojava osam reci", 8, instance.countWords());
	}

	@Test (timeout = 2000)
	public void method_checkBirthday_True() {
		GregorianCalendar rodjendan = new GregorianCalendar();
		
		rodjendan.set(2001, rodjendan.get(GregorianCalendar.MONTH), rodjendan.get(GregorianCalendar.DAY_OF_MONTH));
		
		assertTrue("Kad se unese danasnji dan i mesec ali 2001. godine kao date rodjenja, metoda ne vraca true", instance.checkBirthday(rodjendan));
	}
	
	@Test (timeout = 2000)
	public void metoda_checkBirthday_False() {
		GregorianCalendar rodjendan = new GregorianCalendar();
		
		rodjendan.set(2004, 1, 29);
		
		assertFalse("Kad se unese 29.2.2004. godine kao date rodjenja, metoda ne vraca false", instance.checkBirthday(rodjendan));
	}
	
	@Test (timeout = 2000)
	public void metoda_checkBirthday_Null() {
		assertEquals("Kad se unese NULL kao date rodjenja, metoda ne vraca false", false, instance.checkBirthday(null));
	}
	
	@Test (timeout = 2000)
	public void metoda_checkBirthday_FutureDate() {
		GregorianCalendar rodjendan = new GregorianCalendar();
		
		rodjendan.set(rodjendan.get(GregorianCalendar.YEAR)+1 , rodjendan.get(GregorianCalendar.MONTH), rodjendan.get(GregorianCalendar.DAY_OF_MONTH));

		assertEquals("Kad se unese date rodjenja iz BUDUCNOSTI, metoda ne vraca false", false, instance.checkBirthday(rodjendan));
	}

}
