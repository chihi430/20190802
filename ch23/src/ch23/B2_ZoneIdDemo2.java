package ch23;

import java.time.ZoneId;

public class B2_ZoneIdDemo2 {
	public static void main(String[] args) {
		ZoneId.getAvailableZoneIds()
		 	  .stream()
			  .sorted()
			  .forEach(s->System.out.println(s));
	}
}
