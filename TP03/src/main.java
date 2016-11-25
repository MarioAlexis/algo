
public class Main {
	public static void main(String[] args){
		Roster roster = RosterFactory.createRoster("C:/Users/h3/workspace/INF4705_Lab3/src/160_3_0.6.1");
		TableSeater.organize(roster);
		int cnt = 0;
		for (Table t: roster.tablesList){
			String s = "";
			for (Corporation c: t.seatedCorps){
				s = s + c.id + " ";
				cnt++;
			}
			System.out.println(s);
		}
		System.out.println("fin");
		System.out.println(cnt);
	}
}
