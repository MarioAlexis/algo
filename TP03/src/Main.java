import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
	static final double FROZEN_STATE= 0.0;
	static final double TEMP_INIT = 200.0;
	public static void main(String[] args)
	{
		boolean printSolution = (Integer.parseInt(args[0]) != 0);			// Imprimme info block ou non
		boolean printtime = (Integer.parseInt(args[1]) != 0);	// Imprimme temps execution ou non
		
		/*
		 * Generateur aleatoire
		 * variable de manipulation du timer
		 */
		Random rand = new Random();
		double temperature = TEMP_INIT;
		double deltaTemp;		
		long startprog, endprog;
		double startTime, endTime;
		startprog = System.nanoTime();
		startTime = (double)System.nanoTime();
		
		// Lecture du fichier et creation d'une premiere solution
		Roster currentSolution = RosterFactory.createRoster(args[2]);
		Roster neighborSolution;
		Roster bestSolution = hardCopyRoster(currentSolution);		// Meilleure solution = a la solution courante
		TableSeater.organize(currentSolution);						// Formation des tables
		/*
		 * tant et aussi longtemps que la solution nest pas valide
		 * on creer une autre solution
		 * Ceci doit fait avant de entamer un processus de recuit simule
		 */
		while (!verifySolution(currentSolution)){
			currentSolution = null;
			currentSolution = hardCopyRoster(bestSolution);
			TableSeater.organize(currentSolution);
		}
		bestSolution = null;
		bestSolution = hardCopyRoster(currentSolution);
		bestSolution.updateScore();
		printSolution(printSolution, bestSolution);					// sortie a la console de la premiere solution
		/*
		 * debut de l'algo metaheuristique recuit simule
		 * en fonction du temps
		 * donc 3 minutes
		 */
		while(temperature >= FROZEN_STATE){
			neighborSolution = null;
			neighborSolution = hardCopyRoster(currentSolution);								// Initialisation d'une solution voisine
			MovementInducer.moveSolution(neighborSolution);									// induction d'un mouvement a la solution voisine
			double cost = (-1*neighborSolution.score) - (-1*currentSolution.score);			// on capture le delta des poids des solutions
			if(verifySolution(neighborSolution)){
				/*
				 * si la solution voisine est valide
				 * un processus de recuit simule peu accepter une solution moins bonne
				 * avec une petite probabilite
				 */
				if(accept(cost, temperature) > rand.nextDouble()){
					currentSolution = neighborSolution;
					if(currentSolution.score < bestSolution.score){
						bestSolution = currentSolution;
						if(printSolution)
						{
							printSolution(printSolution, bestSolution);
						}
					}
				}
			} 
			endTime = (double)System.nanoTime();
			deltaTemp = (endTime - startTime)/((double)(TimeUnit.MINUTES.toNanos(3)))*TEMP_INIT;
			temperature-= deltaTemp;
			startTime = (double)System.nanoTime();
		}
		endprog = System.nanoTime();
		if(printtime)
		{
			System.out.println("Temps d'execution du programme : " + TimeUnit.NANOSECONDS.toSeconds(endprog-startprog) + " secondes");
		}
	}
	
	/*
	 * Methode qui valide une solution recue en parametre
	 */
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
			//System.out.println("Not everyone is seated");
			return false;
		}
		for (Table t : roster.tablesList){
			for (int i = 0; i<roster.enemyPairs.size(); i++){
				c1=roster.corporationsList.get(roster.enemyPairs.get(i)[0]);
				c2=roster.corporationsList.get(roster.enemyPairs.get(i)[1]);
				if(t.seatedCorps.contains(c1)&&t.seatedCorps.contains(c2)){
					//System.out.println("Enemies on the same table");
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
			//System.out.println("Score not equal");
			return false;	
		}
		
		return true;
	}
	
	/*
	 * condition de acceptation d'un processus recuit simule
	 */
	static public double accept(double cost, double temperature)
	{
		return Math.min(1.0, Math.exp(-(cost/temperature)));
	}

	/*
	 * Methode qui sortie la solution recue en parametre a la console
	 */
	static public void printSolution(boolean print, Roster solution)
	{
		//System.out.println("New optimum found with score=" + solution.score);
		if(print){
			for (Table t: solution.tablesList){
				String s = "";
				for (Corporation c: t.seatedCorps){
					s = s + c.id + " ";
				}
				System.out.println(s);
			}
			System.out.println("fin");
		}
	}
	
	/*
	 * Methode qui permet de copier les variable d'une classe vers une autre classe
	 * du meme type
	 */
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
