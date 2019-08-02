import java.util.Random;
import java.util.Scanner;

public class QuizPuzzle2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		
		int num1;
		StringBuilder stb = new StringBuilder("123456789");
		String[][] puzzle = new String[3][3];
		String[][] puzzle2 = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
		int num = 1;

		while (true) {
			num1 = ran.nextInt(9) + 1;
			
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
															
					puzzle[i][j] = String.valueOf(num1);
					
				}
			}

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(" "+puzzle[i][j] + " ");// (0,1)~~~~(3,4)까지
				}
				System.out.println();
			}
			
			
			System.out.println("[ Move ] a:Left s:Right w:Up z:Down");
			System.out.println("[ Exit ] k:Exit");
			System.out.println("이동키를 입력하세요 : ");
			String re =sc.next();
			if (re.equalsIgnoreCase("k")) {					
				System.out.println("goodbye");
				break;
			} else if(re.equalsIgnoreCase("a")) {
				
			} else if(re.equalsIgnoreCase("s")) {
				
			} else if(re.equalsIgnoreCase("w")) {
				
			} else if(re.equalsIgnoreCase("z")) {
				
			}
			
			break;
		}
	}
}
