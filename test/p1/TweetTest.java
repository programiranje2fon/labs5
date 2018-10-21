package p1;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import p1.Tweet;

public class TweetTest {
	
	Tweet instance;

	@Before
	public void setUp() throws Exception {
		instance = new Tweet();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}

	@Test (timeout = 2000)
	public void attribute_user() {
		assertEquals("Pocetna vrednost nije 'nepoznato'", "nepoznato", instance.user);
	}
	
	@Test (timeout = 2000)
	public void attribute_tweet() {
		assertEquals("Pocetna vrednost nije 'nepoznato'", "nepoznato", instance.tweet);
	}
	
	@Test (timeout = 2000)
	public void attribute_date() {
		GregorianCalendar dat = new GregorianCalendar();		
		assertEquals("Pocetna vrednost nije trenutni date i vreme", dat.get(GregorianCalendar.YEAR), instance.date.get(GregorianCalendar.YEAR));
		assertEquals("Pocetna vrednost nije trenutni date i vreme", dat.get(GregorianCalendar.MONTH), instance.date.get(GregorianCalendar.MONTH));
		assertEquals("Pocetna vrednost nije trenutni date i vreme", dat.get(GregorianCalendar.DAY_OF_MONTH), instance.date.get(GregorianCalendar.DAY_OF_MONTH));
	}

	@Test (timeout = 2000)
	public void method_setUser() {
		instance.setUser("mika");
		
		assertEquals("Kad se unese 'mika' atribut user ne dobija tu vrednost", "mika", instance.user);
	}

	@Test (timeout = 2000)
	public void method_setUser_Null() {
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance.setUser(null);

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za unet NULL String NE ispisuje se rec GRESKA na ekranu", ispis.trim().equalsIgnoreCase("GRESKA"));
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test (timeout = 2000)
	public void method_setUser_Unknown() {
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance.setUser("nepoznato");

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za unet String 'nepoznato' NE ispisuje se rec GRESKA na ekranu", ispis.trim().equalsIgnoreCase("GRESKA"));
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}
	
	@Test (timeout = 2000)
	public void method_setTweet() {
		instance.setTweet("Moja tweet");
		
		assertEquals("Kad se unese 'Moja tweet' atribut tweet ne dobija tu vrednost", "Moja tweet", instance.tweet);
		
	}

	@Test (timeout = 2000)
	public void method_setTweet_Null() {
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance.setTweet(null);

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za unet NULL String NE ispisuje se rec GRESKA na ekranu", ispis.trim().equalsIgnoreCase("GRESKA"));
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test (timeout = 2000)
	public void method_setTweet_TooLong() {
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance.setTweet("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + 
					"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + 
					"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za unetu predugacku poruku NE ispisuje se rec GRESKA na ekranu", ispis.trim().equalsIgnoreCase("GRESKA"));
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test (timeout = 2000)
	public void method_countHash() {
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
	public void method_checkBirthday_False() {
		GregorianCalendar rodjendan = new GregorianCalendar();
		
		rodjendan.set(2004, 1, 29);
		
		assertFalse("Kad se unese 29.2.2004. godine kao date rodjenja, metoda ne vraca false", instance.checkBirthday(rodjendan));
	}
	
	@Test (timeout = 2000)
	public void method_checkBirthday_Null() {
		assertEquals("Kad se unese NULL kao date rodjenja, metoda ne vraca false", false, instance.checkBirthday(null));
	}
	
	@Test (timeout = 2000)
	public void method_checkBirthday_FutureDate() {
		GregorianCalendar rodjendan = new GregorianCalendar();
		
		rodjendan.set(rodjendan.get(GregorianCalendar.YEAR)+1 , rodjendan.get(GregorianCalendar.MONTH), rodjendan.get(GregorianCalendar.DAY_OF_MONTH));

		assertEquals("Kad se unese date rodjenja iz BUDUCNOSTI, metoda ne vraca false", false, instance.checkBirthday(rodjendan));
	}

}
