
public class Main {
	public static void main(String[] args){
		Roster roster = RosterFactory.createRoster("C:/Users/h3/workspace/INF4705_Lab3/src/160_3_0.6.1");
		TableSeater.organize(roster);
		for (Table t: roster.tablesList){
			String s = "";
			for (Corporation c: t.seatedCorps){
				s = s + c.id + " ";
			}
			System.out.println(s);
		}
		System.out.println("fin");
		verifySolution(roster);
	}
	
	public static void verifySolution(Roster roster){
		int corpTotal = 0;
		int score = 0;
		Corporation c1;
		Corporation c2;
		for (Table t : roster.tablesList){
			corpTotal+=t.seatedCorps.size();
		}
		if(corpTotal!=roster.corporationsList.size()){
			System.out.println("Solution invalid! Not all corporations are seated!");
		}
		for (Table t : roster.tablesList){
			for (int i = 0; i<roster.enemyPairs.size(); i++){
				c1=roster.corporationsList.get(roster.enemyPairs.get(i)[0]);
				c2=roster.corporationsList.get(roster.enemyPairs.get(i)[1]);
				if(t.seatedCorps.contains(c1)&&t.seatedCorps.contains(c2)){
					System.out.println("Solution invalid! Enemies are seated at the same table!");
				}
			}
		}
		for (Table t : roster.tablesList){
			for (int i = 0; i<roster.friendPairs.size(); i++){
				c1=roster.corporationsList.get(roster.friendPairs.get(i)[0]);
				c2=roster.corporationsList.get(roster.friendPairs.get(i)[1]);
				if(t.seatedCorps.contains(c1)&&t.seatedCorps.contains(c2)){
					score--;
				}
			}
		}
		for (Table t : roster.tablesList){
			for (int i = 0; i<roster.notFriendPairs.size(); i++){
				c1=roster.corporationsList.get(roster.notFriendPairs.get(i)[0]);
				c2=roster.corporationsList.get(roster.notFriendPairs.get(i)[1]);
				if(t.seatedCorps.contains(c1)&&t.seatedCorps.contains(c2)){
					score++;
				}
			}
		}
		System.out.print(score);
	}
}
