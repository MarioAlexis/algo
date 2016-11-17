import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlockListCreator {
	public static List<Block> createBlockList (String filename){
		List <Block> blockList = new ArrayList<Block>();
		boolean error = false;
		Scanner scanner;
		int blockCount = 0;
		try {
			scanner = new Scanner(new File(filename));
			//br = new BufferedReader(new FileReader(filename));
		    //String line = br.readLine();
		    String line = scanner.nextLine();
		    blockCount =  Integer.parseInt(line);
		    //line = br.readLine();
		    while (scanner.hasNextLine()) 
		    {
		    	line = scanner.nextLine();
		    	String [] sData = line.split(" ");
		    	blockList.add(new Block(Integer.parseInt(sData[0]), Integer.parseInt(sData[1]), Integer.parseInt(sData[2])));
				blockList.add(new Block(Integer.parseInt(sData[1]), Integer.parseInt(sData[0]), Integer.parseInt(sData[2])));
				blockList.add(new Block(Integer.parseInt(sData[2]), Integer.parseInt(sData[1]), Integer.parseInt(sData[0])));
		        //line = br.readLine();
		    }
			if (blockCount*3!=blockList.size()){
				error = true;
				System.out.println("ERROR: blockCount = " + blockCount + "  ; blockList.size() = " + blockList.size());
			}
		} 
		catch (IOException e) 
		{
			System.exit(1);
		} 
		
		if (!error){
			return blockList;	
		} else {
			return null;
		}
	}
}
