import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main {
	public static void main(String [] args){
		List<Block> blockList = new ArrayList<Block>();
		List<Block> solution = new ArrayList<Block>();
		
		blockList = BlockListCreator.createBlockList("Files/b100_0");
		shuffleBlockList(blockList);
		solution = VoraciousTowerBuilder.build(blockList);
		if (testSolution(solution)){
			System.out.println("Tower solution is valid.");
		} else {
			System.out.println("Tower solution is invalid.");
		}
	}
	
	public static boolean testSolution(List<Block> solution){
		boolean bLegitSolution = true;
		int towerHeight = solution.get(0).height;
		for (int i = 0; i < solution.size()-1; i++){
			if (solution.get(i).base[0] > solution.get(i+1).base[0] && solution.get(i).base[1] > solution.get(i+1).base[1]){
				towerHeight+=solution.get(i+1).height;
			} else {
				bLegitSolution = false;
				break;
			}
		}
		if (bLegitSolution){
			System.out.println("Tower height = " + towerHeight);
		}
		return bLegitSolution;
	}
	
	public static void shuffleBlockList(List<Block> blockList){
		Collections.shuffle(blockList);
	}
}
