//Sudoku-Solver using Backtracking Algorithm
//Marvin Fuchs 16.12.2019
//Java-Version

public class sudokuSolver {
    public static void main(String[] args){
    	// int[][] sudoku = {{8,0,5,1,0,3,0,0,0},
				 //          {6,2,1,8,0,0,0,0,3},
				 //          {0,7,0,6,4,0,1,0,0},
				 //          {0,8,0,4,0,0,0,0,0},
				 //          {7,9,3,0,0,0,4,2,1},
				 //          {0,0,0,0,0,7,0,3,0},
				 //          {0,0,8,0,9,4,0,6,0},
				 //          {2,0,0,0,0,6,8,5,9},
				 //          {0,0,0,3,0,5,2,0,4}
     //    				 };
        int[][] sudoku = {{0,7,1,0,4,0,0,0,8},
				          {4,0,0,3,0,0,0,0,0},
				          {9,0,0,0,0,0,0,7,4},
				          {5,0,6,0,7,1,8,0,0},
				          {0,2,0,0,0,0,0,1,0},
				          {0,0,3,4,9,0,6,0,2},
				          {2,9,0,0,0,0,0,0,5},
				          {0,0,0,0,0,2,0,0,6},
				          {1,0,0,0,8,0,3,2,0}
        				 };
        Solver machine = new Solver(sudoku);
    }
}

class Solver {
    int[][] board;

    Solver (int[][] board) {
    	this.board = board;
    	System.out.println("Sudoku-Problem:");
    	this.show();
    	this.solve();
    	System.out.println();
    	System.out.println("Solution:");
    	this.show();
    }

    Boolean solve() {
    	Integer[] nextElement = this.findNextElement();
    	if (nextElement == null) {
    		return true;
    	}
    	else {
    		for (int i = 1; i<this.board[0].length+1; i++) {
    			if (this.validate(nextElement, i)) {
    				this.board[nextElement[0]][nextElement[1]]=i;
    				if (this.solve()) {
    					return true;
    				}
    				this.board[nextElement[0]][nextElement[1]]=0;
    			}
    		}
    		return false;
    	}
    }

    Boolean validate(Integer[] pos, int value) {
    	int row = pos[0];
    	int column = pos[1];
    	//check Row
    	for (int i = 0; i < this.board.length; i++) {
    		if (this.board[row][i]==value && i!=column) {
    			return false;
    		}
    	}
    	//check Column
    	for (int i = 0; i < this.board[0].length; i++) {
    		if (this.board[i][column]==value && i!=row) {
    			return false;
    		}
    	}
    	//check subgrid
    	int rowSpace = row / 3;
    	int columnSpace = column / 3;
    	for (int i = 3*rowSpace; i < 3*rowSpace+3; i++) {
    		for (int j = 3*columnSpace; j < 3*columnSpace+3; j++) {
    			if (this.board[i][j]==value && i!=row && j!=row) {
    				return false;
    			}
    		}
    	}
    	//else -> validated :)
    	return true;
    }

    Integer[] findNextElement() {
    	for (int i = 0; i < this.board.length; i++) {
    		for (int j = 0; j < this.board[0].length; j++) {
    			if (this.board[i][j]==0) {
    				Integer[] pos = {i,j};
    				return pos;
    			}
    		}
    	}
    	return null;

    }
    void show() {
    	for (int i = 0; i < this.board.length; i++) {
    		if (i % 3 == 0 && i > 0) {
    			System.out.println("---------------------");
    		}
    		for (int j = 0; j < this.board[0].length; j++) {
    			if (j % 3 == 0 && j > 0) {
    				System.out.print("| ");
    			}
    			if (j == this.board[0].length-1) {
    				System.out.println(this.board[i][j]);
    			}
    			else {
    				System.out.print(Integer.toString(this.board[i][j])+" ");
    			}
    		}
    	}
    }
}

