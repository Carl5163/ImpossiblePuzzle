import java.util.ArrayList;

public class Worker implements Runnable {
	
	public static final int UL = 0;
	public static final int UM = 1;
	public static final int UR = 2;
	public static final int ML = 3;
	public static final int MM = 4;
	public static final int MR = 5;
	public static final int LL = 6;
	public static final int LM = 7;
	public static final int LR = 8;
	
	public static final int HEART = 0;
	public static final int DIAMOND = 1;
	public static final int CLUB = 2;
	public static final int SPADE = 3;

	public static final int HEART_HOLE = 4;
	public static final int DIAMOND_HOLE = 5;
	public static final int CLUB_HOLE = 6;
	public static final int SPADE_HOLE = 7;
	
	private static int workerCount = 0;
	
	private final Piece[] puzzleStart;
	
	private int start, end;
	private Piece[] puzzle;
	private ArrayList<int[]> intPerms;
	private int id;
	private ImpossiblePuzzle main;
	
	public Worker(ImpossiblePuzzle main, ArrayList<int[]> intPerms, int start, int end) {

		puzzleStart = new Piece[9];
		puzzleStart[Worker.UL] = new Piece(CLUB_HOLE,    DIAMOND, HEART,   CLUB_HOLE,    1);
		puzzleStart[Worker.UM] = new Piece(DIAMOND_HOLE, DIAMOND, HEART,   HEART_HOLE,   2);
		puzzleStart[Worker.UR] = new Piece(HEART_HOLE,   DIAMOND, SPADE,   DIAMOND_HOLE, 3);
		puzzleStart[Worker.ML] = new Piece(SPADE_HOLE,   DIAMOND, SPADE,   HEART_HOLE,   4);
		puzzleStart[Worker.MM] = new Piece(SPADE_HOLE,   SPADE,   HEART,   CLUB_HOLE,    5);
		puzzleStart[Worker.MR] = new Piece(HEART_HOLE,   SPADE,   SPADE,   CLUB_HOLE,    6);
		puzzleStart[Worker.LL] = new Piece(CLUB_HOLE,    CLUB,    DIAMOND, DIAMOND_HOLE, 7);
		puzzleStart[Worker.LM] = new Piece(SPADE_HOLE,   HEART,   CLUB,    HEART_HOLE,   8);
		puzzleStart[Worker.LR] = new Piece(DIAMOND_HOLE, HEART,   CLUB,    CLUB_HOLE,    9);
		this.start = start;
		this.end = end;
		this.intPerms = intPerms;
		id = workerCount;
		workerCount++;
		this.main = main;
	}
	
	
	@Override
	public void run() {
		for(int r = start; r < end; r++) {
			for(int[] a : intPerms) {
				puzzle = puzzleFromIntArray(a);
				if(isSolved()) {
					main.reportSolution(id, puzzle);
				}
				
			}
			int[] rots = rotCalculator(r);
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j <= rots[i]; j++) {
					puzzleStart[i].rotate();
				}
			}
		}
	}	

	public boolean isSolved() {
		//UPPER LEFT PIECE
			if(Math.abs(puzzle[UL].right - puzzle[UM].left) != 4) {
				return false;
			}
			if(Math.abs(puzzle[UL].down - puzzle[ML].up) != 4) {
				return false;
			}
		//UPPER MIDDLE PIECE
			if(Math.abs(puzzle[UM].right - puzzle[UR].left) != 4) {
				return false;
			}
			if(Math.abs(puzzle[UM].down - puzzle[MM].up) != 4) {
				return false;
			}
		//UPPER RIGHT PIECE
			if(Math.abs(puzzle[UR].down - puzzle[MR].up) != 4) {
				return false;
			}			
		//MIDDLE LEFT PIECE
			if(Math.abs(puzzle[ML].right - puzzle[MM].left) != 4) {
				return false;
			}
			if(Math.abs(puzzle[ML].down - puzzle[LL].up) != 4) {
				return false;
			}
		//MIDDLE MIDDLE PIECE
			if(Math.abs(puzzle[MM].right - puzzle[ML].left) != 4) {
				return false;
			}
			if(Math.abs(puzzle[MM].down - puzzle[LM].up) != 4) {
				return false;
			}
		//MIDDLE RIGHT PIECE
			if(Math.abs(puzzle[MR].down - puzzle[LR].up) != 4) {
				return false;
			}
		//BOTTOM LEFT PIECE
			if(Math.abs(puzzle[LL].right - puzzle[LM].left) != 4) {
				return false;
			}
		//BOTTOM MIDDLE PIECE
			if(Math.abs(puzzle[LM].right - puzzle[LR].left) != 4) {
				return false;
			}
		return true;
	}
	
	private int[] rotCalculator(int num) {
		int[] ret;
		int index;
		ret = new int[9];
		index = 0;
		while(num > 3) {
			ret[index] = num % 4;
			index++;
			num -= ret[index];
			num /= 4;
		}
		ret[index] = num;
		return ret;
		
		
		
	}
	
	private Piece[] puzzleFromIntArray(int[] intArray) {
		Piece[] ret;
		ret = new Piece[9];
		for(int i = 0; i < 9; i++) {
			ret[i] = puzzleStart[intArray[i]];
		}
		return ret;
	}
	
	
}
