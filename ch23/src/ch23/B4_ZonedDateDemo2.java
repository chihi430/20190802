package ch23;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class B4_ZonedDateDemo2 {
	public static void main(String[] args) {
		//한국출발 2017년 7월9일 14시30분
		ZonedDateTime date = ZonedDateTime.of(
				LocalDateTime.of(2019, 4,5,7,9),ZoneId.of("Asia/Seoul"));
		LocalDateTime a = LocalDateTime.now();
		DateTimeFormatter fm1 = 
				DateTimeFormatter.ofPattern("yy-M-d");
		DateTimeFormatter fm2 = 
				DateTimeFormatter.ofPattern("yyyy-MM-d, H:m:s");
		DateTimeFormatter fm3 = 
				DateTimeFormatter.ofPattern("yyyy-MM-dd, a HH:mm:ss W");
		System.out.println(a);
		System.out.println(date.format(fm1));
		System.out.println(date.format(fm2));
		System.out.println(date.format(fm3));

		
	}
}
 
// 한국 출발 현지 시간 2017년 7월 9일 14시30분
// 파리 도착 현지 시간 2017년 7월 9일 17시25분
// 비행에 걸린 시간은?
 