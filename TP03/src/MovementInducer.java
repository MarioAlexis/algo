
import java.util.Random;

public class MovementInducer {
	static Random rand = new Random();
	static final double PERCENTAGE = 0.25;
	static public void moveSolution(Roster solution)
	{
		Corporation removedCandidate = null;
		//On retire un certain pourcentage dentreprises assises a chaque tables de facon aleatoire
		for (Table t: solution.tablesList){
			int removeCount = (int)Math.floor(t.seatedCorps.size()); 
			for (int i = 0; i < removeCount; i++){
				if (t.seatedCorps.size()>0){
					removedCandidate = t.seatedCorps.get(rand.nextInt(t.seatedCorps.size()));
					t.seatedCorps.remove(removedCandidate);
					//On met a jour le nombre de representants a chaque tables
					t.peopleSeated-=removedCandidate.representativeCount;
				}
			}		
		}	
		solution.updateAvailableTables();
		solution.updateScore();
		//On appelle a nouveau lalgorithme vorace
		TableSeater.organize(solution);	
	}
}
