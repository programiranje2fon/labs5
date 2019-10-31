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
		instance = new HistoricEvent("Liberation of Belgrade", 1945, 10, 20);
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
	public void constructor_HistoricEvent() {
		instance = new HistoricEvent("Liberation of Belgrade", 1945, 10, 20);
		
		assertEquals("For the passed arguments: \"Liberation of Belgrade\", 1945, 10, 20, the title is not \"Liberation of Belgrade\"", "Liberation of Belgrade", instance.title);
		assertEquals("For the passed arguments: \"Liberation of Belgrade\", 1945, 10, 20, the year is not 1945", 1945, instance.date.get(GregorianCalendar.YEAR));
		assertEquals("For the passed arguments: \"Liberation of Belgrade\", 1945, 10, 20, the month is not 9", 9, instance.date.get(GregorianCalendar.MONTH));
		assertEquals("For the passed arguments: \"Liberation of Belgrade\", 1945, 10, 20, the day is not 20", 20, instance.date.get(GregorianCalendar.DAY_OF_MONTH));		
	}
	
	@Test (timeout = 2000)
	public void constructor_HistoricEvent_TitleNULL() {
		instance = new HistoricEvent(null, 1945, 10, 20);

		assertTrue("When the first argument (title) is NULL, the method should print ERROR to the console", outContent.toString().toLowerCase().contains("ERROR".toLowerCase()));
	}
	
	@Test (timeout = 2000)
	public void constructor_HistoricEvent_TitleTooShort() {
		instance = new HistoricEvent("War2", 1945, 10, 20);

		assertTrue("When the first argument (title) is shorter than 5 characters, the method should print ERROR to the console", outContent.toString().toLowerCase().contains("ERROR".toLowerCase()));
	}

	@Test (timeout = 2000)
	public void constructor_HistoricEvent_YearNegative() {
		instance = new HistoricEvent("dogadjaj", -1, 10, 20);

		assertTrue("When the second argument (year) is negative, the method should print ERROR to the console", outContent.toString().toLowerCase().contains("ERROR".toLowerCase()));
	}
	
	@Test (timeout = 2000)
	public void constructor_HistoricEvent_MonthNegative() {
		instance = new HistoricEvent("dogadjaj", 1945, -10, 20);

		assertTrue("When the third argument (month) is negative, the method should print ERROR to the console", outContent.toString().toLowerCase().contains("ERROR".toLowerCase()));
	}
	
	@Test (timeout = 2000)
	public void constructor_HistoricEvent_DayNegative() {
		instance = new HistoricEvent("dogadjaj", 1945, 10, -5);

		assertTrue("When the fourth argument (day) is negative, the method should print ERROR to the console", outContent.toString().toLowerCase().contains("ERROR".toLowerCase()));
	}

	@Test (timeout = 2000)
	public void method_getTimePassed() {
		int trenutnaGodina = new GregorianCalendar().get(GregorianCalendar.YEAR);
		
		assertEquals("For the event set in year 1945, the method does not return that the number of year passed is " + (trenutnaGodina - 1945), 
				trenutnaGodina - 1945, instance.getTimePassed());
	}

	@Test (timeout = 2000)
	public void method_reverseTitle() {
		assertEquals("The method does not revert the title properly", "edargleB fo noitarebiL", instance.reverseTitle());
	}

}
