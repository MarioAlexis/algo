import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RosterFactory {
	public static Roster createRoster(String filename){
		boolean error = false;
		int tableCount;
		int corporationCount;
		int pairsCount;
		Roster roster = new Roster();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
		    String line = br.readLine();
		    tableCount =  Integer.parseInt(line);
		    for (int i=0; i<tableCount; i++){
		    	roster.tablesList.add(new Table());
		    }
		    line = br.readLine();
		    corporationCount =  Integer.parseInt(line);
		    for (int i=0; i<corporationCount; i++){
		    	line = br.readLine();
		    	roster.corporationsList.add(new Corporation(Integer.parseInt((line.trim())), Integer.parseInt(String.valueOf(i))));
		    }
			if (corporationCount!=roster.corporationsList.size()){
				error = true;
				System.out.println("ERROR: corporationCount = " + corporationCount + "  ; corporationList.size() = " + roster.corporationsList.size());
			}
			line = br.readLine();
			pairsCount =  Integer.parseInt(line);
		    for (int i=0; i<pairsCount; i++){
		    	line = br.readLine();
		    	String [] sData = line.split("\\s+");
		    	roster.corporationsList.get(Integer.parseInt(sData[0])).enemyCorps.add(roster.corporationsList.get(Integer.parseInt(sData[1])));
		    	roster.corporationsList.get(Integer.parseInt(sData[1])).enemyCorps.add(roster.corporationsList.get(Integer.parseInt(sData[0])));
		    	roster.enemyPairs.add(new int[]{Integer.parseInt(sData[0]),Integer.parseInt(sData[1])});
		    }
		    line = br.readLine();
			pairsCount =  Integer.parseInt(line);
		    for (int i=0; i<pairsCount; i++){
		    	line = br.readLine();
		    	String [] sData = line.split("\\s+");
		    	roster.corporationsList.get(Integer.parseInt(sData[0])).alliedCorps.add(roster.corporationsList.get(Integer.parseInt(sData[1])));
		    	roster.corporationsList.get(Integer.parseInt(sData[1])).alliedCorps.add(roster.corporationsList.get(Integer.parseInt(sData[0])));
		    	roster.friendPairs.add(new int[]{Integer.parseInt(sData[0]),Integer.parseInt(sData[1])});
		    }
		    line = br.readLine();
			pairsCount =  Integer.parseInt(line);
		    for (int i=0; i<pairsCount; i++){
		    	line = br.readLine();
		    	String [] sData = line.split("\\s+");
		    	roster.corporationsList.get(Integer.parseInt(sData[0])).adverseCorps.add(roster.corporationsList.get(Integer.parseInt(sData[1])));
		    	roster.corporationsList.get(Integer.parseInt(sData[1])).adverseCorps.add(roster.corporationsList.get(Integer.parseInt(sData[0])));
		    	roster.notFriendPairs.add(new int[]{Integer.parseInt(sData[0]),Integer.parseInt(sData[1])});
		    }
		    for (Corporation c: roster.corporationsList){
		    	for (Table t: roster.tablesList){
		    		c.availableTables.add(t);
		    	}
		    }
		} catch (IOException e) {
			error = true;
			System.exit(1);
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				error = true;
				System.exit(1);
			}
		}
		if (!error){
			return roster;
		} else {
			return null;
		}
	}
}
