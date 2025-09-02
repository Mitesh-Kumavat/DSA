package Array;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    // This question has 3 types of variation
    // Q 1: Given row number r and column number c. Print the element at
    // position (r, c) in Pascal’s triangle.
    // Q 2: Given the row number n. Print the n-th row of Pascal’s triangle.
    // These upper two problems uses NcR formula
    // (row number - 1) C (col number - 1) : (NcR)
    // to get the element at position at (R , C)

    // Q 3: Given the number of rows n. Print the first n rows of Pascal’s triangle.
    // You can find the first 2 problems here :
    // https://takeuforward.org/data-structure/program-to-generate-pascals-triangle/
    // We are just doing the 3rd because its on leetcode

    // This is also question no. 2
    public static List<Integer> generateRow(int rowNum) {
        List<Integer> row = new ArrayList<>();
        int result = 1;
        row.add(1); // store the 1 at the start of the list

        // now calculate every element and add it to the row list
        // For better understanding review the solution video :
        // https://www.youtube.com/watch?v=bR7mQgwQ_o8
        for (int i = 1; i < rowNum; i++) {
            result = result * (rowNum - i);
            result = result / i;
            row.add(result);
        }
        return row;
    }

    // Optimal
    public static List<List<Integer>> generatePascalTraingle(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        if (n <= 0) {
            return ans;
        }

        for (int i = 1; i <= n; i++) {
            ans.add(generateRow(i));
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> ans = generatePascalTraingle(2);

        System.out.println(ans);
    }
}
