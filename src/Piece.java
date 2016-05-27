
public class Piece {
		
	public int left;
	public int up;
	public int right;
	public int down;
	public int id;
	int numRots; 
	
	
	public Piece(int l, int u, int r, int d, int ids) {
		left = l;
		up = u;
		right = r;
		down = d;
		id = ids;
		numRots = 0;
	}
	
	public void rotate() {
		numRots = (numRots+1) % 4;
		int temp = up;
		up = left;
		left = down;
		down = right;
		right = temp;
	}
		
	
	@Override
	public String toString() {
		return String.format("%d%d", id, numRots);
	}
	
	public boolean equals(Piece other) {
		return (id == other.id && numRots == other.numRots);
	}

}
