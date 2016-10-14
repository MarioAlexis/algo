import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainSeuil 
{
	
	public static void main(String[] args) 
	{
		// args = 1 ==> calcul le seuil du merge sort
		// args = 2 ==> calcul du seuil du bucket sort		
		int nbargs = args.length;
		if (nbargs == 0)
		{
			System.exit(1);
		}
		if (nbargs > 3)
		{
			System.exit(2);
		}
		
		int choice = Integer.parseInt(args[0]);		
		if (choice != 1 && choice != 2)
		{
			System.exit(3);
		}
		
		if (nbargs == 2)
		{
			Scanner scanner;
			ArrayList<Integer> mylist = new ArrayList<Integer>();
			try 
			{
				scanner = new Scanner(new File(args[1]));
				while(scanner.hasNextInt())
				{
					mylist.add(scanner.nextInt());
				}
			} 
			catch (FileNotFoundException e) 
			{
				System.exit(4);
			}
			
			if (choice == 1)
			{
				CalculSeuil.seuilMerge(mylist);
			}
			else if (choice == 2)
			{
				CalculSeuil.seuilBucket(mylist);
			}
		}
		
		System.exit(0);
	}
}
