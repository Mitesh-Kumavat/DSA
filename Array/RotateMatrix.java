package Array;

import java.util.Arrays;

public class RotateMatrix {
    // Brute force: TC -> O(N^2) , SC -> O(N^2)
    // We will create another ans matrix
    // and pick up every element from the given matrix and place it
    // to its correct position in ans matrix
    // and to find where to place just observer by writing some i and j index
    public static int[][] rotateMat(int[][] arr) {
        int[][] ans = new int[arr.length][arr[0].length];
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans.length; j++) {
                ans[j][arr.length - 1 - i] = arr[i][j];
                // to find this relation you have to write every case
                // and observe the relation you will know
            }
        }
        return ans;
    }

    // Optimal Approach: TC -> O(M*N/2) + O(N*N/2) , SC -> O(1)
    // First we transpose the matrix and reverse every row
    // Rotated matrix = (transpose + reverse every row)
    // How to transpose -> Observe carefully
    // the diagonal element remains the same means arr[1][1] , arr[0][0] ...
    // and the remaining element get swapped like -> swap(arr[0][1], arr[1][0])
    // and now you do how to reverse the every row and done .
    public static void rotateMatrix(int[][] arr) {

        // lets transpose
        // just traverse for the upper right traingle
        // so the loops only run for that part only
        // because if we traverse entire array it will become org matrix

        // TC: O(N/2 * M/2) -> This will just run for upper traingle
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr[i].length; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }

        // now lets reverse the row , TC: O(N * N/2 (reversing))
        for (int i = 0; i < arr.length; i++) {
            reverse(arr[i], 0, arr.length - 1);
        }

    }

    public static void reverse(int[] arr, int start, int end) {
        while (start <= end) {
            int temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
        int[][] ans = rotateMat(matrix);

        // Brute force
        for (int[] is : matrix) {
            System.out.println(Arrays.toString(is));
        }
        System.out.println();
        for (int[] is : ans) {
            System.out.println(Arrays.toString(is));
        }

        System.out.println("Optimal Approach");
        int[][] matrix2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        rotateMatrix(matrix2);
        for (int[] is : matrix2) {
            System.out.println(Arrays.toString(is));
        }

    }
}
