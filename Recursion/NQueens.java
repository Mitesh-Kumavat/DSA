package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    /**
     * Recursive function to place queens column by column.
     *
     * @param col           -> current column where we are trying to place a queen
     * @param board         -> chessboard representation
     * @param n             -> board size (n x n)
     * @param leftRow       -> marks if a queen is already placed in a row
     * @param upperDiagonal -> marks if a queen is placed in upper diagonal
     * @param lowerDiagonal -> marks if a queen is placed in lower diagonal
     * @param ans           -> list of all valid board configurations
     */
    public static void solve(
            int col,
            char[][] board,
            int n,
            int[] leftRow,
            int[] upperDiagonal,
            int[] lowerDiagonal,
            List<List<String>> ans) {

        /*
         * BASE CASE:
         * If all columns are filled, a valid solution is formed.
         */
        if (col == n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp.add(new String(board[i]));
            }

            ans.add(temp);
            return;
        }

        /*
         * Try placing queen in every row of the current column
         */
        for (int row = 0; row < n; row++) {

            /*
             * Check if it is safe to place a queen at (row, col)
             * using precomputed arrays for O(1) validation.
             */
            if (leftRow[row] == 0 &&
                    lowerDiagonal[row + col] == 0 &&
                    upperDiagonal[n - 1 + col - row] == 0) {

                // PLACE QUEEN
                board[row][col] = 'Q';
                leftRow[row] = 1;
                lowerDiagonal[row + col] = 1;
                upperDiagonal[n - 1 + col - row] = 1;

                // Recurse for next column
                solve(col + 1, board, n, leftRow, upperDiagonal, lowerDiagonal, ans);

                // BACKTRACK
                board[row][col] = '.';
                leftRow[row] = 0;
                lowerDiagonal[row + col] = 0;
                upperDiagonal[n - 1 + col - row] = 0;
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> ans = new ArrayList<>();

        // Initialize empty chess board
        char[][] board = new char[n][n];
        for (char[] row : board)
            Arrays.fill(row, '.');

        /*
         * leftRow[i] -> is there a queen in row i ?
         * lowerDiagonal[i] -> is there a queen in lower diagonal i ?
         * upperDiagonal[i] -> is there a queen in upper diagonal i ?
         */
        int[] leftRow = new int[n];
        int[] lowerDiagonal = new int[2 * n - 1];
        int[] upperDiagonal = new int[2 * n - 1];

        // Start placing queens from column 0
        solve(0, board, n, leftRow, upperDiagonal, lowerDiagonal, ans);

        return ans;
    }

    public static void main(String[] args) {
        int n = 6;
        NQueens nq = new NQueens();
        List<List<String>> result = nq.solveNQueens(n);
        System.out.println(result);
    }
}
