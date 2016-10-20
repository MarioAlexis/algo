import java.util.ArrayList;
import java.util.List;

public class InsertionSorter 
{
	public static List<Integer> insertionSort(List<Integer> mylist)
	{
		int j;
		int length = mylist.size();
		long start, end;
		
		for(j = 1; j < length; j++)
		{
			Integer key = mylist.get(j);
			int i = j - 1;
			
			while(i >= 0 && mylist.get(i) > key)
			{
				mylist.set(i +1, mylist.get(i));
				i--;
			}
			mylist.set(i+1, key);
		}
		return mylist;
	}

}
