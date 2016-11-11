import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlockListCreator {
	public static List<Block> createBlockList (String filename){
		List <Block> blockList = new ArrayList<Block>();
		boolean error = false;
		BufferedReader br = null;
		int blockCount = 0;
		try {
			br = new BufferedReader(new FileReader(filename));
		    String line = br.readLine();
		    blockCount =  Integer.parseInt(line);
		    line = br.readLine();
		    while (line != null && line != "") {
		    	String [] sData = line.split("\\s+");
		    	blockList.add(new Block(Integer.parseInt(sData[0]), Integer.parseInt(sData[1]), Integer.parseInt(sData[2])));
				blockList.add(new Block(Integer.parseInt(sData[1]), Integer.parseInt(sData[0]), Integer.parseInt(sData[2])));
				blockList.add(new Block(Integer.parseInt(sData[2]), Integer.parseInt(sData[1]), Integer.parseInt(sData[0])));
		        line = br.readLine();
		    }
			if (blockCount*3!=blockList.size()){
				error = true;
				System.out.println("ERROR: blockCount = " + blockCount + "  ; blockList.size() = " + blockList.size());
			}
		} catch (IOException e) {
			error = true;
			System.out.println(e);
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				error = true;
				System.out.println(e);
			}
		}
		if (!error){
			return blockList;	
		} else {
			return null;
		}
	}
}
