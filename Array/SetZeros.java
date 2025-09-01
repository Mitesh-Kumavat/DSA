package Array;

public class SetZeros {

    // Brute force :
    // when we will find the 0 we will make its rows and cols element to -1
    // but 0 remains same we dont change it to -1
    // so now whoever elements are -1 we will change them to 0
    // Deep Explaination: https://takeuforward.org/data-structure/set-matrix-zero/

    // Better Approach:
    // TC: O(2*(N*M)) , SC: O(N + M)
    public static void setZeros(int[][] arr) {

        int[] rows = new int[arr.length];
        int[] cols = new int[arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 0) {
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (rows[i] == 1 || cols[j] == 1) {
                    arr[i][j] = 0;
                }
            }
        }
    }

    // Optimal Approach:
    public static void setZerosOptimal(int[][] arr) {

        // int[] col = new int[arr[0].length]; this is -> arr[0][..]
        // int[] row = new int[arr.length]; this is -> arr[..][0]

        // This will mark the row and col index if its contains any 0 so that we can
        // covert that entire row and col to 0

        int col0 = 1; // this is to handle the special case arr[0][0]
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 0) {
                    // mark the i-th row and j-th col
                    arr[i][0] = 0; // marked i-th row
                    if (j != 0) {
                        arr[0][j] = 0; // marked j-th col
                    } else {
                        col0 = 0;
                    }
                }
            }
        }

        // now just make the elements to 0
        // but do not touch our marked row and col which is : arr[0][..], arr[..][0]
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    // check for col and row if they are marked then set element to 0
                    if (arr[0][j] == 0 || arr[i][0] == 0) {
                        arr[i][j] = 0;
                    }
                }
            }
        }

        // if the first element is 0 then change the first row to 0
        if (arr[0][0] == 0) {
            for (int i = 0; i < arr[0].length; i++) {
                arr[0][i] = 0;
            }
        }

        // if the first element of our special case is 0
        // then mark the entire col to 0

        if (col0 == 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i][0] = 0;
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
        setZerosOptimal(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
