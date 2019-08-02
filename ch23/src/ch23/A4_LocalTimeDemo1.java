package ch23;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class A4_LocalTimeDemo1 {
	public static void main(String[] args) {
	
		
		
		//오늘
		LocalTime now = LocalTime.now();
		System.out.println("Today:" +now);
		
		//2시간 10분 뒤 화상 미팅 예정
		LocalTime mt = now.plusHours(2);
		mt = mt.plusMinutes(10);
		
		//화상 미팅 시각
		System.out.println("화상 미팅 시각 : "+mt);
		
		
	}
}
// LocalDate 는 날짜 정보를 표현하기 위한 클래스, 반면 LocalTime은 시각 정보를 표현하기 위한