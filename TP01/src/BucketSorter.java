import java.util.ArrayList;
import java.util.List;

public class BucketSorter 
{
	private static final int BUCKETSEUIL = 190;
	static public List<Integer> bucketSort(boolean isThreshold, List<Integer> sample){	
		
		int minValue = sample.get(0);
		int maxValue = 0 ;
		for(int i=0; i<sample.size(); i++){
			if(sample.get(i)<minValue){
				minValue = sample.get(i);
			}
			if(sample.get(i)>maxValue){
				maxValue = sample.get(i); 
			} 
		} 
		if(maxValue==minValue)
			return sample;
		int bucketsCount = ((maxValue - minValue) <= sample.size()) ?  (sample.size()) : (maxValue-minValue)/sample.size() + 1;
		int bucketInterval = (maxValue - minValue)/bucketsCount + 1;
		List<Integer> sortedSample = new ArrayList<Integer>();
		List<List<Integer>> buckets = new ArrayList<List<Integer>>();
		for(int i=0; i < bucketsCount; i++){
			buckets.add(new ArrayList<Integer>());
		}
		for(int i=0; i<sample.size(); i++)
		{ 
			int test1 = sample.get(i);
			int test = (sample.get(i)-minValue)/bucketInterval;
			buckets.get(test).add(test1);
		}
		for(int j=0; j<bucketsCount; j++)
		{
			if (buckets.get(j).size()>1)
			{
				if(isThreshold && buckets.get(j).size() < BUCKETSEUIL)
				{
					buckets.set(j, InsertionSorter.insertionSort(buckets.get(j)));
				}
				else
				{
					buckets.set(j, bucketSort(isThreshold, buckets.get(j)));
				}
			}
			if (buckets.get(j).size()!=0)
			{
				for(int i=0; i<buckets.get(j).size(); i++){
					sortedSample.add(buckets.get(j).get(i));
				}
			}
		}
		return sortedSample; 
	} 
}
