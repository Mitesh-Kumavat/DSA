package Recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum1 {

    /*
     * BACKTRACKING FUNCTION
     *
     * ans -> final list that stores all valid combinations
     * index -> current index in the candidates array
     * arr -> candidates array
     * target -> remaining target sum to achieve
     * temp -> current combination being built
     *
     * Key Rule:
     * - Each number can be used UNLIMITED times
     * - Order does NOT matter (so we control index movement)
     */
    public static void backtrack(
            List<List<Integer>> ans,
            int index,
            int[] arr,
            int target,
            ArrayList<Integer> temp) {

        /*
         * BASE CASE:
         * If we have considered all elements
         */
        if (index == arr.length) {

            // If target becomes 0, we found a valid combination
            if (target == 0) {
                ans.add(new ArrayList<>(temp)); // deep copy
            }

            return; // stop recursion
        }

        /*
         * CHOICE 1: PICK the current element
         *
         * Conditions:
         * - Only pick if current element <= remaining target
         * - Since we can reuse the same element,
         * we DO NOT move to next index
         */
        if (arr[index] <= target) {
            temp.add(arr[index]); // choose
            backtrack(ans, index, arr, target - arr[index], temp);
            temp.remove(temp.size() - 1); // backtrack
        }

        /*
         * CHOICE 2: NOT PICK the current element
         *
         * - Move to the next index
         * - Ensures combinations are unique
         */
        backtrack(ans, index + 1, arr, target, temp);
    }

    /*
     * MAIN FUNCTION
     *
     * Initializes result list and starts recursion
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        backtrack(ans, 0, candidates, target, new ArrayList<>());

        return ans;
    }

    public static void main(String[] args) {
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;

        System.out.println(combinationSum(candidates, target));
    }
}
