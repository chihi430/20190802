package MultiDimArrayDemo;

public class MultiDimArrayDemo2 {
	
	public static void main(String[] args){
		
			String[][] names = {{"Mr. ","Mrs. ","Ms. "},
								{"Smith","Jones"}};
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<2;j++)
					System.out.println(names[0][i]+ names[1][j]); 
			}
				
	}

}
