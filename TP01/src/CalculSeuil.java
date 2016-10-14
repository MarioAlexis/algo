import java.util.ArrayList;
import java.util.Random;

public class CalculSeuil 
{	
	public static void seuilMerge(ArrayList<Integer> mylist)
	{
		long startMerge, endMerge;
		long totalMerge;
		
		System.out.print(mylist.size() + ",");
		
		// INSERTION
		ArrayList<Integer> sorted = InsertionSorter.insertionSort(mylist);
		
		// MERGE 
		// Start the Merge Sort
		startMerge = System.currentTimeMillis();
		MergeSorter.mergeSort(mylist);
		endMerge = System.currentTimeMillis();
		// Get the difference
		totalMerge = endMerge - startMerge;
		
		System.out.println(totalMerge + "," + sorted.get(0) + "," + sorted.get(sorted.size()-1)); 
	}
	
	public static void seuilBucket(ArrayList<Integer> mylist)
	{
		
		//Random rand = new Random();
  		long startBucket, endBucket;
  		long totalBucket;

  		//ArrayList<Integer> mylist2 = new ArrayList<Integer>();
  		//for(int i = 0; i < 1000; i++)
  		//{
  		//	mylist2.add(rand.nextInt(((4000 - 1) + 1) + 1));
  		//}
		
		
		System.out.print(mylist.size() + ",");
		
		//INSERTION
		ArrayList<Integer> sorted = InsertionSorter.insertionSort(mylist);
		
		// MERGE 
		// Start the Merge Sort
		startBucket = System.currentTimeMillis();
		ArrayList<Integer> sorted2 =  SeuilBucket.bucketSeuilSort(mylist);
		endBucket = System.currentTimeMillis();
		// Get the difference
		totalBucket = endBucket - startBucket;
		
		System.out.print(totalBucket + "," + sorted.get(0) + "," + sorted.get(sorted.size()-1));
		if(sorted.equals(sorted2)) System.out.println(",SORTED !");
		
	}

}
