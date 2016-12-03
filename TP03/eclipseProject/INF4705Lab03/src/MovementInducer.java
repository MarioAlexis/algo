
import java.util.Random;

public class MovementInducer {
	static Random rand = new Random();
	static final double PERCENTAGE = 0.25;
	static public void moveSolution(Roster solution)
	{
		Corporation removedCandidate = null;
		for (Table t: solution.tablesList){
			int removeCount = (int)Math.floor(t.seatedCorps.size());
			for (int i = 0; i < removeCount; i++){
				if (t.seatedCorps.size()>0){
					removedCandidate = t.seatedCorps.get(rand.nextInt(t.seatedCorps.size()));
					t.seatedCorps.remove(removedCandidate);
					t.peopleSeated-=removedCandidate.representativeCount;
				}
			}		
		}	
		solution.updateAvailableTables();
		solution.updateScore();
		TableSeater.organize(solution);	
		//solution.updateScore();
	}
}
