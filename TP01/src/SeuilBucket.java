import java.util.ArrayList;
import java.util.List;

/**
 * Bucket sort
 * @param array array to be sorted
 * @param bucketCount number of buckets
 * @return array sorted in ascending order
 */

public class SeuilBucket 
{
	static public ArrayList<Integer> bucketSeuilSort(ArrayList<Integer> sample){
		
		
		
		
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
		int bucketInterval = ((maxValue - minValue)==0) ?  maxValue : (maxValue-minValue)/sample.size()+1;
		int bucketsCount = maxValue/bucketInterval + 1;
		ArrayList<Integer> sortedSample = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i < bucketsCount; i++){
			buckets.add(new ArrayList<Integer>());
		}
		for(int i=0; i<sample.size(); i++){
			buckets.get(sample.get(i)/bucketInterval).add(sample.get(i));
		}
		for(int j=0; j<bucketsCount; j++){
			if (buckets.get(j).size()>1){
				buckets.set(j, bucketSeuilSort(buckets.get(j)));
			}
			if (buckets.get(j).size()!=0){
				for(int i=0; i<buckets.get(j).size(); i++){
					sortedSample.add(buckets.get(j).get(i));
				}
			}
		}
		return sortedSample; 
	} 
}