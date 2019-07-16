package ArrayDemo;

public class ArrayDemoWhile {	
		public static void main(String[] args) 
		{
			int[] anArray;             
			int i=0;
			int num=100;
			anArray = new int[10];      
			
			while(i<10)
			{
				anArray[i] = num; 
				i++;
				num=num+100;
			}
			
			i=0;
			while(i<10)
			{
				System.out.println("Element at index "+ i +": " + anArray[i]);
				i++;
			}
		}

}
