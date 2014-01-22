/* Implement a circular buffer of size N. Allow the caller to append, remove and list the contents of the buffer. Implement the buffer to achieve maximum performance for each of the operations.
*/
import java.util.*;
import java.io.*; 

class Buffer{
	public int rear;
	public int front;
	public int size;
	public String[] buffer;
	public int count = 0;

	public Buffer(int length)
	{
		size = length;
		buffer = new String[length];
		rear = 0;
		front = -1;
	}

	public void append(String value)
	{
		front = (front + 1) % size;
		buffer[front] = value;
		count++;
		if(count > size){
			rear = (front + 1) % size;
			count = size;
		}

	}

	public void remove(int count)
	{
		this.count -= count;
		if(this.count == 0) {
			rear = 0;
			front = -1;
		} else
			rear = (rear + count) % size;

	}

	public void display()
	{
		int i = rear;
		for (int index = 0; index < count; index++, i = (i + 1) % size) 
			System.out.println(buffer[i]);
	}
}
public class Solution {

	public static int length;
	public static char command;
	public static String string; 

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		length = Integer.parseInt(br.readLine());
		Buffer B = new Buffer(length);
		while(true)
		{
			StringTokenizer input = new StringTokenizer(br.readLine());
			command = input.nextToken().charAt(0);

			switch(command){
			case 'A': {
				int appendSize = Integer.parseInt(input.nextToken());
				for(int i = 0;i < appendSize ;i++){
					string = br.readLine();
					B.append(string);
				}
				break;
			}

			case 'R': {
				B.remove(Integer.parseInt(input.nextToken()));
				break;
			}

			case 'L': {
				B.display();
				break;
			}

			case 'Q': {
				return;
			}

			}

		}
	}
}
