
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
		if (nbargs > 4)
		{
			System.exit(2);
		}
		
		int choice = Integer.parseInt(args[0]);		
		if (choice != 1 && choice != 2)
		{
			System.exit(3);
		}
		
		int nbelement = Integer.parseInt(args[1]);
		int limitmax = Integer.parseInt(args[2]);
		int pas = Integer.parseInt(args[3]);
		// args = 1
		if(choice == 1)
		{
			System.out.println("NombreElement,Insertion,Merge");
			CalculSeuil.seuilMerge(nbelement, limitmax, pas);
		}
		// args = 2
		if (choice == 2)
		{
			System.out.println("NombreElement,Insertion,Bucket");
			CalculSeuil.seuilBucket(nbelement, limitmax, pas);
		}
		
		System.exit(0);
	}

}
