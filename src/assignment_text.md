# Lab exercise 5

## Problem 1
(to be done by the tutor in cooperation with students)

Create the **Tweet** class in the **p1** package. The class should include:

1. The attribute **user** that represents the username of the person who has sent the tweet. The initial value is "unknown". 

2. The attribute **tweet** that represents the tweet that the user has sent. The initial value is "unknown".

3. The attribute **date** that represents the date and time when the tweet was published. The initial value is the current date and time. 

4. The method **setUser** that takes a username as its parameter and sets the user attribute to that value only if the value is not null and is not "unknown". Otherwise, the method prints "ERROR" on the screen. 

5. The method **setTweet** that takes the text of a tweet as its input parameter and sets the **tweet** attribute to that value if the value neither null nor an empty String, and is up to 140 characters long. Otherwise, the method prints "ERROR" on the screen. 

6. The method **countHash** that counts and returns the number of '#' characters in the tweet's text. 

7. The method **countWords** that counts and returns the number of words in the tweet's text. Assume that each two words are separated by a ' ' character. 

8. The method **print** that prints on the screen the user, the tweet, and the date and time when the tweet was sent, along with an appropriate message. 

9. The method **checkBirthday** that takes a user's birthday (GregorianCalendar) as its parameter. The method first checks if the date passed as the user's birthday is not null and if it pertains to a moment in the past. If so, the method checks if the user has posted this tweet on his birthday. If so, the method returns true; in all other cases it returns false. 

Create the **TestTweets** class in the **p1** package. The class should create a Tweet object in the main method and set the user to "peter121", and the tweet to "This is #my first #tweet". Print all the data about the tweet message, as well as the number of words and hash tags in it. Then create a GregorianCalendar object with the date of 27.10.2001. and check if the user has sent the tweet on his birthday. 


## Problem 2
(students work on it themselves)

Create the **HistoricEvent** class in the **p2** package. The class should include:

1. The String attribute **title**.

2. The GregorianCalendar attribute **date**.

3. A constructor that takes four parameters: a String and three integers. The first parameter is the title of the event, whereas the following three represent the year, the month, and the day when the event took place. This constructor sets the values of the corresponding attributes only if the String passed is not null and is at least 5 characters long, as well as if all the numbers entered are greater than zero. Otherwise, the constructor prints the word "ERROR" on the screen.

4. The method **getTimePassed** that returns the number of years that had passed since the event took place (relative to the current year).

5. The method **reverseTitle** that returns the title of the event, but in the reversed order of characters.

Create the **TestHistoricEvent** class in the **p2** package. In its main method, the class should create a HistoricEvent object: "Bombing of Belgrade in WW2", on Apr 6, 1941. Print on the screen the number of years passed since the event, as well as the event title reversed.
