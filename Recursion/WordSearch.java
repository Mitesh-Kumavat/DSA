package Recursion;

public class WordSearch {

    /**
     * DFS function to check whether the word can be formed
     * starting from cell (i, j).
     *
     * @param i     -> current row
     * @param j     -> current column
     * @param board -> 2D character grid
     * @param word  -> word to be searched
     * @param index -> current index in the word
     */
    public static boolean dfs(int i, int j, char[][] board, String word, int index) {

        /*
         * BASE CASE:
         * If we have matched all characters in the word,
         * return true.
         */
        if (index == word.length()) {
            return true;
        }

        /*
         * INVALID CASES:
         * - Out of grid bounds
         * - Character mismatch
         */
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }

        // Temporarily mark the current cell as visited
        char temp = board[i][j];
        board[i][j] = '#';

        /*
         * Explore all 4 possible directions:
         * Down, Right, Up, Left
         */
        boolean option1 = dfs(i + 1, j, board, word, index + 1);
        boolean option2 = dfs(i, j + 1, board, word, index + 1);
        boolean option3 = dfs(i - 1, j, board, word, index + 1);
        boolean option4 = dfs(i, j - 1, board, word, index + 1);

        // Backtrack:
        // Restore the original character
        board[i][j] = temp;

        // If any direction leads to a solution, return true
        return option1 || option2 || option3 || option4;
    }

    public static boolean exist(char[][] board, String word) {

        int row = board.length;
        int col = board[0].length;

        /*
         * Try to start DFS from every cell in the grid
         * that matches the first character of the word.
         */
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                // start DFS only if first character matches
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(i, j, board, word, 0)) {
                        return true;
                    }
                }
            }
        }

        // Word not found in any path
        return false;
    }

    public static void main(String[] args) {

        char[][] board = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };

        String word = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";

        System.out.println(exist(board, word)); // true
        System.out.println(exist(board, word2)); // true
        System.out.println(exist(board, word3)); // false
    }
}
