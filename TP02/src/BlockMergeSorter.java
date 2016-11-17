import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
 
public class BlockMergeSorter
{
	public static List<Block> mergeSort(boolean isThreshold, List<Block> m) {
		if (m.size() <= 1)
			return m;

		int middle = m.size() / 2;
		List<Block> left = m.subList(0, middle);
		List<Block> right = m.subList(middle, m.size());

		right = mergeSort(isThreshold, right);
		left = mergeSort(isThreshold, left);
		List<Block> result = merge(left, right);

		return result;
	}

	private static List<Block> merge(List<Block> left, List<Block> right) {
		List<Block> result = new ArrayList<Block>();
		Iterator<Block> it1 = left.iterator();
		Iterator<Block> it2 = right.iterator();

		Block x = it1.next();
		Block y = it2.next();
		while (true) {
			// change the direction of this comparison to change the direction
			// of the sort
			if (x.area.compareTo(y.area) <= 0) {
				result.add(x);
				if (it1.hasNext()) {
					x = it1.next();
				} else {
					result.add(y);
					while (it2.hasNext()) {
						result.add(it2.next());
					}
					break;
				}
			} else {
				result.add(y);
				if (it2.hasNext()) {
					y = it2.next();
				} else {
					result.add(x);
					while (it1.hasNext()) {
						result.add(it1.next());
					}
					break;
				}
			}
		}
		return result;
	}
}
