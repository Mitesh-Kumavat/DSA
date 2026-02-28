package SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FruitIntoBasket {

    /*
     * PROBLEM:
     * You are given an array where each element represents a fruit type.
     * You have only 2 baskets and each basket can hold only one type of fruit.
     * You must pick fruits in a contiguous manner.
     *
     * Return the maximum number of fruits you can collect.
     *
     * This is equivalent to:
     * → Longest subarray containing at most 2 distinct integers.
     */

    /*
     * BRUTE FORCE APPROACH
     * Time Complexity: O(n^2)
     * Space Complexity: O(1) (at most 2 elements in set)
     *
     * Idea:
     * - Start from every index i.
     * - Expand j forward.
     * - Use a Set to track distinct fruits.
     * - If distinct fruits > 2 → break.
     * - Track max length.
     */
    public static int fruitsBrute(int[] arr) {
        int max = 0;

        for (int i = 0; i < arr.length; i++) {

            // Set to store distinct fruit types
            Set<Integer> set = new HashSet<>();

            for (int j = i; j < arr.length; j++) {

                set.add(arr[j]); // Add current fruit type

                // If more than 2 types → invalid window
                if (set.size() > 2) {
                    break;
                }

                // Valid window → update max length
                max = Math.max(max, j - i + 1);
            }
        }

        return max;
    }

    /*
     * BETTER APPROACH - SLIDING WINDOW (SHRINK WITH WHILE LOOP)
     * Time Complexity: O(2N) ≈ O(N)
     * Space Complexity: O(1) (map size at most 2)
     *
     * Idea:
     * - Use two pointers: l (left) and r (right)
     * - Expand r to grow window
     * - Use HashMap to store frequency of fruits
     * - If distinct fruits > 2 → shrink from left
     *
     * This is classic "Longest Subarray with At Most K Distinct Characters"
     */
    public static int fruitsInBasket(int[] arr) {
        int max = 0;

        int l = 0, r = 0; // sliding window pointers
        HashMap<Integer, Integer> map = new HashMap<>();

        while (r < arr.length) {

            // Add current fruit into map
            map.put(arr[r], map.getOrDefault(arr[r], 0) + 1);

            /*
             * If window becomes invalid (more than 2 types),
             * shrink window from left until valid again
             */
            while (map.size() > 2) {

                // Reduce frequency of left fruit
                map.put(arr[l], map.get(arr[l]) - 1);

                // If frequency becomes 0 → remove from map
                if (map.get(arr[l]) == 0) {
                    map.remove(arr[l]);
                }

                l++; // shrink window
            }

            // Window is valid → update max
            max = Math.max(max, r - l + 1);

            r++; // expand window
        }

        return max;
    }

    /*
     * OPTIMAL APPROACH - SMALL OPTIMIZATION
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     *
     * Difference:
     * Instead of shrinking completely using while loop,
     * we shrink only one step when size > 2.
     *
     * WHY DOES THIS WORK?
     * Because in sliding window,
     * each element is added once and removed once.
     *
     * Still overall O(N).
     */
    public static int fruitsInBasketOptimal(int[] arr) {
        int max = 0;

        int l = 0, r = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        while (r < arr.length) {

            // Add fruit at right pointer
            map.put(arr[r], map.getOrDefault(arr[r], 0) + 1);

            /*
             * If window invalid,
             * shrink only one position from left
             */
            if (map.size() > 2) {

                map.put(arr[l], map.get(arr[l]) - 1);

                if (map.get(arr[l]) == 0) {
                    map.remove(arr[l]);
                }

                l++;
            }

            // Update max length
            max = Math.max(max, r - l + 1);

            r++;
        }

        return max;
    }

    /*
     * DRY RUN EXAMPLE
     * arr2 = {3,3,3,1,2,1,1,2,3,3,4}
     *
     * Longest valid subarray:
     * {1,2,1,1,2} → length = 5
     *
     * Output → 5
     */

    public static void main(String[] args) {
        int[] arr = { 1, 1, 2 };
        int[] arr2 = { 3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4 };

        System.out.println(fruitsBrute(arr));
        System.out.println(fruitsInBasket(arr2));
        System.out.println(fruitsInBasketOptimal(arr2));
    }
}