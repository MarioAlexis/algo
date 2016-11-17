import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static long voraceTime=0;
	public static void main(String[] args)
	{
		// ARGS PARSER
		boolean printBlockData = (Integer.parseInt(args[0]) != 0);			// Imprimme info block ou non
		boolean printtime = (Integer.parseInt(args[1]) != 0);				// Imprimme temps execution ou non
		int choice = Integer.parseInt(args[2]);								// Le choix d'algo provenant du utilisateur
		long accVoraceTime=0;												// Accumulateur pour calculer la moyenne du temps execution de algo vorace proba.
		
		List<Block> ascendingBlockList = new ArrayList<Block>();
		List<Block> solution = new ArrayList<Block>();
		
		//Pr�-traitement
		ascendingBlockList = BlockListCreator.createBlockList(args[3]);
		// Block ordonne en ordre croissant
		ascendingBlockList = BlockMergeSorter.mergeSort(false, ascendingBlockList);
		List<Block> ascendingBlockListCOPY = new ArrayList<Block>();
		List<Block> descendingBlockList = new ArrayList<Block>(ascendingBlockList);
		// Block ordonne en ordre decroissantS
		Collections.reverse(descendingBlockList);
		
		switch (choice)
		{
			// Call vorace proba.
			case 1 :
					for(int i=0; i<10; i++)
					{
						ascendingBlockListCOPY.clear();
						ascendingBlockListCOPY.addAll(ascendingBlockList);
						solution = VoraciousTowerBuilder.build(ascendingBlockListCOPY);
						accVoraceTime += voraceTime;
						testSolution(printBlockData, solution);
					}
					if(printtime)
					{
						System.out.println(accVoraceTime/10);
					}
					break;
					
			// Call Programmation Dynamique
			case 2 :solution = DynamicTowerBuilder.build(printtime, descendingBlockList);
					testSolution(printBlockData, solution);
					break;
					
			// Call Recherche par Tabou
			case 3 :solution = TabuTowerBuilder.build(printtime, descendingBlockList);
					testSolution(printBlockData, solution);;
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
	/*
	 * Methode qui permet de verifier si notre solution est valide ou non
	 */
	public static boolean testSolution(boolean printBlockData, List<Block> solution){
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
		// Si on recoit le boolean pour imprimmer les info des blocks, on les imprimme ici.
		if (printBlockData && bLegitSolution){
			System.out.println("\n\tFORMATION DE LA TOUR\n---------------------------------------------");
			for(int i=0; i<solution.size(); i++)
			{
				Block tmpblock = solution.get(i);
				System.out.println("Block no." + i + " --> h = " + tmpblock.height + " l = " + tmpblock.base[0] + " p = " + tmpblock.base[1]);
			}
			System.out.println("\t Hauteur de la tour --> " + towerHeight);
		}
		else if(!bLegitSolution)
		{
			System.out.println("Tour InValid !");
		}
		return bLegitSolution;
	}
}
