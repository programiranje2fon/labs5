package p2;

public class TestHistoricEvent {

	public static void main(String[] args) {
		HistoricEvent id = 
				new HistoricEvent("Bombing of Belgrade in WW2",
						1941, 4, 6);

		System.out.println("The event took place " + id.getTimePassed() + " years ago.");
		
		System.out.println("The event title reversed: " + id.reverseTitle());

	}

}
