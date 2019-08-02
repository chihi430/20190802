import java.util.Random;
import java.util.Scanner;

class game1 {
	Scanner sc = new Scanner(System.in);
	Random ran = new Random();

	StringBuilder stb = new StringBuilder("123456789");
	String[][] puzzle = new String[3][3];
	String[][] puzzle2 = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" } };
	String re, te;

	int num1;
	int row = 0, col = 0;
	int num = 1, chance = 0;

	boolean isRun = true;
	boolean run = true;

	game1() {
		while (isRun) {
			num1 = ran.nextInt(9) + 1;

			if (stb.indexOf(String.valueOf(num1)) >= 0) {
				puzzle[row][col] = String.valueOf(num1);
				stb.deleteCharAt(stb.indexOf(String.valueOf(num1)));
				col++;
			}

			if (col == 3) {
				row++;
				col = 0;
			} else if (row == 3) {
				isRun = false;
			}
			

		}

		row = ran.nextInt(3);
		col = ran.nextInt(3);
		puzzle[row][col] = "x";
		Print();
		Start();
		//System.out.println(puzzle[row][col]);	
		


	}

	void Start() {

		while (run) {
			System.out.println("[ Move ] a:Left s:Right w:Up z:Down");
			System.out.println("[ Exit ] k:Exit");
			System.out.println("이동키를 입력하세요 : ");
			re = sc.next();

			switch (re) {
			case "a":
				if (col >= 1) {
					te = puzzle[row][--col];
					puzzle[row][col] = "x";
					puzzle[row][col + 1] = te;
				}
				break;
			case "s":
				if (col <= 1) {
					te = puzzle[row][++col];
					puzzle[row][col] = "x";
					puzzle[row][col - 1] = te;
				}
				break;
			case "w":
				if (row >= 1) {
					te = puzzle[--row][col];
					puzzle[row][col] = "x";
					puzzle[row + 1][col] = te;
				}
				break;
			case "z":
				if (row <= 1) {
					te = puzzle[++row][col];
					puzzle[row][col] = "x";
					puzzle[row - 1][col] = te;
				}
				break;
			case "k":
				System.out.println("종료");
				run = false;
				break;
			default:
				System.out.println("잘못입력하셨습니다.");
			}
			
			Compare();
		}
	}

	void Compare() {
		Print();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (puzzle[i][j].equals(puzzle2[i][j]))
					chance++;
			}

		}
		if (chance == 8) {
			System.out.println("정답입니다.");
			run = false;
		} else
			chance = 0;
	}

	void Print() {

		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length; j++) {
				System.out.print(puzzle[i][j] + " ");
			}
			System.out.println();
		}
	}

}

public class QuizPuzzle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		game1 g = new game1();

	}

}
