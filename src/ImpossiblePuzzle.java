import java.util.ArrayList;

public class ImpossiblePuzzle {
	
	public static final String[] NAMES = {"HEART", "DIAMOND", "CLUB", "SPADE", "HEART_HOLE", "DIAMOND_HOLE", "CLUB_HOLE", "SPADE_HOLE"};
	
	public static final int NUM_WORKERS = 100;
	
	private ArrayList<Piece[]> solutions;
	
	private ArrayList<int[]> intPerms;
	
	public static void main(String[] args) {
		new ImpossiblePuzzle();
	}
	
	public ImpossiblePuzzle() {
		solutions = new ArrayList<Piece[]>();
		ArrayList<Integer> bInts = new ArrayList<Integer>();
		ArrayList<Integer> eInts = new ArrayList<Integer>();
		eInts.add(0); eInts.add(1); eInts.add(2); 
		eInts.add(3); eInts.add(4); eInts.add(5);
		eInts.add(6); eInts.add(7); eInts.add(8);
		intPerms = new ArrayList<int[]>();
		getIntPerms(bInts, eInts);
		int divide = (int) Math.ceil(262143/NUM_WORKERS);
		System.out.println(NUM_WORKERS + " workers trying " + divide + " solutions each.");
		for(int i = 0; i < NUM_WORKERS; i++) {
			new Thread(new Worker(this, intPerms, i*divide, (i+1)*divide)).start();
		}
		
		
	}

	public void printPuzzle(Piece[] p) {
		for(int i = 0; i < 9; i++) {
			System.out.print(p[i] + " ");
			if(i == 2 || i == 5) {
				System.out.println();
			}
		}
		System.out.println();
	}
	
	public void reportSolution(int id, Piece[] solution) {
		boolean dupe = true;
		for(Piece[] s : solutions) {
			for(int i = 0; i < 9; i++) {
				if(!solution[i].equals(s[i])) {
					dupe = false;
				}
			}
		}
		if(!dupe || solutions.size() == 0) {
			solutions.add(solution);
			System.out.println("Solved by Worker #" + id + "!!!");
			printPuzzle(solution);
		}
	}
	
	
	private void getIntPerms(ArrayList<Integer> beginInts, ArrayList<Integer> endInts) {
		if(endInts.size() <= 1) {
			beginInts.add(endInts.get(0));
			int[] array = new int[9];
			for(int i = 0; i < 9; i++) {
				array[i] = beginInts.get(i);
			}
			intPerms.add(array);
		} else {
			for(int i = 0; i < endInts.size(); i++) {
				ArrayList<Integer> newEndArray = new ArrayList<Integer>(); 
				ArrayList<Integer> newBeginArray = new ArrayList<Integer>(); 
				
				for(int c = 0; c < endInts.size(); c++) {
					if(c != i) {
						newEndArray.add(endInts.get(c));
					}
				}				
				newBeginArray.addAll(beginInts);
				newBeginArray.add(endInts.get(i));
				getIntPerms(newBeginArray, newEndArray);
			}
		}
	}

}
