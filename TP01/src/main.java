import java.io.File;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Scanner;

public class main 
{
	
	public static void main(String[] args) 
	{
		boolean printsorted = (Integer.parseInt(args[0]) != 0);
		boolean printtime = (Integer.parseInt(args[1]) != 0);
		int choice = Integer.parseInt(args[2]);
		

		Scanner scanner;
		ArrayList<Integer> mylist = new ArrayList<Integer>();
		try 
		{
			scanner = new Scanner(new File(args[3]));
			while(scanner.hasNextInt())
			{
				mylist.add(scanner.nextInt());
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.exit(1);
		}
		
		switch (choice)
		{
			case 1 : MainSorter.merge(printsorted, printtime, false, mylist);
				break;
			case 2 : MainSorter.merge(printsorted, printtime, true, mylist);
				break;
			case 3 : MainSorter.bucket(printsorted, printtime, false, mylist);
				break;
			case 4 : MainSorter.bucket(printsorted, printtime, true, mylist);
			default : break;
		}
		System.exit(0);
	}
}
