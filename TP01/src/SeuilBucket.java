import java.util.ArrayList;
import java.util.List;

public class SeuilBucket 
{
	static public ArrayList<Integer> bucketSeuilSort(ArrayList<Integer> sample){
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
		int bucketInterval = ((maxValue - minValue)==0) ?  maxValue : (maxValue-minValue)/sample.size()+1;
		if(bucketInterval==minValue && bucketInterval==maxValue)
			return sample;
		int bucketsCount = maxValue/bucketInterval + 1;
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
		
		for(int j=0; j<bucketsCount; j++)
		{
			if (buckets.get(j).size()>1)
			{
				ArrayList<Integer> midBucket = bucketSeuilSort(buckets.get(j));
				buckets.set(j, midBucket);
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