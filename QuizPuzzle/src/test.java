import java.util.Scanner;
import java.util.Random;

class Puzzle{
	StringBuilder sb = new StringBuilder("123456789");
	Scanner sc = new Scanner(System.in);
	Random rd = new Random();
	String input,temp;
	int num, row=0, line=0, count = 0;
	String[][] puzzle = new String[3][3];
	String[][] puzzle2 = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
	boolean isRun = true;
	boolean run = true;
	
	Puzzle(){
		while(isRun)
		{
			num = rd.nextInt(9)+1;
			
			if(sb.indexOf(String.valueOf(num)) >= 0)
			{
				puzzle[row][line] = String.valueOf(num);
				sb.deleteCharAt(sb.indexOf(String.valueOf(num)));
				line++;
			}
			
			if(line == 3)
			{
				row++;
				line = 0;
			}
			else if(row == 3)
				isRun = false;
		}
		
		row = rd.nextInt(3);
		line = rd.nextInt(3);
		puzzle[row][line] = "x";
		
		Print();
		
		Start();
	}
	

	void Start() {
		
		while(run)
		{
			System.out.println("\n[ MOVE ] a: Left s:Right w:Up z:Down");
			System.out.println("[ Exit ] k:Exit");
			System.out.print("이동키를 입력하세요: ");
			input = sc.next();
			
			switch(input)
			{
				case "a":
					if(line >= 1)
					{
						temp = puzzle[row][--line];
						puzzle[row][line] = "x";
						puzzle[row][line+1] = temp;
					}
					break;
				case "s":
					if(line <= 1)
					{
						temp = puzzle[row][++line];
						puzzle[row][line] = "x";
						puzzle[row][line-1] = temp;
					}
					break;
				case "w":
					if(row >= 1)
					{
						temp = puzzle[--row][line];
						puzzle[row][line] = "x";
						puzzle[row+1][line] = temp;
					}
					break;
				case "z":
					if(row <= 1)
					{
						temp = puzzle[++row][line];
						puzzle[row][line] = "x";
						puzzle[row-1][line] = temp;
					}
					break;
				case "k":
					System.out.println("종료");
					run = false;
					
			}
			
			Compare();
		}		
	}
	
	void Compare() {
		Print();
		for(int i = 0 ; i < 3; i ++)
		{
			for(int j = 0 ; j < 3 ; j++)
			{
				if(puzzle[i][j].equals(puzzle2[i][j]))
					count++;
			}
		}
		
		if(count == 8)
		{
			System.out.println("정답입니다. 게임을 종료합니다\n Good Bye~");
			run = false;
		}
		else
			count = 0;
	}
	
	void Print() {
		
		for(int i = 0; i < puzzle.length ; i++)
		{
			for(int j = 0 ; j < puzzle.length ; j++)
			{
				System.out.print(puzzle[i][j]+ " ");
			}
			System.out.println();
		}
	}
	
	
}



public class test {
	public static void main(String[] args) {
		Puzzle G = new Puzzle();

	}
}



