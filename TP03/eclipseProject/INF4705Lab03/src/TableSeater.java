import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TableSeater {
	final static int DEVIATION_MODIFIER=1;
	public static void organize(Roster roster){
		int round = 0;
		Table selectedTable;
		Corporation candidate;
		double possibleScore=0;
		int totalWeight = roster.totalWeight;	
		Random rand = new Random();
		List <Table> candidateTables = new ArrayList<Table>();
		while(round!=2){
			candidate = roster.getNextUnseatedCorporation();	
			if(candidate!=null){
				for (Table t : candidate.availableTables){
					t.possibleInducedWeight = totalWeight;
					t.possibleDeviation=roster.getDeviation(candidate, t);
					for (Corporation c: t.seatedCorps){
						if (c.alliedCorps.contains(candidate)){
							t.possibleInducedWeight-=1;
						} else {
							if (c.adverseCorps.contains(candidate)){
								t.possibleInducedWeight+=1;
							}
						}
					}
				}
				selectedTable = null;
				for (Table t : candidate.availableTables){
					if (selectedTable==null){
						selectedTable=t;
						possibleScore=(t.possibleDeviation*DEVIATION_MODIFIER)+t.possibleInducedWeight;
					} else {
						if ((t.possibleDeviation*DEVIATION_MODIFIER)+t.possibleInducedWeight<possibleScore){
							selectedTable = t;
						}
					}
				}
				candidateTables.clear();
				for (Table t : candidate.availableTables){
					if ((t.possibleDeviation*DEVIATION_MODIFIER)+t.possibleInducedWeight == (selectedTable.possibleDeviation*DEVIATION_MODIFIER) + selectedTable.possibleInducedWeight){
							candidateTables.add(t);
					}
				}
				selectedTable = candidateTables.get(rand.nextInt(candidateTables.size()));
				totalWeight = selectedTable.possibleInducedWeight;
				selectedTable.seatedCorps.add(candidate);
				selectedTable.peopleSeated+=candidate.representativeCount;
				roster.score=selectedTable.possibleDeviation + totalWeight;
				roster.totalWeight = totalWeight;
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
		//System.out.println(roster.getDeviation(null, null));
		//System.out.println(roster.score);
		//System.out.println(totalWeight);
	}	
}
