import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Roster {
	double score = 0;
	int totalWeight = 0;
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
		//Cette methode retourne un candidat aleatoirement parmis les entreprises ayant le moins de tables disponibles
		Corporation currentCandidate = null;
		List<Corporation> candidates = new ArrayList<Corporation>();
		for (Corporation c: corporationsList){
			boolean isSeated = false;
			for(Table t: tablesList){		
				if(t.seatedCorps.contains(c)){
					isSeated = true;
				}
			}
			if(!isSeated){
				if (c.availableTables.size()>0){
					if (currentCandidate==null){
						currentCandidate = c;
					} else {
						if((c.availableTables.size() < currentCandidate.availableTables.size())){
							currentCandidate = c;
						}
					}
				}
			}
		}
		//Tous les candidats netant pas assis et ayant le meme nombre de tables disponibles (minimal) sont mis dans un conteuneur duquel on selectionnera le candidat final au hasard
		if(currentCandidate != null){
			for (Corporation c: corporationsList){
				boolean isSeated = false;
				for(Table t: tablesList){		
					if(t.seatedCorps.contains(c)){
						isSeated = true;
					}
				}
				if(!isSeated){
					if (c.availableTables.size() == currentCandidate.availableTables.size()){
						candidates.add(c);
					}
				}
			}
		} else {
			return null;
		}
		Random rand = new Random();
		if (candidates.size()==0){
			return null;
		} else {
			return candidates.get(rand.nextInt(candidates.size()));
		}	
	}
	public void updateAvailableTables() {
		for(Corporation c1: corporationsList){
			boolean haveEnemy = false;
			c1.availableTables.clear();
			for(Table t: tablesList){
				haveEnemy = false;
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
	public void updateAvailableTablesNoAdverse(){
		for(Corporation c1: corporationsList){
			boolean haveEnemy = false;
			c1.availableTables.clear();
			for(Table t: tablesList){
				haveEnemy = false;
				for(Corporation c2 : t.seatedCorps){
					if (c1.enemyCorps.contains(c2) || c1.adverseCorps.contains(c2)){
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
	
	public void updateScore(){
		double deviation = getDeviation(null, null);
		int weight = 0;
		Corporation c1;
		Corporation c2;
		for (Table t : tablesList){
			for (int i = 0; i<friendPairs.size(); i++){
				c1=corporationsList.get(friendPairs.get(i)[0]);
				c2=corporationsList.get(friendPairs.get(i)[1]);
				if(t.seatedCorps.contains(c1)&&t.seatedCorps.contains(c2)){
					weight--;
				}
			}
		}
		for (Table t : tablesList){
			for (int i = 0; i<notFriendPairs.size(); i++){
				c1=corporationsList.get(notFriendPairs.get(i)[0]);
				c2=corporationsList.get(notFriendPairs.get(i)[1]);
				if(t.seatedCorps.contains(c1)&&t.seatedCorps.contains(c2)){
					weight++;
				}
			}
		}
		this.totalWeight = weight;
		this.score = weight+deviation;
	}
}
