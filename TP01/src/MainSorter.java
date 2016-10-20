import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainSorter 
{	
	public static void merge(boolean printsorted, boolean printtime, boolean isThreshold, List<Integer> mylist)
	{
		long startMerge, endMerge;
		long totalMerge;
		
		// MERGE 
		// Start the Merge Sort
		startMerge = System.nanoTime();
		List<Integer> sorted = MergeSorter.mergeSort(isThreshold, mylist);
		endMerge = System.nanoTime();
		// Get the difference
		totalMerge = endMerge - startMerge;
		
		if(printsorted)
		{
			for(Integer element : sorted)
				System.out.println(element);
		}
		if(printtime)
		{
			System.out.println(totalMerge);
		}
	}
	
	public static void bucket(boolean printsorted, boolean printtime, boolean isThreshold, List<Integer> mylist)
	{
		
		//Random rand = new Random();
  		long startBucket, endBucket;
  		long totalBucket;
		
		// Bucket sort
		startBucket = System.nanoTime();
		List<Integer> sorted =  BucketSorter.bucketSort(isThreshold, mylist);
		endBucket = System.nanoTime();
		// Get the difference
		totalBucket = endBucket - startBucket;
		
		if(printsorted)
		{
			for(Integer element : sorted)
				System.out.println(element);
		}
		if(printtime)
		{
			System.out.println(totalBucket);
		}
		
	}

}
