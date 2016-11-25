
public class TableSeater {
	final static int DEVIATION_MODIFIER=1;
	public static void organize(Roster roster){
		int round = 0;
		Table selectedTable;
		Corporation candidate;
		double possibleScore=0;
		int totalWeight = 0;	
		while(round!=2){
			candidate = roster.getNextUnseatedCorporation();	
			if(candidate!=null){
				for (Table t : candidate.availableTables){
					t.possibleWeight = totalWeight;
					t.possibleDeviation=roster.getDeviation(candidate, t);
					for (Corporation c: t.seatedCorps){
						if (c.alliedCorps.contains(candidate)){
							t.possibleWeight-=1;
						} else {
							if (c.adverseCorps.contains(candidate)){
								t.possibleWeight+=1;
							}
						}
					}
				}
				selectedTable = null;
				for (Table t : candidate.availableTables){
					if (selectedTable==null){
						selectedTable=t;
						possibleScore=(t.possibleDeviation*DEVIATION_MODIFIER)+t.possibleWeight;
					} else {
						if ((t.possibleDeviation*DEVIATION_MODIFIER)+t.possibleWeight<possibleScore){
							selectedTable = t;
						}
					}
				}
				totalWeight = selectedTable.possibleWeight;
				selectedTable.seatedCorps.add(candidate);
				selectedTable.peopleSeated+=candidate.representativeCount;
				roster.score=selectedTable.possibleDeviation + totalWeight;
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
		System.out.println(roster.getDeviation(null, null));
		System.out.println(roster.score);
		System.out.println(totalWeight);
	}	
}
