import java.util.Random;

public class Main {
	public static void main(String[] args)
	{
		double INIT_TEMP = 200.0f;
		int tempCnt = 1;
		Roster currentSolution = RosterFactory.createRoster("Files/160_3_0.6.1");
		
		Random rand = new Random();
		double start;
		double temp = INIT_TEMP;
		start = (double)System.nanoTime();
		TableSeater.organize(currentSolution);
	    if(!verifySolution(currentSolution)){
	    	System.out.println("Failed.");
	    	return;
		}
		Roster bestSolution = hardCopyRoster(currentSolution);
		bestSolution.updateScore();
		printSolution(bestSolution);
		while(((double)System.nanoTime() - start)/1000000000.0f < (60*3)){
			if (temp == 0){
				tempCnt++;
				temp = INIT_TEMP*tempCnt;
				System.out.println("TEMP x 2!!!!");
				System.out.println("TEMP x 2!!!!");
				System.out.println("TEMP x 2!!!!");
				System.out.println("TEMP x 2!!!!");
				System.out.println("TEMP x 2!!!!");
				break;
			}
			Roster neighborSolution = hardCopyRoster(currentSolution);
			MovementInducer.moveSolution(neighborSolution);
			if(verifySolution(neighborSolution)){
				double cost = (-1*neighborSolution.score) - (-1*currentSolution.score);
				if(accept(cost, temp) > rand.nextDouble()){
					currentSolution = neighborSolution;
					System.out.println("New current Solution with score= " + currentSolution.score);
					if(currentSolution.score < bestSolution.score){
						bestSolution = currentSolution;
						printSolution(bestSolution);
					}
				} else {
					System.out.println("No accept");
				}		
				temp-=0.05;
			} 
		}
		
	}
	
	public static boolean verifySolution(Roster roster){
		int corpTotal = 0;
		int weight = 0;
		double score = 0;
		Corporation c1;
		Corporation c2;
		for (Table t : roster.tablesList){
			corpTotal+=t.seatedCorps.size();
		}
		if(corpTotal!=roster.corporationsList.size()){
			System.out.println("Not everyone is seated");
			return false;
		}
		for (Table t : roster.tablesList){
			for (int i = 0; i<roster.enemyPairs.size(); i++){
				c1=roster.corporationsList.get(roster.enemyPairs.get(i)[0]);
				c2=roster.corporationsList.get(roster.enemyPairs.get(i)[1]);
				if(t.seatedCorps.contains(c1)&&t.seatedCorps.contains(c2)){
					System.out.println("Enemies on the same table");
					return false;
				}
			}
		}
		for (Table t : roster.tablesList){
			for (int i = 0; i<roster.friendPairs.size(); i++){
				c1=roster.corporationsList.get(roster.friendPairs.get(i)[0]);
				c2=roster.corporationsList.get(roster.friendPairs.get(i)[1]);
				if(t.seatedCorps.contains(c1)&&t.seatedCorps.contains(c2)){
					weight--;
				}
			}
		}
		for (Table t : roster.tablesList){
			for (int i = 0; i<roster.notFriendPairs.size(); i++){
				c1=roster.corporationsList.get(roster.notFriendPairs.get(i)[0]);
				c2=roster.corporationsList.get(roster.notFriendPairs.get(i)[1]);
				if(t.seatedCorps.contains(c1)&&t.seatedCorps.contains(c2)){
					weight++;
				}
			}
		}
		//roster.totalWeight = weight;
		score = weight + roster.getDeviation(null, null);
		if(score != roster.score){
			System.out.println("Score not equal");
			return false;	
		}
		
		return true;
	}
	
	static public double accept(double cost, double temperature)
	{
		return Math.min(1.0f, Math.exp(-(cost/temperature)));
	}
	
	static public void printSolution(Roster solution)
	{
		System.out.println("New optimum found with score=" + solution.score);
		for (Table t: solution.tablesList){
			String s = "";
			for (Corporation c: t.seatedCorps){
				s = s + c.id + " ";
			}
			System.out.println(s);
		}
		System.out.println("fin");
	}
	
	static public Roster hardCopyRoster(Roster toCopy)
	{
		// friends copy
		Roster toReturn = new Roster();
		
		toReturn.totalWeight = toCopy.totalWeight;
/*		for(int i=0; i < toCopy.friendPairs.size(); i++)
		{
			toReturn.friendPairs.add(toCopy.notFriendPairs.get(i));
		}*/
		toReturn.friendPairs = toCopy.friendPairs;
		
		// no friends copy
/*		for(int i=0; i < toCopy.notFriendPairs.size(); i++)
		{
			toReturn.notFriendPairs.add(toCopy.notFriendPairs.get(i));
		}*/
		toReturn.notFriendPairs = toCopy.notFriendPairs;
		
		// enemy copy
/*		for(int i=0; i < toCopy.enemyPairs.size(); i++)
		{
			toReturn.enemyPairs.add(toCopy.enemyPairs.get(i));
		}*/
		toReturn.enemyPairs = toCopy.enemyPairs;
		
		// tables copy
		toReturn.corporationsList = toCopy.corporationsList;
		
		for(int i=0; i < toCopy.tablesList.size(); i++)
		{
			toReturn.tablesList.add(new Table()); 
			toReturn.tablesList.get(i).peopleSeated = 0;
			
			for(int j=0; j < toCopy.tablesList.get(i).seatedCorps.size(); j++)
			{
				toReturn.tablesList.get(i).seatedCorps.add(toCopy.tablesList.get(i).seatedCorps.get(j));
				toReturn.tablesList.get(i).peopleSeated+=toCopy.tablesList.get(i).seatedCorps.get(j).representativeCount;
			}
		}
		
		return toReturn;
	}
}
