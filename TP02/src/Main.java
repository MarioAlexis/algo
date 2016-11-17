import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static long voraceTime=0;
	public static void main(String[] args)
	{
		System.out.println("AMIN TEST");
		// ARGS PARSER
		boolean printBlockData = (Integer.parseInt(args[0]) != 0);
		boolean printtime = (Integer.parseInt(args[1]) != 0);
		int choice = Integer.parseInt(args[2]);		
		
		List<Block> ascendingBlockList = new ArrayList<Block>();
		List<Block> solution = new ArrayList<Block>();
		
		//Pr�-traitement
		ascendingBlockList = BlockListCreator.createBlockList(args[3]);
		ascendingBlockList = BlockMergeSorter.mergeSort(false, ascendingBlockList);
		List<Block> descendingBlockList = new ArrayList<Block>(ascendingBlockList);
		Collections.reverse(descendingBlockList);
		
		switch (choice)
		{
			case 1 :solution = VoraciousTowerBuilder.build(ascendingBlockList);
					System.out.println("test vorace : " + voraceTime);
					testSolution(solution);
					break;
			case 2 :solution = DynamicTowerBuilder.build(printtime, descendingBlockList);
					testSolution(solution);
					break;
			case 3 :solution = TabuTowerBuilder.build(printtime, descendingBlockList);
					testSolution(solution);;
					break;
			default : break;
		}
		System.exit(0);
		
		/*System.out.println("---------Voracious---------");
		solution = VoraciousTowerBuilder.build(ascendingBlockList);
		testSolution(solution);
		System.out.println("----------Dynamic----------");
		solution = DynamicTowerBuilder.build(descendingBlockList);
		testSolution(solution);
		System.out.println("---------Tabu Search-------");
		solution = TabuTowerBuilder.build(descendingBlockList);
		testSolution(solution);*/
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
