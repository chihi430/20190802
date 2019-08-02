package ch23;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

public class A6_LocalDateTimeDemo1 {
	public static void main(String[] args) {
	
		
		// 현재 날짜와 시각
		LocalDateTime dt= LocalDateTime.now();
		System.out.println(dt);
		
		//영국 바이어와 22시간 35분 뒤 화상 미팅 예정
		LocalDateTime mt = dt.plusHours(22);
		mt = mt.plusMinutes(35);
		
		//영국 바이어와 22시간 35분 뒤 화상 미팅 예정
		System.out.println(mt);
		
		
		
	}
}
// LocalDate 는 날짜 정보를 표현하기 위한 클래스, 반면 LocalTime은 시각 정보를 표현하기 위한