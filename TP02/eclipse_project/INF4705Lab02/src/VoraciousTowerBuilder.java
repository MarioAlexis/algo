import java.util.ArrayList;
import java.util.List;

public class VoraciousTowerBuilder {
	public static List<Block> build(List<Block> list){
		List<Block> solution = new ArrayList<Block>();
		solution.add(list.get(0));
		list.remove(0);
		for (Block b : list){
			if (b.base[0] > solution.get(0).base[0] && b.base[1] > solution.get(0).base[1]){
				solution.add(0,b);
			} else {
				for (int i = 1; i < solution.size(); i++){
					if (b.base[0] < solution.get(i).base[0] && b.base[1] < solution.get(i).base[1]){
						if (i < solution.size()-1) {
							if (b.base[0] > solution.get(i+1).base[0] && b.base[1] > solution.get(i+1).base[1]) {
								solution.add(i+1,b);
								break;
							}
						} else {
							solution.add(b);
						}
					}
				}
			}			
		}
		return solution;
	}
}
