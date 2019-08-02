
import java.util.Random;
import java.util.Scanner;

public class QuizOfficial {
	
	
	static String[][] numQuiz=new String[3][3];
	
	public static void main(String[] args) {
		Random randQuiz = new Random();	// 처음 문제생성을 위해 사용
		Scanner sc= new Scanner(System.in);
		boolean exitFlag= true;
		String swap;
		String input; 					// 사용자로부터 입력받을 키
		int xAxis=3,yAxis=3;
		int xMoveIndex=0,yMoveIndex=0;
			
		//정답 표본 배열 
		final String[][] solutionResult = 
			{{"1", "2", "3"},
			 {"4", "5", "6"},
			 {"7", "8", "X"}};
				
		for(int i =0; i<3; i++) {
			for(int j=0; j<3; j++) {
				numQuiz[i][j]=solutionResult[i][j];
			}	
		}
		int cnt= randQuiz.nextInt(1000);
		cnt=2;
		int mixing;
		do {
			for(int i=0; i<cnt;i++) {
				xMoveIndex=findXpos(numQuiz);
				yMoveIndex=findYpos(numQuiz);
				mixing=randQuiz.nextInt(4);
				moveX(xMoveIndex, yMoveIndex, mixing);
			}
		}while(!check_solution(numQuiz,solutionResult));
		
		while(exitFlag) {
			for(int i=0; i<50; i++) System.out.println();
			// 제시된 숫자판 출력 
			for(int i=0; i<xAxis;i++) {
				for(int j=0; j<yAxis;j++) {
					System.out.print(numQuiz[i][j]+" ");		
				}
				System.out.println();
			}
			
			xMoveIndex=findXpos(numQuiz);
			yMoveIndex=findYpos(numQuiz);

		//	System.out.println("X: "+ xMoveIndex+", Y:"+yMoveIndex);
			System.out.println();
			
			
			System.out.println("[ Move ] a:left s:Right w:Up z:Down");
			System.out.println("[ Exit ] k:Exit");
			
			System.out.println("이동키를 입력하세요: ");
			input=sc.next();
			
			
			switch(input) {
			case "a":	//숫자가 빈곳으로 이동 왼쪽이동
				if(moveX(xMoveIndex, yMoveIndex, 0))
					System.out.println("잘못 움직였습니다.");
				break;
			case "s":	//오른쪽이동
				if(moveX(xMoveIndex, yMoveIndex, 1))
					System.out.println("잘못 움직였습니다.");
				
				break;
			case "w":	//위쪽이동
				if(moveX(xMoveIndex, yMoveIndex, 2))
					System.out.println("잘못 움직였습니다.");
				break;
			case "z":	//아래쪽이동
				if(moveX(xMoveIndex, yMoveIndex, 3))
					System.out.println("잘못 움직였습니다.");
				break;
			case "k":	//게임 종료 
				exitFlag=false;
				break;
			default:
				System.out.println("잘못입력하셨습니다. 다시 입력해 주세요 ");
				break;
			}
			//System.out.println("X: "+ xMoveIndex+", Y:"+yMoveIndex);
			System.out.println();
			if(!exitFlag) break;
		//정답과 현재 상태의 비교 로직
			exitFlag=check_solution(numQuiz,solutionResult);
			
		}
		
		System.out.println("Good bye~");
	}
	
	
	static public boolean moveX(int xPos, int yPos, int dir) {
		String swap;
		int pivot;
		boolean flag=false;
		switch(dir) {
		case 0://숫자가 빈곳으로 이동 왼쪽이동
			pivot=yPos+1;
			if(pivot >= 0 && pivot<3) {
				swap = numQuiz[xPos][yPos];
				numQuiz[xPos][yPos]=numQuiz[xPos][pivot];
				numQuiz[xPos][pivot]=swap;
			}else {
				flag=true;
			}
			break;
		case 1:
			pivot=yPos-1;
			if(pivot >= 0 && pivot<3) {
				swap = numQuiz[xPos][yPos];
				numQuiz[xPos][yPos]=numQuiz[xPos][pivot];
				numQuiz[xPos][pivot]=swap;
			}else {
				flag=true;
			}
			break;
		case 2:
			pivot = xPos+1;
			if(pivot >= 0 && pivot<3) {
				swap = numQuiz[xPos][yPos];
				numQuiz[xPos][yPos]=numQuiz[pivot][yPos];
				numQuiz[pivot][yPos]=swap;
			}else {
				flag=true;
			}
			break;
		case 3:
			pivot=xPos-1;
			if(pivot >= 0 && pivot<3) {
				swap = numQuiz[xPos][yPos];
				numQuiz[xPos][yPos]=numQuiz[pivot][yPos];
				numQuiz[pivot][yPos]=swap;
			}else {
				flag=true;
			}
			break;
		}
		return flag;
	}
	
	
	static public int findXpos(String[][] src) {
		int xPos=0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(src[i][j].equals("X"))
					xPos=i;
			}
		}
		return xPos; 
	}
	
	static public int findYpos(String[][] src) {
		int yPos=0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(src[i][j].equals("X"))
					yPos=j;
			}
		}
		return yPos; 
	}
	
	static boolean check_solution(String[][] solution, String[][] submit) {
		boolean checker=false;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(!solution[i][j].equals(submit[i][j]))
					return true; 
			}
		}
		return checker;
	}
}
