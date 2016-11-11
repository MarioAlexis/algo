
public class Block {
	public Block(int x, int y, int z){
		base = new int[2];
		height = x;
		if(y<=x){
			base[0] = y;
			base[1] = z;
		} else {
			base[0] = z;
			base[1] = y;
		}
		area = y * z;
	}
	public int height;
	public int[] base;
	public int area;
}
