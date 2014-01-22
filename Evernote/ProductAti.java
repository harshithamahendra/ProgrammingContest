/*
Given a list of integers, your task is to write a program to output an integer-valued list of equal length such that the output element at index 'i' is the product of all input elements except for the input element at 'i'.
*/
import java.io.*;

public class ProductAti {
	public static long[] inputArr;
	public static int size;
	public static long product = 1;
	public static int flag = 0;

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(bReader.readLine());
		inputArr =new long[size];
		for(int i = 0;i < size; i++)
		{
			inputArr[i] = Integer.parseInt(bReader.readLine());
		}
		
		for(int i = 0;i < size; i++){
			if(inputArr[i] == 0){
				flag++;
			}
			else{
			product *= inputArr[i];
			}
		}
		
		for(int i = 0;i < size; i++)
		{
			if(flag == 0){
			System.out.println(product/inputArr[i]);
			}
			else if(flag == 1 && inputArr[i] == 0)
			{
				System.out.println(product);
			}
			else
			{
				System.out.println(0);
			}
		}
	}
}
