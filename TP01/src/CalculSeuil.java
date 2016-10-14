import java.util.ArrayList;

public class CalculSeuil 
{	
	public static void seuilMerge(ArrayList<Integer> mylist)
	{
		long startMerge, endMerge;
		long totalMerge;
		
		System.out.print(mylist.size() + ",");
		
		// INSERTION
		InsertionSorter.insertionSort(mylist);
		
		// MERGE 
		// Start the Merge Sort
		startMerge = System.currentTimeMillis();
		MergeSorter.mergeSort(mylist);
		endMerge = System.currentTimeMillis();
		// Get the difference
		totalMerge = endMerge - startMerge;
		
		System.out.println(totalMerge); 
	}
	
	public static void seuilBucket(ArrayList<Integer> mylist)
	{
		long startBucket, endBucket;
		long totalBucket;
		
		System.out.print(mylist.size() + ",");
		
		//INSERTION
		InsertionSorter.insertionSort(mylist);
		
		// MERGE 
		// Start the Merge Sort
		startBucket = System.currentTimeMillis();
		SeuilBucket.bucketSeuilSort(mylist);
		endBucket = System.currentTimeMillis();
		// Get the difference
		totalBucket = endBucket - startBucket;
		
		System.out.println(totalBucket); 
	}

}
