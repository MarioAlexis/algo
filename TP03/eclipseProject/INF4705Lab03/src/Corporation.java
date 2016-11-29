import java.util.ArrayList;
import java.util.List;

public class Corporation {
	public Integer id = 0;
	public Integer representativeCount;
	public List<Corporation> enemyCorps;
	public List<Corporation> adverseCorps;
	public List<Corporation> alliedCorps;
	public List<Table> availableTables;
	public Corporation(Integer representatives, Integer id){
		this.id = id;
		representativeCount = representatives;
		enemyCorps = new ArrayList<Corporation>();
		adverseCorps = new ArrayList<Corporation>();
		alliedCorps = new ArrayList<Corporation>();
		availableTables = new ArrayList<Table>();
	}
}
