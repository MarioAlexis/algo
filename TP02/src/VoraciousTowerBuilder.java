import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VoraciousTowerBuilder {
	public static List<Block> build(List<Block> list){
		long startVorace, endVorace;		

		startVorace = System.nanoTime();		
		
		List<Block> solution = new ArrayList<Block>();
		Random randomGenerator = new Random();		
		int cumulatedArea = 0;
		int candidateChoice = 0;
		int selectionCounter = 0;
		int idx = 0;
		//Puisque nous voulons une probabilit� de s�lection plus �lev�e en fontion de la surface effective 
		//du bloc, nous allons g�n�rer un nombre al�atoire pour s�lectionner un bloc en fonction des surfaces
		//cumul�es. (Les blocs sont d�j� ordonn�es en ordre croissant de surface) Ainsi, la probabilit�
		//relative de chaque bloc correspond � l'aire de sa surface effective.		
		while (list.size() > 0){
			idx = 0;
			cumulatedArea = 0;
			selectionCounter = 0;
			for (Block b : list){
				cumulatedArea+=b.area;
			}
			candidateChoice = randomGenerator.nextInt(cumulatedArea);
			if (candidateChoice == 0){
				candidateChoice = 1;
			}
			while (selectionCounter < candidateChoice){
				selectionCounter += list.get(idx++).area;
			}
			if (solution.size()==0){
				solution.add(list.get(idx-1));
			} else {
				if (list.get(idx-1).base[0] > solution.get(0).base[0] && list.get(idx-1).base[1] > solution.get(0).base[1]){
					solution.add(0,list.get(idx-1));
				} else {
					for (int i = 1; i < solution.size(); i++){
						if (list.get(idx-1).base[0] < solution.get(i).base[0] && list.get(idx-1).base[1] < solution.get(i).base[1]){
							if (i < solution.size()-1) {
								if (list.get(idx-1).base[0] > solution.get(i+1).base[0] && list.get(idx-1).base[1] > solution.get(i+1).base[1]) {
									solution.add(i+1,list.get(idx-1));
									break;
								}
							} else {
								solution.add(list.get(idx-1));
							}
						}
					}
				}
			}
			
			list.remove(idx-1);
		}
		endVorace = System.nanoTime();
		Main.voraceTime = endVorace - startVorace;
		return solution;
	}
}
