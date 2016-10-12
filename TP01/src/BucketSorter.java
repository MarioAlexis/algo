import java.util.ArrayList;
import java.util.List;
public class BucketSorter {
	public BucketSorter(){
		
	} 
	public static ArrayList<Integer> bucketSort(List<Integer> sample, boolean recursion){
		int minValue = sample.get(0);
		int maxValue = 0;
		ArrayList<Integer> sortedSample = new ArrayList<Integer>();
		for(int i=0; i<sample.size(); i++){
			if(sample.get(i)<minValue){
				minValue = sample.get(i);
			}
			if(sample.get(i)>maxValue){
				maxValue = sample.get(i); 
			} 
		} 
		int bucketInterval = (maxValue-minValue)/sample.size();
		int bucketsCount = maxValue/bucketInterval;
		ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i < bucketsCount; i++)
		{
			buckets.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<sample.size(); i++)
		{
			int test1 = sample.get(i);
			int test2 = test1/bucketInterval;
			buckets.get(test2).add(sample.get(i));
		}
		if (!recursion){
			for(int j=0; j<bucketsCount; j++){
				InsertionSorter.insertionSort(buckets.get(j));
			} 
		} else {
			for(int j=0; j<bucketsCount; j++)
			{
				if (buckets.get(j).size()>1)
				{
					ArrayList<Integer> midBucket = bucketSort(buckets.get(j), recursion);
					buckets.set(j, midBucket);
				}
			}
		}
		for(int j=0; j<bucketsCount; j++){
			if (buckets.get(j).size()!=0){
				for(int i=0; i<buckets.get(j).size(); i++){
					sortedSample.add(buckets.get(j).get(i));
				}
			}
		}
		return sortedSample; 
	} 
}