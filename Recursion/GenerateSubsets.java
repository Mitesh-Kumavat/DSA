package Recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateSubsets {

    /*
     * BACKTRACKING FUNCTION
     *
     * current -> current subset being built
     * index -> current index in the original array
     * set -> original input array
     *
     * At every index, we have TWO choices:
     * 1) Exclude the element
     * 2) Include the element
     */
    public static void backtrack(List<List<Integer>> result,
            List<Integer> current,
            int index,
            int[] set) {

        // BASE CASE:
        // If we have processed all elements,
        // current now represents ONE valid subset
        if (index == set.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // CHOICE 1: Do NOT include set[index]
        // Just move to the next index
        backtrack(result, current, index + 1, set);

        // CHOICE 2: Include set[index]
        // Add it, move forward, then BACKTRACK
        current.add(set[index]); // choose
        backtrack(result, current, index + 1, set); // explore
        current.remove(current.size() - 1); // un-choose (BACKTRACK)
    }

    public static List<List<Integer>> generateSubsets(int[] set) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 0, set);
        return result;
    }

    public static void main(String[] args) {
        int[] set = { 1, 2, 3 };
        List<List<Integer>> subsets = generateSubsets(set);
        System.out.println(subsets);
    }
}
