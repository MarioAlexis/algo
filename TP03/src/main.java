
public class main {
	public static void main(String[] args){
		Roster roster = RosterFactory.createRoster("C:/Users/h3/workspace/INF4705_Lab3/src/20_1_0.3.1");
		TableSeater.organize(roster);
		for (Table t: roster.tablesList){
			String s = "";
			for (Corporation c: t.seatedCorps){
				s = s + c.id + " ";
			}
			System.out.println(s);
		}
		System.out.println("fin");
	}
}
