import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabuTowerBuilder {
	public static List<Block> build(List<Block> list){
		Random randomGenerator = new Random();
		List<Block> solution = new ArrayList<Block>();
		List<Block> currentSolution = new ArrayList<Block>();
		List<Block> neighborSolution = new ArrayList<Block>();
		Integer currentSolutionHeight = 0;
		Integer neighborSolutionHeight = 0;
		Integer[] tabuDownTime = new Integer[list.size()];
		int iteration = 0;
		for (int i = 0; i < tabuDownTime.length; i++){
			tabuDownTime[i] = 0;
		}
		solution.add(list.get(0)); // On utilise le bloc avec l'aire effective la plus grande comme base (pour se donner une chance)
		currentSolution.add(list.get(0));
		neighborSolution.add(list.get(0));
		currentSolutionHeight = currentSolution.get(0).height;		
		while (iteration < 100){
			for (int i = 0; i < list.size(); i++){
				for (int j = neighborSolution.size()-1; j >= 0; j--){
					if (!neighborSolution.contains(list.get(i)) && tabuDownTime[i] == 0 && list.get(i).base[0] < neighborSolution.get(j).base[0] && list.get(i).base[1] < neighborSolution.get(j).base[1]){
						if (!(j < neighborSolution.size()-1 && list.get(i).base[0] > neighborSolution.get(j+1).base[0] && list.get(i).base[1] > neighborSolution.get(j+1).base[1])){
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
						for (int k = 0; k < neighborSolution.size(); k++){
							neighborSolutionHeight+=neighborSolution.get(k).height;
						}
						if(neighborSolutionHeight > currentSolutionHeight){
							currentSolution.clear();
							currentSolution.addAll(neighborSolution);
							currentSolutionHeight = neighborSolutionHeight;
						} 
						neighborSolution.clear();
						neighborSolution.addAll(solution);
						break;
					}
				}
			}
			neighborSolutionHeight = 0;
			for (int k = 0; k < neighborSolution.size(); k++){
				neighborSolutionHeight+=neighborSolution.get(k).height;
			}
			if (currentSolutionHeight > neighborSolutionHeight){
				solution.clear();
				solution.addAll(currentSolution);
				neighborSolution.clear();
				neighborSolution.addAll(currentSolution);
			} else {
				iteration++;
			}
			for (int i = 0; i < tabuDownTime.length; i++){
				if (tabuDownTime[i]>0){
					tabuDownTime[i]--;
				}
			}
		}
		return solution;
	}
}
