import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
 
public class MergeSorter
{
	private static final int MERGESEUIL = 245;
	
    public static List<Integer> mergeSort(boolean isThreshold, List<Integer> m)
    {
		if(isThreshold && m.size() < MERGESEUIL)
			return InsertionSorter.insertionSort(m);
		else
			if(m.size() <= 1) return m;
 
        
        int middle = m.size() / 2;
        List<Integer> left = m.subList(0, middle);
        List<Integer> right = m.subList(middle, m.size());
 
        
        right = mergeSort(isThreshold, right);
        left = mergeSort(isThreshold, left);
        List<Integer> result = merge(left, right);
 
        return result;
    }
 
    private static List<Integer> merge(List<Integer> left, List<Integer> right)
    {
        List<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> it1 = left.iterator();
        Iterator<Integer> it2 = right.iterator();
 
	Integer x = it1.next();
	Integer y = it2.next();
        while (true){
            //change the direction of this comparison to change the direction of the sort
            if(x.compareTo(y) <= 0){
		result.add(x);
		if(it1.hasNext()){
		    x = it1.next();
		}else{
		    result.add(y);
		    while(it2.hasNext()){
			result.add(it2.next());
		    }
		    break;
		}
	    }else{
		result.add(y);
		if(it2.hasNext()){
		    y = it2.next();
		}else{
		    result.add(x);
		    while (it1.hasNext()){
			result.add(it1.next());
		    }
		    break;
		}
	    }
        }
        return result;
    }
}
