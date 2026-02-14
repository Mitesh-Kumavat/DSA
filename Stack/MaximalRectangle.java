package Stack;

import java.util.Stack;

public class MaximalRectangle {

    public static int largestRectangleArea(int[] heights) {
        int ans = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            while (!st.isEmpty() && heights[st.peek()] > heights[i]) {
                int el = st.pop();
                int nse = i;
                int pse = st.isEmpty() ? -1 : st.peek();
                ans = Math.max(ans, (nse - pse - 1) * heights[el]);
            }

            st.push(i);
        }

        while (!st.isEmpty()) {
            int nse = heights.length;
            int el = st.pop();
            int pse = st.isEmpty() ? -1 : st.peek();
            ans = Math.max(ans, (nse - pse - 1) * heights[el]);
        }

        return ans;
    }

    /*
     * We are given a matrix of '0' and '1'.
     * We must find the largest rectangle containing only '1'.
     *
     * Convert each row into a histogram.
     *
     * How?
     * Instead of thinking in 2D,
     * we build height of consecutive 1's column-wise.
     *
     * Example:
     *
     * Matrix:
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     *
     * After row 0 → heights = [1,0,1,0,0]
     * After row 1 → heights = [2,0,2,1,1]
     * After row 2 → heights = [3,1,3,2,2]
     * After row 3 → heights = [4,0,0,3,0]
     *
     * Each row becomes a HISTOGRAM problem.
     *
     * So at every row:
     * 1 Update heights
     * 2 Calculate largest histogram area
     * 3 Keep track of global maximum
     *
     * A rectangle in matrix = consecutive 1's vertically and horizontally.
     *
     * By building heights:
     * - heights[j] tells how many continuous 1's are stacked vertically.
     *
     * So rectangle area becomes:
     * height × width
     *
     * Which is exactly the histogram problem.
     *
     */

    public static int maximalRectangle(char[][] matrix) {

        int maxArea = 0;

        // This array stores histogram heights for current row
        int[] heights = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {

            /*
             * STEP 1: Update histogram heights
             *
             * If current cell is '1':
             * Increase height (extend vertical stack)
             *
             * If current cell is '0':
             * Reset height to 0 (rectangle breaks here)
             */
            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }

            /*
             * STEP 2: Treat updated heights as histogram
             */
            int currentMax = largestRectangleArea(heights);

            /*
             * STEP 3: Update global maximum
             */
            maxArea = Math.max(maxArea, currentMax);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                { '1', '0', '1', '0', '0' },
                { '1', '0', '1', '1', '1' },
                { '1', '1', '1', '1', '1' },
                { '1', '0', '0', '1', '0' }
        };

        System.out.println(maximalRectangle(matrix));
    }
}
