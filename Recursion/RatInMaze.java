package Recursion;

import java.util.ArrayList;
import java.util.List;

public class RatInMaze {

    /**
     * Checks whether the rat can move to cell (i, j).
     *
     * Conditions for a safe cell:
     * 1. Cell must be inside the grid
     * 2. Cell must be open (maze[i][j] == 1)
     * 3. Cell must not be visited already
     *
     * @return true if the cell is safe to move into
     */
    public static boolean isSafe(int i, int j, int n, int[][] visited, int[][] maze) {

        if (i >= 0 && i < n && j >= 0 && j < n && maze[i][j] == 1 && visited[i][j] == 0) {
            return true;
        }

        return false;
    }

    /**
     * Recursive backtracking function to find all possible paths
     * from top-left (0,0) to bottom-right (n-1,n-1).
     *
     * @param temp    -> current path string (sequence of moves)
     * @param maze    -> maze matrix (1 = open, 0 = blocked)
     * @param n       -> size of maze (n x n)
     * @param i       -> current row position of the rat
     * @param j       -> current column position of the rat
     * @param ans     -> list storing all valid paths
     * @param visited -> matrix to track visited cells
     */
    public static void solve(
            String temp,
            int[][] maze,
            int n,
            int i,
            int j,
            List<String> ans,
            int[][] visited) {

        /*
         * BASE CASE:
         * If the rat reaches the destination cell,
         * store the path and return.
         */
        if (i == n - 1 && j == n - 1) {
            ans.add(temp);
            return;
        }

        /*
         * Mark the current cell as visited
         * (to avoid cycles).
         */
        visited[i][j] = 1;

        // TRY ALL 4 DIRECTIONS

        // Move Down (D)
        if (isSafe(i + 1, j, n, visited, maze)) {
            solve(temp + 'D', maze, n, i + 1, j, ans, visited);
        }

        // Move Left (L)
        if (isSafe(i, j - 1, n, visited, maze)) {
            solve(temp + 'L', maze, n, i, j - 1, ans, visited);
        }

        // Move Right (R)
        if (isSafe(i, j + 1, n, visited, maze)) {
            solve(temp + 'R', maze, n, i, j + 1, ans, visited);
        }

        // Move Up (U)
        if (isSafe(i - 1, j, n, visited, maze)) {
            solve(temp + 'U', maze, n, i - 1, j, ans, visited);
        }

        /*
         * BACKTRACKING:
         * Unmark the current cell before returning
         * so it can be used in other paths.
         */
        visited[i][j] = 0;
    }

    public static List<String> solveMaze(int[][] maze, int n) {

        List<String> ans = new ArrayList<>();
        int[][] visited = new int[n][n];

        // Start only if the starting cell is open
        if (maze[0][0] == 1) {
            solve("", maze, n, 0, 0, ans, visited);
        }

        return ans;
    }

    public static void main(String[] args) {

        int[][] maze = {
                { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 0, 0 },
                { 0, 1, 1, 1 },
        };

        int n = maze.length;

        System.out.println(solveMaze(maze, n));
    }
}
