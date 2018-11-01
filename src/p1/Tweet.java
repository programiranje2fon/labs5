package p1;

import java.util.GregorianCalendar;

public class Tweet {
	
	String user = "unknown";
	String tweet = "unknown";
	GregorianCalendar date = new GregorianCalendar();
	
	void setUser (String u) {
		if (u != null && !u.equals("unknown"))
			user = u;
		else
			System.out.println("ERROR");
	}
	
	void setTweet (String t) {
		if (t != null && !t.equals("") && t.length() <= 140)
			tweet = t;
		else
			System.out.println("ERROR");
	}
	
	int countHash() {
		int counter = 0;
		
		for(int i=0;i<tweet.length();i++)
			if (tweet.charAt(i) == '#')
				counter++;
		
		return counter;
	}
	
	int countWords() {
		String[] words = tweet.split(" ");
		return words.length;
	}
	
	void print() {
		System.out.println("User: " + user);
		System.out.println("Date: " + date.getTime());
		System.out.println("Tweet: " + tweet);
	}
	
	boolean checkBirthday(GregorianCalendar birthday) {
		if (birthday == null || birthday.after(new GregorianCalendar()))
			return false;
		
		int month = birthday.get(GregorianCalendar.MONTH);
		int day = birthday.get(GregorianCalendar.DAY_OF_MONTH);
		
		int tweetMonth = date.get(GregorianCalendar.MONTH);
		int tweetDay = date.get(GregorianCalendar.DAY_OF_MONTH);
		
		if (month == tweetMonth && day == tweetDay)
			return true;
		else
			return false;
	}

}
