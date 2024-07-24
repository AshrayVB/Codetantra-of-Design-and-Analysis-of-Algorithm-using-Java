package q30658;
import java.util.*;
public class NQueenBacktracking {
    int n;
    NQueenBacktracking(int n) {
    	this.n = n;
    }

    /* Display solution*/
    void displaySolution(int queenBoard[][]) {
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++)
    			System.out.print(" " + queenBoard[i][j] + " ");
    			System.out.println();
    		
    	}
    }

        /* isSafe() function checks if a queen can be placed on queenBoard[row][col]. */
    boolean isSafe(int queenBoard[][], int row, int col) {
        // Check the row on left side
        for (int i = 0; i < col; i++) {
            if (queenBoard[row][i] == 1) {
                return false;
            }
        }

        // Check the upper diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (queenBoard[i][j] == 1) {
                return false;
            }
        }

        // Check the lower diagonal on left side
        for (int i = row, j = col; j >= 0 && i < n; i++, j--) {
            if (queenBoard[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    /* Utility function for N Queen problem solution */
    boolean utilityFunctionNQueen(int queenBoard[][], int col) {
        // Base case: If all queens are placed, return true
        if (col >= n) {
            return true;
        }
        // Try placing the queen in all rows of the current column
        for (int i = 0; i < n; i++) {
            if (isSafe(queenBoard, i, col)) {
                // Place the queen
                queenBoard[i][col] = 1;

                // Recur to place rest of the queens
                if (utilityFunctionNQueen(queenBoard, col + 1)) {
                    return true;
                }

                // If placing the queen doesn't lead to a solution, backtrack
                queenBoard[i][col] = 0; // BACKTRACK
            }
        }

        // If the queen cannot be placed in any row in this column, return false
        return false;
	}
    /* uses solveNQUtil () to solve the problem. Note that there may be more than one
    /* solutions, this function prints one of the feasible solutions.*/
    boolean mainSolutionNQueen() {
    	int queenBoard[][] = new int[n][n];
    	if (utilityFunctionNQueen(queenBoard, 0) == false) {
    		System.out.print("Solution does not exist");
    		return false;
    		
    	}
    	displaySolution(queenBoard);
    	return true;
    }

    // Driver main method
    public static void main(String args[]) {
    	int n;
    	System.out.print("Enter size of queen board i.e. N: ");
    	Scanner sc = new Scanner(System.in);
    	n = sc.nextInt();
    	NQueenBacktracking queen = new NQueenBacktracking(n);
    	queen.mainSolutionNQueen();
    }
}
