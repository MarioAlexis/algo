import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovementInducer {
	static Random rand = new Random();
	static List<Corporation> candidates = new ArrayList<Corporation>();
	static Table currentTable;
    static Table selectedTable;
    static Corporation c1;
    static Corporation c2;
    static Table t1 = null;
    static Table t2 = null;
    static boolean sameTable = true;
    static boolean notEnoughTables = false;
	static public void moveCorporation(Roster solution)
	{
		solution.updateAvailableTablesNoAdverse();
		for (Corporation c: solution.corporationsList){
			if (c.availableTables.size() > 1){
				candidates.add(c);
			}
		}
		if (candidates.size()<=1){
			solution.updateAvailableTables();
		}
		for (Corporation c: solution.corporationsList){
			if (c.availableTables.size() > 1){
				candidates.add(c);
			}
		}
		c1 = candidates.get(rand.nextInt(candidates.size()));
		for (Table t: solution.tablesList){
			if(t.seatedCorps.contains(c1)){
				t1 = t;
				break;
			}
		}
		t1.seatedCorps.remove(c1);
		t1.peopleSeated-=c1.representativeCount;
		t2 = c1.availableTables.get(rand.nextInt(c1.availableTables.size()));
		while (t1==t2){
			t2 = c1.availableTables.get(rand.nextInt(c1.availableTables.size()));
			System.out.println("available tables size="+c1.availableTables.size());
		}
		t2.seatedCorps.add(c1);
		t2.peopleSeated+=c1.representativeCount;

		/*sameTable = true;
		notEnoughTables = true;
		solution.updateAvailableTablesNoAdverse();
		for (Corporation c: solution.corporationsList){
			if (c.availableTables.size() > 1){
				candidates.add(c);
			}
		}
		c1 = candidates.get(rand.nextInt(candidates.size()));
		while (sameTable){
			c2 = candidates.get(rand.nextInt(candidates.size()));
			for (Table t: solution.tablesList){
				if (!(t.seatedCorps.contains(c1) && t.seatedCorps.contains(c2))){
					sameTable = false;
				}
			}
		}
		for (Table t: solution.tablesList){
			if(t.seatedCorps.contains(c1)){
				t1 = t;
			}
			if(t.seatedCorps.contains(c2)){
				t2 = t;
			}
		}
		t1.seatedCorps.remove(c1);
		t1.peopleSeated-=c1.representativeCount;
		t2.seatedCorps.remove(c2);
		t2.peopleSeated-=c2.representativeCount;
		solution.updateAvailableTablesNoAdverse();
		if (c1.availableTables.contains(t2) && c2.availableTables.contains(t1)){
			t2.seatedCorps.add(c1);
			t2.peopleSeated+=c1.representativeCount;
			t1.seatedCorps.add(c2);
			t1.peopleSeated+=c2.representativeCount;
		} else {
			t1 = c1.availableTables.get(rand.nextInt(c1.availableTables.size()));
			t1.seatedCorps.add(c1);
			t1.peopleSeated+=c1.representativeCount;
			if (c1.enemyCorps.contains(c2)){
				for(Table t: c2.availableTables){
					if(t.seatedCorps.contains(c1)){
						c2.availableTables.remove(t);
						break;
					}
				}
			}
			t2 = c2.availableTables.get(rand.nextInt(c2.availableTables.size()));
			t2.seatedCorps.add(c2);
			t2.peopleSeated+=c2.representativeCount;
		}*/
		solution.updateScore();
	}
}
