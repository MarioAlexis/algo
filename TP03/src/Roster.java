import java.util.ArrayList;
import java.util.List;

public class Roster {
	public List<Table> tablesList;
	public List<Corporation> corporationsList;
	public Roster(){
		tablesList = new ArrayList<Table>();
		corporationsList = new ArrayList<Corporation>();
	}
	public Corporation getNextUnseatedCorporation(){
		Corporation nextCorp = null;
		for (Corporation c: corporationsList){
			boolean isSeated = false;
			for(Table t: tablesList){		
				if(t.seatedCorps.contains(c)){
					isSeated = true;
				}
			}
			if(!isSeated){
				if (c.availableTables.size()>0){
					if (nextCorp==null){
						nextCorp = c;
					} else {
						if (c.availableTables.size() < nextCorp.availableTables.size()){
							nextCorp = c;
						}
					}
				}
			}
		}
		return nextCorp;
	}
	public void updateAvailableTables() {
		for(Corporation c1: corporationsList){
			boolean haveEnemy = false;
			c1.availableTables.clear();
			for(Table t: tablesList){
				for(Corporation c2 : t.seatedCorps){
					if (c2==c1.enemyCorps){
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
}
