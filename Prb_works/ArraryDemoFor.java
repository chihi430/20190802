package ArrayDemo;

public class ArraryDemoFor {
	public static void main(String[] args) 
	{
		int[] anArray;            
		int num=100;
		anArray = new int[10];     
		
		for(int i=0;i<10;i++)
		{
			anArray[i] = num; 
			
			num=num+100;
		}
		
		for(int i=0;i<10;i++)
		{
			System.out.println("Element at index "+ i +": " + anArray[i]);
		}
	}
}
