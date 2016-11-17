import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabuTowerBuilder {
	public static List<Block> build(boolean PrintTime, List<Block> list){
		long startTabu, endTabu;
		startTabu = System.nanoTime();
		
		Random randomGenerator = new Random();						// Generation aleatoire pout le temps qu'un blok sera tabu
		List<Block> solution = new ArrayList<Block>();
		List<Block> currentSolution = new ArrayList<Block>();
		List<Block> neighborSolution = new ArrayList<Block>();
		Integer currentSolutionHeight = 0;
		Integer neighborSolutionHeight = 0;
		Integer[] tabuDownTime = new Integer[list.size()];			// Indice correspond a la position de la liste de candidat. Si != a 0, alors il est tabou
		int iteration = 0;
		for (int i = 0; i < tabuDownTime.length; i++){
			tabuDownTime[i] = 0;
		}
		solution.add(list.get(0)); 									// On utilise le bloc avec l'aire effective la plus grande comme base (pour se donner une chance)
		currentSolution.add(list.get(0));
		neighborSolution.add(list.get(0));
		currentSolutionHeight = currentSolution.get(0).height;	
		// pour 100 iterations
		while (iteration < 100){
			// pour chaque candidats
			for (int i = 0; i < list.size(); i++){
				//pour chaque element de notre solution voisine
				for (int j = neighborSolution.size()-1; j >= 0; j--){
					// On verifie si le block nest pas tabou et on verifie si il est candidat pour une meilleure solution
					if (!neighborSolution.contains(list.get(i)) && tabuDownTime[i] == 0 && list.get(i).base[0] < neighborSolution.get(j).base[0] && list.get(i).base[1] < neighborSolution.get(j).base[1]){
						if (!(j < neighborSolution.size()-1 && list.get(i).base[0] > neighborSolution.get(j+1).base[0] && list.get(i).base[1] > neighborSolution.get(j+1).base[1])){
							// si un element est un bon candidat, mais la partie du haut ne l'est plus.
							// On les suprimme et ils sont maintenant dans la lisye tabou.
							for (int k = neighborSolution.size()-1; k > j ; k--){
								for (int l = 0; l < list.size(); l++){
									if (list.get(l) == neighborSolution.get(k)){
										tabuDownTime[l] = randomGenerator.nextInt(3) + 7;
									}
								}
								neighborSolution.remove(k);
							}
						} 
						neighborSolution.add(j+1,list.get(i));
						neighborSolutionHeight = 0;
						// calcul de la hauteur de notre solution voisine
						for (int k = 0; k < neighborSolution.size(); k++){
							neighborSolutionHeight+=neighborSolution.get(k).height;
						}
						// Si notre solution voisine est plus interessante, on la considere comme solution courante.
						if(neighborSolutionHeight > currentSolutionHeight){
							currentSolution.clear();
							currentSolution.addAll(neighborSolution);
							currentSolutionHeight = neighborSolutionHeight;
						} 
						// Reset notre solution voisine
						neighborSolution.clear();
						neighborSolution.addAll(solution);
						// Pas besoin de continuer en profondeur, alors BREAK
						break;
					}
				}
			}
			neighborSolutionHeight = 0;
			for (int k = 0; k < neighborSolution.size(); k++){
				neighborSolutionHeight+=neighborSolution.get(k).height;
			}
			// On considere qu'on a une nouvelle solution si notre solution courante est plus interesssante que notre solution premiere
			if (currentSolutionHeight > neighborSolutionHeight){
				solution.clear();
				solution.addAll(currentSolution);
				neighborSolution.clear();
				neighborSolution.addAll(currentSolution);
			} else {
				// prochaine iteration de algo Tabou
				iteration++;
			}
			// diminution de une ietartion pour les elements dans la liste tabou
			// Ici, un Hashmap aurait ete la solution optimale pour avoir une instruction en temps constant
			// et eviter de iterer a travers d'une boucle.
			for (int i = 0; i < tabuDownTime.length; i++){
				if (tabuDownTime[i]>0){
					tabuDownTime[i]--;
				}
			}
		}
		endTabu = System.nanoTime();
		if(PrintTime)
		{
			System.out.println(endTabu - startTabu);
		}
		return solution;
	}
}
