package Array;

import java.util.Arrays;

public class repeatingAndMissing {

    // Brute force: pick up every element from the array and
    // loop again from 1 to N , and if the element exists two time this will be
    // repeating, and if it doesnt exist it will be missing
    public static int[] repeatingAndMissingBrute(int[] arr) {
        int[] ans = new int[2];
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int count = 0;

            for (int el : arr) {
                if (el == i) {
                    count++;
                }
            }

            if (count == 2) {
                ans[0] = i;
            } else if (count == 0) {
                ans[1] = i;
            }
        }

        return ans;
    }

    // Better Approach: using hash array
    // declare hash array of size n + 1, and set every element 0
    // now iterate through given array and mark that in hash array
    // increase the value of the index (increase the count of seen element)
    // now the repeated number will have count 2 , and missing number will have
    // count 0, so return them in array format
    // TC: O(2N), SC: O(N)
    public static int[] repeatingAndMissingBetter(int[] arr) {
        int[] hashArr = new int[arr.length + 1];
        int repeating = 0;
        int missing = 0;

        for (int i = 0; i < arr.length; i++) {
            hashArr[arr[i]] = hashArr[arr[i]] + 1;
        }

        System.out.println(Arrays.toString(hashArr));

        for (int i = 1; i < hashArr.length; i++) {
            if (hashArr[i] == 0) {
                missing = i;
            }

            if (hashArr[i] == 2) {
                repeating = i;
            }
        }

        return new int[] { repeating, missing };
    }

    // Optimal Approach: using mathametical formula
    public static int[] repeatingAndMissingOptimal(int[] a) {
        long n = a.length; // size of the array

        // Find Sn and S2n:
        long SN = (n * (n + 1)) / 2; // sum of first N natural numbers
        long S2N = (n * (n + 1) * (2 * n + 1)) / 6; // sum of square of first N natural numbers

        // Calculate S and S2:
        long S = 0; // calculate the sum of numbers from given array
        long S2 = 0;// calculate the sum of square of numbers from given array

        for (int i = 0; i < n; i++) {
            S += a[i];
            S2 += (long) a[i] * (long) a[i];
        }

        // S-Sn = X-Y:
        long val1 = S - SN;

        // S2-S2n = X^2-Y^2: => (X+Y)(X-Y)
        long val2 = S2 - S2N;

        // Find X+Y = (X^2-Y^2)/(X-Y):
        val2 = val2 / val1;

        // Find X and Y: X = ((X+Y)+(X-Y))/2 and Y = X-(X-Y),
        // Here, X-Y = val1 and X+Y = val2:
        long x = (val1 + val2) / 2;
        long y = x - val1;

        int[] ans = { (int) x, (int) y };
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 1, 2, 5, 3 };
        System.out.println(Arrays.toString(repeatingAndMissingBetter(arr)));
    }
}
