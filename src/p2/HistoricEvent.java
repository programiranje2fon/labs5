package p2;

import java.util.GregorianCalendar;

public class HistoricEvent {
	
	String title;
	GregorianCalendar date;
	
	public HistoricEvent(String t, int year, int month, int day) {
		if (t != null && t.length() >= 5 && year > 0 && month > 0 && day > 0) {
			title = t;
			date = new GregorianCalendar();
			date.set(year, month-1, day);
		}
		else System.out.println("ERROR");
	}
	
	int getTimePassed() {
		GregorianCalendar today = new GregorianCalendar();
		
		int currentYear = today.get(GregorianCalendar.YEAR);
		
		return currentYear - date.get(GregorianCalendar.YEAR);			
	}
	
	String reverseTitle() {
		String reversedTitle = "";
		
		for (int i = title.length()-1; i >= 0; i--)
			reversedTitle = reversedTitle + title.charAt(i);
		
		return reversedTitle;
	}

}
