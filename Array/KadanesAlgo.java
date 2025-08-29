// Leetcode : https://leetcode.com/problems/maximum-subarray/
package Array;

public class KadanesAlgo {

    // Optimal Approach
    // TC: O(N), SC: O(1)
    // Intiution: if the sum < 0 then we will re initialize it to 0
    // A subarray with a sum less than 0 will always reduce our answer and so this
    // type of subarray cannot be a part of the subarray with maximum sum.
    public static int kadanesAlgo(int[] arr) {
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (sum > max) {
                max = sum;
            }

            if (sum < 0) {
                sum = 0;
            }
        }

        return max;
    }

    // Follow up : print the longest subarray as well
    public static int kadanesAlgoWithArray(int[] arr) {
        int max = Integer.MIN_VALUE;
        int sum = 0;

        int start = 0, end = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (sum > max) {
                max = sum;
                end = i; // if the max is updated then this might be the longest array so update the end also
            }

            if (sum < 0) {
                sum = 0;
                start = i + 1; // from the next element our subarray will start
            }
        }

        for (int i = start; i <= end; i++) {
            System.out.println(arr[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(kadanesAlgoWithArray(arr));
    }
}
