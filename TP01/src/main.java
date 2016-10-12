import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main 
{
	public static void main(String[] args) 
	{
		//System.out.println(System.currentTimeMillis());
		List<Integer> mylist = new ArrayList<>();
		Integer[] array = {56,89,12,3,1,77,125,19,23,41};
		Collections.addAll(mylist, array);
		
		
		List<Integer> sorted = BucketSorter.bucketSort(mylist, true);
		//System.out.println(System.currentTimeMillis()
		/*for(Integer element : sorted) 
		{
            System.out.println(element);
        }*/

	}

}
