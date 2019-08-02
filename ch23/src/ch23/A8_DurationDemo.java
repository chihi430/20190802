package ch23;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;

public class A8_DurationDemo {
	public static void main(String[] args) {
	
		
		LocalDateTime dt1 = LocalDateTime.of(2020, Month.JANUARY,12,15,30);
		LocalDateTime dt2 = LocalDateTime.of(2020, Month.FEBRUARY,12,15,30);
		
		LocalDateTime dt3 = LocalDateTime.of(2020, Month.JANUARY, 12,15,30);
		LocalDateTime dt4 = LocalDateTime.of(2020, Month.FEBRUARY,13,14,29);
		
		
		Duration drDate1 = Duration.between(dt1, dt2);
		System.out.println(drDate1);
		
		Duration drDate2 = Duration.between(dt3, dt4);
		System.out.println(drDate2);
	}
}
// LocalDate 는 날짜 정보를 표현하기 위한 클래스, 반면 LocalTime은 시각 정보를 표현하기 위한