package BinarySearch;

public class FindRowWithMaxOnes {

    public static int rowWithMaxOneBrute(int[][] matrix) {

        int maxOnes = 0;
        int indexOfMaxOnes = -1;

        for (int i = 0; i < matrix.length; i++) {
            int cntOf1 = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    cntOf1++;

                    if (cntOf1 > maxOnes) {
                        maxOnes = cntOf1;
                        indexOfMaxOnes = i;
                    }
                }
            }
        }

        return indexOfMaxOnes;
    }

    public static int rowWithMaxOneOptimal(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int maxOnes = 0;
        int rowIndex = -1;

        for (int i = 0; i < n; i++) {
            int low = 0;
            int high = m - 1;
            int firstOne = -1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (matrix[i][mid] == 1) {
                    firstOne = mid;
                    high = mid - 1; // move left
                } else {
                    low = mid + 1; // move right
                }
            }

            if (firstOne != -1) {
                int onesCount = m - firstOne;
                if (onesCount > maxOnes) {
                    maxOnes = onesCount;
                    rowIndex = i; // store row index (correct)
                }
            }
        }

        return rowIndex;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 0, 0, 1, 1 },
                { 0, 1, 1, 1 },
                { 0, 0, 1, 1 },
                { 1, 1, 1, 1 }
        };
        System.out.println(rowWithMaxOneOptimal(matrix));
    }
}
