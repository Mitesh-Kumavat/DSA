package BinarySearch;

public class SearchIn2DMatrix2 {

    /*
     * PROBLEM:
     * You are given a 2D matrix where:
     * 1) Every row is sorted in increasing order.
     * 2) Every column is also sorted in increasing order.
     *
     * TASK:
     * Check whether a given target value exists in the matrix.
     * Return true if found, otherwise false.
     */

    // Better Approach: Apply Binary Search on each row
    /*
     * IDEA:
     * Since each row is already sorted, we can apply binary search individually
     * on every row.
     *
     * PROCESS:
     * - Iterate every row.
     * - Apply binary search on that row.
     * - If target found → return true.
     *
     * TC: O(n * log m) → because we run binary search for each of n rows
     * SC: O(1) → no extra space used
     */
    public static boolean search2DArrBetter(int[][] arr, int target) {
        int n = arr.length;
        int m = arr[0].length;

        for (int i = 0; i < n; i++) {
            int low = 0, high = m - 1;

            // binary search on the current row
            while (low <= high) {
                int mid = low + (high - low) / 2;
                
                if (arr[i][mid] == target) {
                    return true;
                } else if (arr[i][mid] < target) {
                    low = mid + 1; // move right
                } else {
                    high = mid - 1; // move left
                }
            }
        }

        return false; // target not found in any row
    }

    // Optimal Approach: Start from top-right corner
    /*
     * IDEA:
     * The matrix is sorted row-wise AND column-wise.
     * So a powerful trick is:
     * Start from the top-right corner.
     *
     * WHY TOP-RIGHT?
     * - It gives a “decision point”:
     * If arr[row][col] > target → move LEFT (because left is smaller)
     * If arr[row][col] < target → move DOWN (because down is bigger)
     *
     * So in one step, you eliminate an entire row or column.
     *
     * TC: O(n + m) → worst case we move at most n steps down + m steps left
     * SC: O(1)
     */
    public static boolean search2DArrOptimal(int[][] arr, int target) {

        int n = arr.length;
        int m = arr[0].length;
        int row = 0; // start at first row
        int col = m - 1; // start at last column (top-right)

        while (row < n && col >= 0) {

            if (arr[row][col] == target) {
                return true; // found the target
            } else if (arr[row][col] < target) {
                row++; // move down → values increase
            } else {
                col--; // move left → values decrease
            }
        }

        return false; // target not present
    }

    public static void main(String[] args) {
        int[][] arr = {
                { 1, 4, 7, 11, 15 },
                { 2, 5, 8, 12, 19 },
                { 3, 6, 9, 16, 22 },
                { 10, 13, 14, 17, 24 },
                { 18, 21, 23, 26, 30 }
        };

        int target = 5;
        int target2 = 20;

        System.out.println(search2DArrOptimal(arr, target)); // true
        System.out.println(search2DArrBetter(arr, target2)); // false
    }
}
