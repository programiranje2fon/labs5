package p2;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import p2.HistoricEvent;

public class HistoricEventTest {
	
	HistoricEvent instance;

	@Before
	public void setUp() throws Exception {
		instance = new HistoricEvent("Oslobadjanje Beograda", 1945, 10, 20);
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}

	@Test (timeout = 2000)
	public void constructor_HistoricEvent() {
		instance = new HistoricEvent("Oslobadjanje Beograda", 1945, 10, 20);
		
		assertEquals("Konstruktor ne postavlja title kako treba", "Oslobadjanje Beograda", instance.title);
		assertEquals("Konstruktor ne postavlja godinu kako treba", 1945, instance.date.get(GregorianCalendar.YEAR));
		assertEquals("Konstruktor ne postavlja mesec kako treba", 9, instance.date.get(GregorianCalendar.MONTH));
		assertEquals("Konstruktor ne postavlja dan kako treba", 20, instance.date.get(GregorianCalendar.DAY_OF_MONTH));		
	}
	
	@Test (timeout = 2000)
	public void constructor_HistoricEvent_TitleNULL() {
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance = new HistoricEvent(null, 1945, 10, 20);

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za unet NULL title NE ispisuje se rec ERROR na ekranu", ispis.trim().equalsIgnoreCase("ERROR"));
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}
	
	@Test (timeout = 2000)
	public void constructor_HistoricEvent_TitleTooShort() {
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance = new HistoricEvent("Rat2", 1945, 10, 20);

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za unet prekratak title NE ispisuje se rec ERROR na ekranu", ispis.trim().equalsIgnoreCase("ERROR"));
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test (timeout = 2000)
	public void constructor_HistoricEvent_YearNegative() {
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance = new HistoricEvent("dogadjaj", -1, 10, 20);

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za unetu negativnu godinu NE ispisuje se rec ERROR na ekranu", ispis.trim().equalsIgnoreCase("ERROR"));
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}
	
	@Test (timeout = 2000)
	public void constructor_HistoricEvent_MonthNegative() {
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance = new HistoricEvent("dogadjaj", 1945, -10, 20);

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za unet negativan mesec NE ispisuje se rec ERROR na ekranu", ispis.trim().equalsIgnoreCase("ERROR"));
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}
	
	@Test (timeout = 2000)
	public void constructor_HistoricEvent_DayNegative() {
		PrintStream pom = System.out;
		try {
			// Otvoren outputstream za redirekciju System.out
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			System.out.flush();
			// Redirekcija
			System.setOut(new PrintStream(buffer));

			instance = new HistoricEvent("dogadjaj", 1945, 10, -5);

			System.out.flush();

			// Preuzimanje ispisa metode na ekranu
			String ispis = buffer.toString();

			// Vracanje System.out na staro
			System.setOut(pom);

			assertTrue("Za unet negativan dan NE ispisuje se rec ERROR na ekranu", ispis.trim().equalsIgnoreCase("ERROR"));
		} catch (Exception e) {
			System.setOut(pom);
			e.printStackTrace();
		}
	}

	@Test (timeout = 2000)
	public void method_getTimePassed() {
		int trenutnaGodina = new GregorianCalendar().get(GregorianCalendar.YEAR);
		
		assertEquals("Za dogadjaj iz 1945 godine, ne vraca da je bio pre "+(trenutnaGodina - 1945)+" godina je bio", 
				trenutnaGodina - 1945, instance.getTimePassed());
	}

	@Test (timeout = 2000)
	public void method_reverseTitle() {
		assertEquals("Metoda ne vraca title naopako", "adargoeB ejnajdabolsO", instance.reverseTitle());
	}

}
