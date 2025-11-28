package BinarySearch;

import java.util.Arrays;

public class FindMedian2DArr {

    public static int findMedianBrute(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int totalElements = rows * cols;
        int[] arr = new int[totalElements];
        int index = 0;

        // Flatten the 2D matrix into a 1D array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[index++] = matrix[i][j];
            }
        }

        // Sort the 1D array
        Arrays.sort(arr);

        // Find and return the median
        if (totalElements % 2 == 1) {
            return arr[totalElements / 2];
        } else {
            return (arr[(totalElements / 2) - 1] + arr[totalElements / 2]) / 2;
        }
    }

    public static int findMedianOptimal(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Find the minimum and maximum elements in the matrix
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] < min) {
                min = matrix[i][0];
            }
            if (matrix[i][cols - 1] > max) {
                max = matrix[i][cols - 1];
            }
        }

        int desiredCount = (rows * cols + 1) / 2;

        // Binary search between min and max
        while (min < max) {
            int mid = min + (max - min) / 2;
            int place = 0;

            for (int i = 0; i < rows; i++) {
                place += countLessEqual(matrix[i], mid);
            }

            if (place < desiredCount) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return min;
    }

    private static int countLessEqual(int[] row, int target) {
        int low = 0;
        int high = row.length;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (row[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 3, 5 },
                { 2, 6, 9 },
                { 3, 6, 9 }
        };

        int median = findMedianBrute(matrix);
        System.out.println("Median of the 2D row-wise sorted array is: " + median);
    }
}
