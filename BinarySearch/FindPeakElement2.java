package BinarySearch;

public class FindPeakElement2 {

    /*
     * PROBLEM:
     * We are given a 2D matrix where we must find a "peak element".
     * A peak element is defined as:
     * arr[row][col] > arr[row][col - 1] AND arr[row][col] > arr[row][col + 1]
     *
     * Note: We only check left and right because the algorithm is column-based.
     *
     * Goal → return the index {row, col} of any one peak.
     * If no peak found → return {-1, -1}.
     */

    /*
     * IDEA:
     * For a specific column 'col', we scan all rows and pick the maximum value.
     * This row is important because a peak can only exist at the maximum element
     * of that column (since it must be larger than neighbors).
     *
     * FIX:
     * index MUST start from 0, otherwise row 0 case breaks and returns -1.
     */
    public static int maxColEmenet(int[][] arr, int col) {
        int max = arr[0][col];
        int index = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i][col] > max) {
                max = arr[i][col];
                index = i;
            }
        }

        return index;
    }

    // Optimal Solution Using Binary Search on Columns
    /*
     * MAIN IDEA:
     * We apply binary search on COLUMNS (not rows).
     *
     * STEPS:
     * 1) Take mid column.
     * 2) Find the row containing the maximum element in this mid column.
     * 3) Compare arr[row][mid] with its LEFT and RIGHT neighbors.
     *
     * Conditions:
     * If arr[row][mid] > left AND arr[row][mid] > right → peak found.
     * Else if left > arr[row][mid] → peak lies on LEFT side → move high = mid - 1
     * Else → peak lies on RIGHT side → move low = mid + 1
     *
     * WHY IT WORKS:
     * Because if a neighbor is bigger, the peak must lie in that direction.
     *
     * TIME COMPLEXITY:
     * O(n * log m)
     * (finding max in column = O(n), binary search on columns = log m)
     *
     * SPACE COMPLEXITY: O(1)
     */
    public static int[] findPeakElement(int[][] arr) {

        int n = arr.length;
        int m = arr[0].length;

        int low = 0;
        int high = m - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // get row index of maximum element of this mid column
            int row = maxColEmenet(arr, mid);

            // neighbors
            int left = (mid - 1 >= 0) ? arr[row][mid - 1] : -1;
            int right = (mid + 1 < m) ? arr[row][mid + 1] : -1;

            // PEAK CONDITION
            if (arr[row][mid] > left && arr[row][mid] > right) {
                return new int[] { row, mid };
            }

            // move to the side with bigger neighbor
            else if (arr[row][mid] < left) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        // no peak found
        return new int[] { -1, -1 };
    }

    // Brute Force Approach
    /*
     * Check each element and compare left & right.
     *
     * TIME COMPLEXITY: O(n * m)
     * SPACE COMPLEXITY: O(1)
     *
     */
    public static int[] findPeakBetter(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {

                int curr = arr[r][c];
                int left = (c - 1 >= 0) ? arr[r][c - 1] : -1;
                int right = (c + 1 < m) ? arr[r][c + 1] : -1;

                if (curr > left && curr > right) {
                    return new int[] { r, c };
                }
            }
        }

        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        int[][] arr = {
                { 70, 50, 40, 30, 20 },
                { 100, 1, 2, 3, 4 }
        };

        int[] result1 = findPeakElement(arr);
        System.out.println("Peak Element found at: [" + result1[0] + ", " + result1[1] + "]");
    }
}
