import java.util.Random;

public class Main {
	public static void main(String[] args)
	{
		Roster currentSolution = RosterFactory.createRoster("Files/160_3_0.6.1");
		Random rand = new Random();
		double start, end;
		double initTemp = 50.0f;
		start = (double)System.nanoTime();
		Roster bestSolution = new Roster();
		TableSeater.organize(currentSolution);
		double currentSolutionWeigh = currentSolution.score;
		while(true)
		{
			Roster neighborSolution = HardCopyRoster(currentSolution);
			/*
			 * Switch Candidat here
			 */
			double neighborSolutionWeigh = neighborSolution.score;
			double cost = currentSolutionWeigh - neighborSolutionWeigh;
			if(accept(cost, initTemp) > rand.nextDouble())
			{
				
			}
			

			verifySolution(CurrentSolution);
		}
	}
	
	public static boolean verifySolution(Roster roster){
		int corpTotal = 0;
		int score = 0;
		Corporation c1;
		Corporation c2;
		for (Table t : roster.tablesList){
			corpTotal+=t.seatedCorps.size();
		}
		if(corpTotal!=roster.corporationsList.size()){
			return false;
		}
		for (Table t : roster.tablesList){
			for (int i = 0; i<roster.enemyPairs.size(); i++){
				c1=roster.corporationsList.get(roster.enemyPairs.get(i)[0]);
				c2=roster.corporationsList.get(roster.enemyPairs.get(i)[1]);
				if(t.seatedCorps.contains(c1)&&t.seatedCorps.contains(c2)){
					return false;
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
		if(score != roster.score)
			return false;
		
		return true;
	}
	
	static public double accept(double cost, double temperature)
	{
		return Math.min(1.0f, Math.exp(-(cost/temperature)));
	}
	
	static public void printSolution(Roster solution)
	{
		for (Table t: solution.tablesList){
			String s = "";
			for (Corporation c: t.seatedCorps){
				s = s + c.id + " ";
			}
			System.out.println(s);
		}
		System.out.println("fin");
	}
	
	static public Roster HardCopyRoster(Roster toCopy)
	{
		// friends copy
		Roster toReturn = new Roster();
		for(int i=0; i < toCopy.friendPairs.size(); i++)
		{
			toReturn.friendPairs.add(toCopy.notFriendPairs.get(i));
		}
		
		// no friends copy
		for(int i=0; i < toCopy.notFriendPairs.size(); i++)
		{
			toReturn.notFriendPairs.add(toCopy.notFriendPairs.get(i));
		}
		
		// enemy copy
		for(int i=0; i < toCopy.enemyPairs.size(); i++)
		{
			toReturn.enemyPairs.add(toCopy.enemyPairs.get(i));
		}
		
		// tables copy
		for(int i=0; i < toCopy.tablesList.size(); i++)
		{
			toReturn.tablesList.add(new Table());
			toReturn.tablesList.get(i).peopleSeated = toCopy.tablesList.get(i).peopleSeated;
			
			for(int j=0; j < toCopy.tablesList.get(i).seatedCorps.size(); j++)
			{
				toReturn.tablesList.get(i).seatedCorps.add(toCopy.tablesList.get(i).seatedCorps.get(j));
			}
		}
		
		return toReturn;
	}
}
