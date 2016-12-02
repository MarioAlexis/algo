import java.util.Random;

public class MovementInducer {
	private static Random rand = new Random();
	private static Corporation candidate;
	private static Table currentTable;
	private static Table selectedTable;
	static public void moveCorporation(Roster solution)
	{
		solution.updateAvailableTables();
		candidate = solution.corporationsList.get(rand.nextInt(solution.corporationsList.size()));
		for (Table t : solution.tablesList){
			if (t.seatedCorps.contains(candidate)){
				currentTable = t;
				break;
			}
		}
		selectedTable = candidate.availableTables.get(rand.nextInt(candidate.availableTables.size()));
		while (selectedTable==currentTable){
			selectedTable = candidate.availableTables.get(rand.nextInt(candidate.availableTables.size()));
		}
		currentTable.seatedCorps.remove(candidate);
		selectedTable.seatedCorps.add(candidate);
		solution.updateScore();
	}
}
