
import java.util.*;

public class NQueens {

    static int N;

    // Print chessboard
    static void printBoard(int board[][]) {
        System.out.println("\nN-Queens Solution:");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check whether queen can be placed
    static boolean isSafe(int board[][], int row, int col) {

        // Check left side of current row
        for (int j = 0; j < col; j++) {
            if (board[row][j] == 1)
                return false;
        }

        // Check upper diagonal
        for (int i = row, j = col; i >= 0 && j >= 0;
             i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check lower diagonal
        for (int i = row, j = col; i < N && j >= 0;
             i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // Solve N-Queens using Backtracking
    static boolean solveNQueens(int board[][], int col) {

        // All queens are placed
        if (col >= N)
            return true;

        // Try placing queen in each row
        for (int i = 0; i < N; i++) {

            if (isSafe(board, i, col)) {

                board[i][col] = 1;

                if (solveNQueens(board, col + 1))
                    return true;

                // Backtracking
                board[i][col] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of N: ");
        N = sc.nextInt();

        if (N < 1) {
            System.out.println("Invalid value of N!");
            sc.close();
            return;
        }

        int board[][] = new int[N][N];

        // Start solving from first column
        if (solveNQueens(board, 0)) {
            printBoard(board);
        } else {
            System.out.println("Solution does not exist!");
        }

        sc.close();
    }
}
