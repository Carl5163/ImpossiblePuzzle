
public class Piece {
		
	public int left;
	public int up;
	public int right;
	public int down;
	public int id;
	
	
	public Piece(int l, int u, int r, int d, int ids) {
		left = l;
		up = u;
		right = r;
		down = d;
		id = ids;
	}
	
	public void rotate() {
		int temp = up;
		up = left;
		left = down;
		down = right;
		right = temp;
	}
		
	
	@Override
	public String toString() {
		return Integer.toString(id);
		//return String.format("%d, %d, %d, %d", left, up, right, down);
	}

}
