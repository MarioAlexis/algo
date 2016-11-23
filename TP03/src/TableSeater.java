import java.util.Random;

public class TableSeater {
	public static void organize(Roster roster){
		int round = 0;
		Table selectedTable;
		Corporation candidate;
		Random randomGenerator = new Random();
		while(round!=2){
			candidate = roster.getNextUnseatedCorporation();	
			if(candidate!=null){
				selectedTable = candidate.availableTables.get(randomGenerator.nextInt(candidate.availableTables.size()));
				selectedTable.seatedCorps.add(candidate);
				for(Corporation c: roster.corporationsList){
					if (c.enemyCorps.contains(candidate) || (round==0 && c.adverseCorps.contains(candidate))){
						c.availableTables.remove(selectedTable);
					} 
				}				
			} else {	
				if (round==0){
					roster.updateAvailableTables();					
				}	
				round++;
			}
		}
	}
}
