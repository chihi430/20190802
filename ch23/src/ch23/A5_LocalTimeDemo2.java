package ch23;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class A5_LocalTimeDemo2 {
	public static void main(String[] args) {
	
		
		
		LocalTime start = LocalTime.of(14,24,35);
		System.out.println("PC 이용 시작 시각 : "+start);
		
		LocalTime end = LocalTime.of(17, 31,19);
		System.out.println("PC이용 종료 시작 : "+end);
		
		Duration between = Duration.between(start, end);
		System.out.println("총 PC 이용 시간 : "+between);
		System.out.println("총 PC 이용 시간 : "+between.toHours() + ":"+between.toMinutes()+":"+between.toMillis());
		
		
	}
}
// LocalDate 는 날짜 정보를 표현하기 위한 클래스, 반면 LocalTime은 시각 정보를 표현하기 위한