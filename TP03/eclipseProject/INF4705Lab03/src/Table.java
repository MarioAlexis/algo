import java.util.ArrayList;
import java.util.List;

public class Table {
	public double possibleDeviation;
	public int possibleInducedWeight;
	public Integer peopleSeated = 0;
	public List<Corporation> seatedCorps;
	public Table(){
		seatedCorps = new ArrayList<Corporation>();
	}
}
