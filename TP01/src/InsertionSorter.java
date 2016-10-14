import java.util.ArrayList;

public class InsertionSorter 
{
	public static ArrayList<Integer> insertionSort(ArrayList<Integer> mylist)
	{
		int j;
		ArrayList<Integer> listintern = new ArrayList<Integer>(mylist);
		int length = listintern.size();
		long start, end;
		
		start = System.currentTimeMillis();
		for(j = 1; j < length; j++)
		{
			Integer key = listintern.get(j);
			int i = j - 1;
			
			while(i >= 0 && listintern.get(i) > key)
			{
				listintern.set(i +1, listintern.get(i));
				i--;
			}
			listintern.set(i+1, key);
		}
		
		end = System.currentTimeMillis();
		System.out.print((end - start) + ",");
		return listintern;
	}

}
