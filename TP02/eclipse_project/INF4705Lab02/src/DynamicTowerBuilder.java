	import java.util.ArrayList;
import java.util.List;

public class DynamicTowerBuilder {
	public static List<Block> build(boolean printTime, List<Block> list){
		long startDyn, endDyn;
		startDyn = System.nanoTime();
		List<List<Block>> solution = new ArrayList<List<Block>>();
		int [] maximumTowerHeight = new int[list.size()];
		for (int i = 0; i < list.size(); i++){
			maximumTowerHeight[i] = list.get(i).height;
			solution.add(new ArrayList<Block>());
			solution.get(i).add(list.get(i));
		}
		for (int i = 1; i < list.size(); i++){
			for (int j = 0; j < i; j++){
				if(list.get(i).base[0] < list.get(j).base[0] && list.get(i).base[1] < list.get(j).base[1] && maximumTowerHeight[i] < maximumTowerHeight[j] + list.get(i).height){				
					maximumTowerHeight[i] = maximumTowerHeight[j] + list.get(i).height;
					solution.get(i).clear();
					for (Block b : solution.get(j)){
						solution.get(i).add(b);
					}
					solution.get(i).add(list.get(i));
				}
			}
		}
		int max = maximumTowerHeight[0];
		int idx = 0;
		for (int i = 1; i < solution.size(); i++){
			if ( max < maximumTowerHeight[i]){
				max = maximumTowerHeight[i];
				idx = i;
			}
		}
		endDyn = System.nanoTime();
		if(printTime)
		{
			System.out.println(endDyn - startDyn);
		}
		return solution.get(idx);
	}
}
