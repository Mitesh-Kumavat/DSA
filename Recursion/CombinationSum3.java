package Recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {

    /**
     * Recursive function to generate combinations of size 'k'
     * whose sum equals 'targetSum'.
     *
     * @param index     -> current index in nums array (1 to 9)
     * @param nums      -> array containing numbers 1 to 9
     * @param temp      -> current combination being built
     * @param ans       -> list of all valid combinations
     * @param targetSum -> remaining sum needed
     * @param k         -> remaining numbers to pick
     */
    public static void findCombinations(
            int index,
            int[] nums,
            List<Integer> temp,
            List<List<Integer>> ans,
            int targetSum,
            int k) {

        /*
         * BASE CASE 1:
         * If we have picked exactly k numbers
         */
        if (k == 0) {
            // If the picked numbers sum up to targetSum,
            // then this is a valid combination
            if (targetSum == 0) {
                ans.add(new ArrayList<>(temp));
            }
            return; // stop recursion
        }

        /*
         * BASE CASE 2:
         * If we have exhausted all numbers (1 to 9)
         */
        if (index == nums.length) {
            return;
        }

        // PICK THE CURRENT NUMBER
        temp.add(nums[index]);

        // Recurse:
        // - move to next index
        // - reduce targetSum by chosen number
        // - reduce k since one number is picked
        findCombinations(index + 1, nums, temp, ans, targetSum - nums[index], k - 1);

        // Backtrack: remove the last picked number
        temp.remove(temp.size() - 1);

        // NOT PICK THE CURRENT NUMBER

        findCombinations(index + 1, nums, temp, ans, targetSum, k);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {

        int arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        List<List<Integer>> ans = new ArrayList<>();

        findCombinations(0, arr, new ArrayList<>(), ans, n, k);

        return ans;
    }

    public static void main(String[] args) {
        CombinationSum3 cs3 = new CombinationSum3();

        // Example:
        // k = 3 numbers
        // sum = 7
        // Expected combination: [1, 2, 4]
        List<List<Integer>> result = cs3.combinationSum3(3, 7);
        System.out.println(result);
    }
}
