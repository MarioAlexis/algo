import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TableSeater {
	//Il est possible de modifier le poid de lecart type dans le score du roster pour equilibrer davantages les convives assises aux tables
	final static int DEVIATION_MODIFIER=1;
	public static void organize(Roster roster){
		int round = 0;
		Table selectedTable;
		Corporation candidate;
		double possibleScore=0;
		int totalWeight = roster.totalWeight;	
		Random rand = new Random();
		List <Table> candidateTables = new ArrayList<Table>();
		//2 rounds : la premiere exclue les tables ou se trouvent des compagnies adverse et ennemies, le second exclue seulement les ennemis
		while(round!=2){
			//On choisi un candidat aleatoirement parmis ceux qui partagent un nombre minimal de tables disponibles
			candidate = roster.getNextUnseatedCorporation();	
			//S'il ne trouve pas de candidat, on change de round et on inclue les tables ou se trouvent des compagnies adverse (poid +1)
			if(candidate!=null){
				//Pour toutes les tables disponibles, on estime lecart type et le poid induit par son association au candidat
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
				//On choisi la tabla produisant le score minimal
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
				//Si dautres tables produisent le meme score on choisi une table au hasard dans ce lot
				for (Table t : candidate.availableTables){
					if ((t.possibleDeviation*DEVIATION_MODIFIER)+t.possibleInducedWeight == (selectedTable.possibleDeviation*DEVIATION_MODIFIER) + selectedTable.possibleInducedWeight){
							candidateTables.add(t);
					}
				}
				selectedTable = candidateTables.get(rand.nextInt(candidateTables.size()));
				totalWeight = selectedTable.possibleInducedWeight;
				selectedTable.seatedCorps.add(candidate);
				selectedTable.peopleSeated+=candidate.representativeCount;
				//On met a jour le score reel dans notre objet roster
				roster.score=selectedTable.possibleDeviation + totalWeight;
				roster.totalWeight = totalWeight;
				//On met a jour le conteneur de tables disponibles pour tous les candidats
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
