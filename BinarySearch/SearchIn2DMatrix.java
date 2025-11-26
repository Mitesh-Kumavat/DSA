package BinarySearch;

public class SearchIn2DMatrix {

    /**
     * Better Approach:
     * ---------------
     * Apply binary search on every row individually.
     * 
     * Logic:
     * 1. Since rows are sorted, the element must lie within the range of that row
     * → check if (arr[i][0] <= target <= arr[i][m]).
     * 2. If yes → apply binary search on that row.
     * 
     * Time Complexity: O(n * log m)
     * Space Complexity: O(1)
     */
    public static boolean searchMatrix(int[][] arr, int target) {
        int n = arr.length;
        int m = arr[0].length - 1;

        for (int i = 0; i < n; i++) {

            // Check if target MAY lie in this row
            if (arr[i][0] <= target && arr[i][m] >= target) {

                int low = 0;
                int high = m;

                // Binary search within this row
                while (low <= high) {
                    int mid = low + (high - low) / 2;

                    if (arr[i][mid] == target) {
                        return true;
                    } else if (arr[i][mid] > target) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
            }
        }
        return false; // Not found in any row
    }

    /**
     * Optimal Approach:
     * -----------------
     * Since the matrix is sorted in a special way:
     * - Each row is sorted
     * - First element of every row is > last element of previous row
     * 
     * This means the matrix behaves like a sorted 1D array.
     * We have to Flatten the 2D matrix into 1D (Hypothetically)
     * 
     * Flattened indexing:
     * midIndex → corresponds to matrix[midIndex / m][midIndex % m]
     * 
     * Perform ONE binary search on the entire 2D matrix.
     * 
     * Time Complexity: O(log (n*m)) → BEST possible
     * Space Complexity: O(1)
     */
    public static boolean searchMatrixOptimal(int[][] arr, int target) {
        int n = arr.length;
        int m = arr[0].length;

        int low = 0;
        int high = n * m - 1; // treat whole matrix as 1D array

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int row = mid / m; // convert 1D index → row
            int col = mid % m; // convert 1D index → col

            int value = arr[row][col];

            if (value == target) {
                return true;
            } else if (value < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 60 }
        };

        int target = 3;

        // Using better approach
        boolean result1 = searchMatrix(matrix, target);
        System.out.println("Better Approach → Found: " + result1);

        // Using optimal approach
        boolean result2 = searchMatrixOptimal(matrix, target);
        System.out.println("Optimal Approach → Found: " + result2);
    }
}
