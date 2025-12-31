package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {

    /*
     * INTUITION (Very Important):
     *
     * - Each number can be used ONLY ONCE
     * - The array may contain duplicates
     * - The result must NOT contain duplicate combinations
     *
     * KEY STRATEGY:
     * 1. Sort the array (mandatory for duplicate handling)
     * 2. Use a for-loop instead of pick / not-pick recursion
     * 3. Skip duplicate elements at the SAME recursion level
     * 4. Move index forward (i + 1) because elements cannot be reused
     */

    /*
     * BACKTRACKING FUNCTION
     *
     * index -> starting index for current recursion
     * arr -> sorted candidates array
     * target -> remaining sum needed
     * ans -> final list of all valid combinations
     * temp -> current combination being built
     */
    public static void findCombinations(
            int index,
            int[] arr,
            int target,
            List<List<Integer>> ans,
            List<Integer> temp) {

        /*
         * BASE CASE:
         * If target becomes 0,
         * current combination is valid
         */
        if (target == 0) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        /*
         * LOOP over choices starting from index
         *
         * Why loop?
         * - To try all possible candidates at this level
         * - Each recursion level decides the NEXT number
         */
        for (int i = index; i < arr.length; i++) {

            /*
             * DUPLICATE SKIP CONDITION (MOST IMPORTANT PART)
             *
             * If current element is same as previous element
             * AND we are at the same recursion level,
             * skip it to avoid duplicate combinations
             *
             * Example:
             * [1, 1, 2]
             * If first 1 is chosen at index 0,
             * we must skip second 1 at index 1 for the same level
             */
            if (i > index && arr[i] == arr[i - 1]) {
                continue;
            }

            /*
             * PRUNING (Optimization)
             *
             * Since array is sorted,
             * if current element > target,
             * no further elements can fit
             */
            if (arr[i] > target) {
                break;
            }

            /*
             * CHOOSE current element
             */
            temp.add(arr[i]);

            /*
             * RECURSIVE CALL
             *
             * i + 1 → move forward because:
             * - each element can be used ONLY ONCE
             */
            findCombinations(i + 1, arr, target - arr[i], ans, temp);

            /*
             * BACKTRACK
             *
             * Remove last added element
             * to explore other possibilities
             */
            temp.remove(temp.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);

        findCombinations(0, candidates, target, ans, new ArrayList<>());

        return ans;
    }

    public static void main(String[] args) {
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;

        System.out.println(combinationSum(candidates, target));
    }
}
