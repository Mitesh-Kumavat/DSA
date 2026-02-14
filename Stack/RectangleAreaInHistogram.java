package Stack;

import java.util.Stack;

public class RectangleAreaInHistogram {

    /*
     * NSE = Next Smaller Element index
     *
     * For each index i,
     * find first smaller element on RIGHT.
     *
     * If none exists → return n (array length).
     */
    public static int[] NSE(int[] arr) {

        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {

            // Remove all bars taller or equal to current
            // because they cannot be next smaller
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            // If stack empty → no smaller on right
            ans[i] = st.isEmpty() ? n : st.peek();

            st.push(i);
        }

        return ans;
    }

    /*
     * PSE = Previous Smaller Element index
     *
     * For each index i,
     * find first smaller element on LEFT.
     *
     * If none exists → return -1.
     */
    public static int[] PSE(int[] arr) {

        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from left to right
        for (int i = 0; i < n; i++) {

            // Remove all bars taller than current
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            ans[i] = st.isEmpty() ? -1 : st.peek();

            st.push(i);
        }

        return ans;
    }

    /*
     * METHOD 1: Using PSE + NSE arrays
     *
     * For each index:
     * width = nse[i] - pse[i] - 1
     * area = height[i] * width
     */
    public static int largestRectangleAreaBrute(int[] heights) {

        int[] nse = NSE(heights);
        int[] pse = PSE(heights);

        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {

            int width = nse[i] - pse[i] - 1;
            int area = heights[i] * width;

            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    /*
     * METHOD 2: Single Pass Optimized Stack Approach
     *
     * INTUITION
     * For every bar, we want to know:
     *
     * 1) How far can this bar extend to the LEFT?
     * 2) How far can this bar extend to the RIGHT?
     *
     * The bar can extend:
     * ➜ Until we find a smaller bar.
     *
     * So for every bar:
     * width = (next smaller element index - previous smaller element index - 1)
     *
     * Instead of precomputing NSE and PSE separately,
     * we calculate area IMMEDIATELY when a bar cannot extend further.
     *
     * WHY STACK?
     * We maintain an INCREASING stack of indices.
     *
     * Stack property:
     * heights[stack[0]] < heights[stack[1]] < heights[stack[2]] ...
     *
     * This ensures:
     * - As long as heights are increasing, we just push.
     * - When a smaller height appears, we start resolving areas.
     *
     * WHY THIS WORKS IN SINGLE PASS?
     * Because:
     * - Each index is pushed ONCE.
     * - Each index is popped ONCE.
     * So total operations = O(2n) = O(n)
     *
     */

    public static int largestRectangleArea(int[] heights) {

        int n = heights.length;
        int maxArea = 0;

        // Stack will store INDICES (not heights)
        // Why indices?
        // Because we need to calculate width using index positions.
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {

            /*
             * IMPORTANT LOGIC:
             *
             * If current height is SMALLER than the height
             * at stack top, then stack top bar cannot extend further.
             *
             * Why?
             * Because we found a smaller bar on the RIGHT.
             *
             * So we start calculating area for those taller bars.
             */
            while (!st.isEmpty() && heights[st.peek()] > heights[i]) {

                // This is the bar for which we calculate area
                int heightIndex = st.pop();

                /*
                 * RIGHT BOUNDARY:
                 * Current index 'i' is the first smaller element on right.
                 */
                int rightBoundary = i;

                /*
                 * LEFT BOUNDARY:
                 * After popping, the new stack top is previous smaller element.
                 * If stack is empty → no smaller element on left.
                 */
                int leftBoundary = st.isEmpty() ? -1 : st.peek();

                /*
                 * WIDTH CALCULATION:
                 *
                 * width = rightBoundary - leftBoundary - 1
                 *
                 * Why -1?
                 * Because boundaries themselves are smaller elements,
                 * and rectangle extends BETWEEN them.
                 */
                int width = rightBoundary - leftBoundary - 1;

                /*
                 * AREA = height * width
                 */
                maxArea = Math.max(maxArea, heights[heightIndex] * width);
            }

            /*
             * If current bar is greater than stack top,
             * it means it might extend further.
             * So we push it.
             */
            st.push(i);
        }

        /*
         * FINAL STEP:
         *
         * Some bars might never get a smaller element on right.
         *
         * Example:
         * Increasing array like [1,2,3,4]
         *
         * For them:
         * Right boundary = n (end of array)
         */
        while (!st.isEmpty()) {

            int heightIndex = st.pop();

            int rightBoundary = n; // No smaller element on right
            int leftBoundary = st.isEmpty() ? -1 : st.peek();

            int width = rightBoundary - leftBoundary - 1;

            maxArea = Math.max(maxArea, heights[heightIndex] * width);
        }

        return maxArea;
    }

    public static void main(String[] args) {

        int[] heights = { 2, 1, 5, 6, 2, 3 };

        System.out.println(largestRectangleArea(heights));
    }
}