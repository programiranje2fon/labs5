package p1;

import java.util.GregorianCalendar;

public class TestTweet {

	public static void main(String[] args) {
		Tweet tweet = new Tweet();
		
		tweet.setUser("peter121");
		
		tweet.setTweet("This is #my first #tweet");
		
		tweet.print();

		System.out.println("Hash tags: " + tweet.countHash());

		System.out.println("Words: " + tweet.countWords());
		
		GregorianCalendar birthday = new GregorianCalendar();
		
		birthday.set(2001, 9, 27);
		
		System.out.println("Tweeted on birthday:" + tweet.checkBirthday(birthday));
	}

}
