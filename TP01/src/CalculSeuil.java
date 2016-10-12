import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class CalculSeuil 
{	
	public static void seuilMerge(int nbElements, int limitMax, int pas)
	{
		if (nbElements >= limitMax) return;
		Random rand = new Random();
		long startMerge, endMerge;
		long totalMerge;

		int minElement = nbElements;
		int maxElement = minElement * 2;
		
		System.out.print(nbElements + ",");
		ArrayList<Integer> mylist = new ArrayList<Integer>();
		for(int i = 0; i < minElement; i++)
		{
			mylist.add(rand.nextInt(((maxElement - minElement) + 1) + minElement));
		}
		
		// INSERTION
		List<Integer> sorted = InsertionSorter.insertionSort(mylist);
		
		// MERGE 
		// Start the Merge Sort
		startMerge = System.currentTimeMillis();
		List<Integer> sorted2 = MergeSorter.mergeSort(mylist);
		endMerge = System.currentTimeMillis();
		// Get the difference
		totalMerge = endMerge - startMerge;
		
		System.out.println(totalMerge);
		seuilMerge(nbElements + pas, limitMax, pas); 
	}
	
	public static void seuilBucket(int nbElements, int limitMax, int pas)
	{
		//if (nbElements >= limitMax) return;
		Random rand = new Random();
		long startBucket, endBucket;
		long totalBucket;

		int minElement = nbElements;
		int maxElement = minElement * 2;
		
		System.out.print(nbElements + ",");
		
		/*Scanner scanner;
		ArrayList<Integer> mylist = new ArrayList<Integer>();
		try {
			scanner = new Scanner(new File("TextFiles/testset_1000_1.txt"));
			while(scanner.hasNextInt()){
				mylist.add(scanner.nextInt());
			}
			System.out.println("Scan successful");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Scan failed: FileNotFound");
		}*/
		ArrayList<Integer> mylist = new ArrayList<Integer>();
		for(int i = 0; i < minElement; i++)
		{
			mylist.add(rand.nextInt(((10000 - 1) + 1) + 1));
		}
		
		//INSERTION
		ArrayList<Integer> sorted = InsertionSorter.insertionSort(mylist);
		
		// MERGE 
		// Start the Merge Sort
		startBucket = System.currentTimeMillis();
		List<Integer> sorted2 = SeuilBucket.bucketSeuilSort(mylist);
		endBucket = System.currentTimeMillis();
		// Get the difference
		totalBucket = endBucket - startBucket;
		
		if(sorted.equals(sorted2))
			System.out.println("SUCCESSS's          ");
		
		System.out.println(totalBucket);
		//seuilBucket(nbElements+pas, limitMax, pas); 
	}

}
