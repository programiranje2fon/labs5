package p2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HistoricEventTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	private HistoricEvent instance;

	@Before
	public void setUp() throws Exception {
		instance = new HistoricEvent("Oslobadjanje Beograda", 1945, 10, 20);
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
	public void konstruktor_HistoricEvent() {
		instance = new HistoricEvent("Oslobadjanje Beograda", 1945, 10, 20);
		
		assertEquals("Konstruktor ne postavlja title kako treba", "Oslobadjanje Beograda", instance.title);
		assertEquals("Konstruktor ne postavlja godinu kako treba", 1945, instance.date.get(GregorianCalendar.YEAR));
		assertEquals("Konstruktor ne postavlja mesec kako treba", 9, instance.date.get(GregorianCalendar.MONTH));
		assertEquals("Konstruktor ne postavlja dan kako treba", 20, instance.date.get(GregorianCalendar.DAY_OF_MONTH));		
	}
	
	@Test (timeout = 2000)
	public void konstruktor_HistoricEvent_TitleNULL() {
		instance = new HistoricEvent(null, 1945, 10, 20);

		assertTrue("Za unet NULL title NE ispisuje se rec ERROR na ekranu", outContent.toString().trim().equalsIgnoreCase("ERROR"));
	}
	
	@Test (timeout = 2000)
	public void konstruktor_HistoricEvent_TitleTooShort() {
		instance = new HistoricEvent("Rat2", 1945, 10, 20);

		assertTrue("Za unet prekratak title NE ispisuje se rec ERROR na ekranu", outContent.toString().trim().equalsIgnoreCase("ERROR"));
	}

	@Test (timeout = 2000)
	public void konstruktor_HistoricEvent_YearNegative() {
		instance = new HistoricEvent("dogadjaj", -1, 10, 20);

		assertTrue("Za unetu negativnu godinu NE ispisuje se rec ERROR na ekranu", outContent.toString().trim().equalsIgnoreCase("ERROR"));
	}
	
	@Test (timeout = 2000)
	public void konstruktor_HistoricEvent_MonthNegative() {
		instance = new HistoricEvent("dogadjaj", 1945, -10, 20);

		assertTrue("Za unet negativan mesec NE ispisuje se rec ERROR na ekranu", outContent.toString().trim().equalsIgnoreCase("ERROR"));
	}
	
	@Test (timeout = 2000)
	public void konstruktor_HistoricEvent_DayNegative() {
		instance = new HistoricEvent("dogadjaj", 1945, 10, -5);

		assertTrue("Za unet negativan dan NE ispisuje se rec ERROR na ekranu", outContent.toString().trim().equalsIgnoreCase("ERROR"));
	}

	@Test (timeout = 2000)
	public void metoda_getTimePassed() {
		int trenutnaGodina = new GregorianCalendar().get(GregorianCalendar.YEAR);
		
		assertEquals("Za dogadjaj iz 1945 godine, ne vraca da je bio pre "+(trenutnaGodina - 1945)+" godina je bio", 
				trenutnaGodina - 1945, instance.getTimePassed());
	}

	@Test (timeout = 2000)
	public void metoda_reverseTitle() {
		assertEquals("Metoda ne vraca title naopako", "adargoeB ejnajdabolsO", instance.reverseTitle());
	}

}
