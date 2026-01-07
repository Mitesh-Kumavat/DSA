package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset2 {

    /**
     * Recursive function to generate all unique subsets.
     *
     * @param index -> current index from where we can choose elements
     * @param nums  -> input array (already sorted to handle duplicates)
     * @param temp  -> current subset being built
     * @param ans   -> final list of all unique subsets
     */
    public static void findSubSets(int index, int[] nums, List<Integer> temp, List<List<Integer>> ans) {

        // Every recursive call represents a valid subset
        // So we add a COPY of temp to the answer list
        ans.add(new ArrayList<>(temp));

        // Try picking elements starting from 'index'
        for (int i = index; i < nums.length; i++) {

            /*
             * Skip duplicates:
             * - If i > index and nums[i] == nums[i - 1],
             * then this element would create the same subset
             * as the previous one at the same recursion level.
             *
             * This is the KEY LINE that avoids duplicate subsets.
             */
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }

            // Choose the current element
            temp.add(nums[i]);

            // Recurse to build further subsets using next index
            findSubSets(i + 1, nums, temp, ans);

            // Backtrack:
            // Remove the last added element to try other possibilities
            temp.remove(temp.size() - 1);
        }
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {

        // Sort the array so that duplicates come together
        // This is REQUIRED for the duplicate-skip logic to work
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        findSubSets(0, nums, new ArrayList<>(), ans);

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 2 };

        // Output will contain only unique subsets
        System.out.println(subsetsWithDup(nums));
    }
}
