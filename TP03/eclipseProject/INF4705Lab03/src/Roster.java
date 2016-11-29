import java.util.ArrayList;
import java.util.List;

public class Roster {
	double score;
	int deviation;
	public List<int[]> enemyPairs;
	public List<int[]> friendPairs;
	public List<int[]> notFriendPairs;
	public List<Table> tablesList;
	public List<Corporation> corporationsList;
	public Roster(){
		tablesList = new ArrayList<Table>();
		corporationsList = new ArrayList<Corporation>();
		enemyPairs = new ArrayList<int[]>();
		friendPairs = new ArrayList<int[]>();
		notFriendPairs = new ArrayList<int[]>();
	}
	public Corporation getNextUnseatedCorporation(){
		Corporation candidate = null;
		for (Corporation c: corporationsList){
			boolean isSeated = false;
			for(Table t: tablesList){		
				if(t.seatedCorps.contains(c)){
					isSeated = true;
				}
			}
			if(!isSeated){
				if (c.availableTables.size()>0){
					if (candidate==null || (c.availableTables.size() < candidate.availableTables.size())){
						candidate = c;
					} 
				}
			}
		}
		return candidate;
	}
	public void updateAvailableTables() {
		for(Corporation c1: corporationsList){
			boolean haveEnemy = false;
			c1.availableTables.clear();
			for(Table t: tablesList){
				for(Corporation c2 : t.seatedCorps){
					if (c1.enemyCorps.contains(c2)){
						haveEnemy = true;
						break;
					}
				}
				if(!haveEnemy){
					c1.availableTables.add(t);
				}
			}
		}
	}
	public double getDeviation(Corporation c, Table selectedTable) {
		double mean = 0;
		double deviation=0;
		for(Table t: tablesList){
			if(t==selectedTable){
				mean+=(t.peopleSeated+c.representativeCount);
			} else {
				mean+=t.peopleSeated;
			}
		}
		mean = mean/tablesList.size();
		for (Table t: tablesList){
			if(t==selectedTable){
				deviation+=(t.peopleSeated+c.representativeCount-mean)*(t.peopleSeated+c.representativeCount-mean);
			} else {
				deviation+=(t.peopleSeated-mean)*(t.peopleSeated-mean);
			}
		}
		deviation = Math.sqrt(deviation/tablesList.size());
		return deviation;
	}
}
