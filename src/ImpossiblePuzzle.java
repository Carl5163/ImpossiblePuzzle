
public class ImpossiblePuzzle {
	
	public static final int HEART = 0;
	public static final int DIAMOND = 1;
	public static final int CLUB = 2;
	public static final int SPADE = 3;

	public static final int HEART_HOLE = 10;
	public static final int DIAMOND_HOLE = 11;
	public static final int CLUB_HOLE = 12;
	public static final int SPADE_HOLE = 13;
	
	public static final int UL = 0;
	public static final int UM = 1;
	public static final int UR = 2;
	public static final int ML = 3;
	public static final int MM = 4;
	public static final int MR = 5;
	public static final int LL = 6;
	public static final int LM = 7;
	public static final int LR = 8;
	
	private final Piece[] puzzleStart; 
	private Piece[] puzzle; 
	
	public static void main(String[] args) {
		new ImpossiblePuzzle();
	}
	
	public ImpossiblePuzzle() {
		puzzleStart = new Piece[9];
		puzzleStart[UL] = new Piece(CLUB_HOLE,    DIAMOND, HEART,   CLUB_HOLE,    1);
		puzzleStart[UM] = new Piece(DIAMOND_HOLE, DIAMOND, HEART,   HEART_HOLE,   2);
		puzzleStart[UR] = new Piece(HEART_HOLE,   DIAMOND, SPADE,   DIAMOND,      3);
		puzzleStart[ML] = new Piece(SPADE_HOLE,   DIAMOND, SPADE,   HEART_HOLE,   4);
		puzzleStart[MM] = new Piece(SPADE_HOLE,   SPADE,   HEART,   CLUB_HOLE,    5);
		puzzleStart[MR] = new Piece(HEART_HOLE,   SPADE,   SPADE,   CLUB_HOLE,    6);
		puzzleStart[LL] = new Piece(CLUB_HOLE,    CLUB,    DIAMOND, DIAMOND_HOLE, 7);
		puzzleStart[LM] = new Piece(SPADE_HOLE,   HEART,   CLUB,    HEART_HOLE,   8);
		puzzleStart[LR] = new Piece(DIAMOND_HOLE, HEART,   CLUB,    CLUB_HOLE,    9);
		
		
		Piece swapTemp;
		printPuzzle(puzzleStart);
		System.out.println();
		
		int count = 1;
		for(int i = 0; i < 9; i++) {
			for(int rot = 0; rot < 4; rot++) {
				for(int j = 0; j < 9; j++) {
					puzzle = puzzleStart.clone();
					swapTemp = puzzle[j];
					puzzle[j] = puzzle[i];
					puzzle[i] = swapTemp;
					printPuzzle(puzzle);
					System.out.println();
					if(isSolved()) {
						System.out.println("SOLVED!!!!");
						return;
					}
				}
				puzzleStart[i].rotate();
			}
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
	

	public boolean isSolved() {
		boolean ret = true;
		//UPPER LEFT PIECE
			if(puzzleStart[UL].right > 3) {
				if(puzzleStart[UM].left+10 != puzzleStart[UL].right) {
					ret = false;
				}
			} else {
				if(puzzleStart[UM].left-10 != puzzleStart[UL].right) {
					ret = false;
				}
			}
			if(puzzleStart[UL].down > 3) {
				if(puzzleStart[ML].up+10 != puzzleStart[UL].down) {
					ret = false;
				}
			} else {
				if(puzzleStart[ML].up-10 != puzzleStart[UL].down) {
					ret = false;
				}
			}
		//UPPER MIDDLE PIECE
			if(puzzleStart[UM].right > 3) {
				if(puzzleStart[UR].left+10 != puzzleStart[UM].right) {
					ret = false;
				}
			} else {
				if(puzzleStart[UR].left-10 != puzzleStart[UM].right) {
					ret = false;
				}
			}
			if(puzzleStart[UM].down > 3) {
				if(puzzleStart[MM].up+10 != puzzleStart[UM].down) {
					ret = false;
				}
			} else {
				if(puzzleStart[MM].up-10 != puzzleStart[UM].down) {
					ret = false;
				}
			}
		//UPPER RIGHT PIECE
			if(puzzleStart[UR].down > 3) {
				if(puzzleStart[MR].up+10 != puzzleStart[UR].down) {
					ret = false;
				}
			} else {
				if(puzzleStart[MR].up-10 != puzzleStart[UR].down) {
					ret = false;
				}
			}
			
		//MIDDLE LEFT PIECE
			if(puzzleStart[ML].right > 3) {
				if(puzzleStart[MM].left+10 != puzzleStart[ML].right) {
					ret = false;
				}
			} else {
				if(puzzleStart[MM].left-10 != puzzleStart[ML].right) {
					ret = false;
				}
			}
			if(puzzleStart[ML].down > 3) {
				if(puzzleStart[LL].up+10 != puzzleStart[ML].down) {
					ret = false;
				}
			} else {
				if(puzzleStart[LL].up-10 != puzzleStart[ML].down) {
					ret = false;
				}
			}
		//MIDDLE MIDDLE PIECE
			if(puzzleStart[MM].right > 3) {
				if(puzzleStart[MR].left+10 != puzzleStart[MM].right) {
					ret = false;
				}
			} else {
				if(puzzleStart[MR].left-10 != puzzleStart[MM].right) {
					ret = false;
				}
			}
			if(puzzleStart[MM].down > 3) {
				if(puzzleStart[LM].up+10 != puzzleStart[MM].down) {
					ret = false;
				}
			} else {
				if(puzzleStart[LM].up-10 != puzzleStart[MM].down) {
					ret = false;
				}
			}
		//MIDDLE RIGHT PIECE
			if(puzzleStart[MR].down > 3) {
				if(puzzleStart[LR].up+10 != puzzleStart[MR].down) {
					ret = false;
				}
			} else {
				if(puzzleStart[LR].up-10 != puzzleStart[MR].down) {
					ret = false;
				}
			}
		//BOTTOM LEFT PIECE
			if(puzzleStart[LL].right > 3) {
				if(puzzleStart[LM].left+10 != puzzleStart[LL].right) {
					ret = false;
				}
			} else {
				if(puzzleStart[LM].left-10 != puzzleStart[LL].right) {
					ret = false;
				}
			}
		//BOTTOM MIDDLE PIECE
			if(puzzleStart[MM].right > 3) {
				if(puzzleStart[LR].left+10 != puzzleStart[LM].right) {
					ret = false;
				}
			} else {
				if(puzzleStart[LR].left-10 != puzzleStart[LM].right) {
					ret = false;
				}
			}
		return ret;
	}
}
