package BitManipulation;

import java.util.ArrayList;
import java.util.List;

/*
    PROBLEM: Generate Power Set (All Subsets) using Bit Manipulation

    Given an integer array nums, we need to generate all possible subsets.
    Total subsets for an array of size n = 2^n

    Example:
    nums = [1, 2, 3]
    n = 3
    Total subsets = 2^3 = 8

    We use numbers from 0 to (2^n - 1) and treat them as bitmasks.
    Each bit decides whether an element is included or not.
*/

public class PowerSet {

    public static List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;

        /*
         * Total number of subsets = 2^n
         * Left shift 1 by n positions gives 2^n
         * 
         * Example:
         * n = 3
         * 1 << 3 = 8 (1000 in binary)
         */
        int totalSubsets = 1 << n;

        /*
         * Outer loop runs from 0 to (2^n - 1)
         * Each number represents a unique subset using its binary form
         */
        for (int mask = 0; mask < totalSubsets; mask++) {

            // Temporary list to store the current subset
            List<Integer> subset = new ArrayList<>();

            /*
             * Check each bit position from 0 to n-1
             * If the ith bit of mask is set, include nums[i]
             */
            for (int i = 0; i < n; i++) {

                /*
                 * (1 << i) creates a number with only ith bit set
                 * 
                 * mask & (1 << i):
                 * - If result != 0 → ith bit is ON
                 * - If result == 0 → ith bit is OFF
                 */
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }

            // Add the generated subset to the answer list
            ans.add(subset);
        }

        // Return all subsets
        return ans;
    }

    public static void main(String[] args) {

        int[] nums = { 1, 2, 3 };

        List<List<Integer>> result = subsets(nums);

        System.out.println(result);
    }
}

/*
 * DRY RUN (nums = [1, 2, 3])
 * 
 * n = 3
 * totalSubsets = 1 << 3 = 8
 * 
 * mask | binary | subset formed
 * --------------------------------
 * 0 | 000 | []
 * 1 | 001 | [1]
 * 2 | 010 | [2]
 * 3 | 011 | [1, 2]
 * 4 | 100 | [3]
 * 5 | 101 | [1, 3]
 * 6 | 110 | [2, 3]
 * 7 | 111 | [1, 2, 3]
 * 
 * - Total subsets = 2^n
 * - Use numbers from 0 to (2^n - 1) as bitmasks
 * - ith bit ON → include nums[i]
 * - Bit manipulation avoids recursion
 */
