package Array;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    // For this problem there is only one optimal sol exist
    // No brute force, no better, just the OPTIMAL only
    // so the spiral printing has some pattern which is like this
    // Left to Right -> Top to Bottom -> Right to left -> Bottom to top
    // so make 4 variables which will help us to get the elements in desired pattern
    public static List<Integer> spiralMatrix(int[][] arr) {

        List<Integer> list = new ArrayList<>();

        int n = arr.length;
        int m = arr[0].length;
        int left = 0, top = 0;
        int right = m - 1, bottom = n - 1;
        // Remember: Left to Right -> Top to Bottom -> Right to left -> Bottom to top

        while (top <= bottom && left <= right) {
            // Left to Right
            for (int i = left; i <= right; i++) {
                list.add(arr[top][i]);
            }
            top++;

            // Top to bottom
            for (int i = top; i <= bottom; i++) {
                list.add(arr[i][right]);
            }
            right--;

            // Right to left
            if (top <= bottom) { // this condition is neccary for this kind of array : [1,2,3,4] 1D array
                for (int i = right; i >= left; i--) {
                    list.add(arr[bottom][i]);
                }
                bottom--;
            }

            // Bottom to Top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    list.add(arr[i][left]);
                }
                left++;
            }
        }

        return list;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        List<Integer> ans = spiralMatrix(matrix);

        System.out.println(ans);

    }
}
