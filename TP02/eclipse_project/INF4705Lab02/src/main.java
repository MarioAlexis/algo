import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main {
	public static void main(String [] args){
		List<Block> ascendingBlockList = new ArrayList<Block>();
		List<Block> solution = new ArrayList<Block>();
		
		//Pré-traitement
		ascendingBlockList = BlockListCreator.createBlockList("C:/temp/workspace-ll/INF4705_Lab2/src/b100_0");
		ascendingBlockList = BlockMergeSorter.mergeSort(false, ascendingBlockList);
		List<Block> descendingBlockList = new ArrayList<Block>(ascendingBlockList);
		Collections.reverse(descendingBlockList);
		
		System.out.println("---------Voracious---------");
		solution = VoraciousTowerBuilder.build(ascendingBlockList);
		testSolution(solution);
		System.out.println("----------Dynamic----------");
		solution = DynamicTowerBuilder.build(descendingBlockList);
		testSolution(solution);
		System.out.println("---------Tabu Search-------");
		solution = TabuTowerBuilder.build(descendingBlockList);
		testSolution(solution);
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
			System.out.println("Tower solution is valid.");
			System.out.println("Tower height = " + towerHeight);
			System.out.println("Number of blocks used = " + solution.size());
		}
		return bLegitSolution;
	}
}
