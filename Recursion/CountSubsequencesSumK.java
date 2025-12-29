package Recursion;

import java.util.ArrayList;

public class CountSubsequencesSumK {

    // Basic Idea:
    // For every element in the array, you have 2 choices:
    // Include the element
    // Exclude the element

    // This creates a binary recursion tree → total 2^n subsequences.

    // We track:
    // index → where we are in the array
    // sum → sum of selected elements
    // list → current subsequence

    // Base condition:
    // When index == arr.length, the subsequence is complete.

    /*
     * APPROACH 1: PRINT ALL SUBSEQUENCES WITH SUM = K
     * - Explores the full recursion tree
     * - Time Complexity: O(2^n)
     * - Space Complexity: O(n) (recursion + list)
     */
    public static void printAllSubsequences(int[] arr, int k, int index, ArrayList<Integer> list, int sum) {
        // BASE CASE: all elements processed
        if (index == arr.length) {
            if (sum == k) {
                System.out.println(list);
            }
            return;
        }

        // INCLUDE current element
        list.add(arr[index]); // choose
        sum += arr[index]; // update sum
        printAllSubsequences(arr, k, index + 1, list, sum);

        // BACKTRACK
        list.remove(list.size() - 1); // undo choice
        sum -= arr[index]; // restore sum

        // EXCLUDE current element
        printAllSubsequences(arr, k, index + 1, list, sum);
    }

    /*
     * APPROACH 2: PRINT ONLY ONE VALID SUBSEQUENCE
     * - Stops recursion as soon as first valid subsequence is found
     * - Uses boolean return to prune recursion tree
     */
    public static boolean printOneSubsequence(
            int[] arr,
            int k,
            int index,
            ArrayList<Integer> list,
            int sum) {
        // BASE CASE
        if (index == arr.length) {
            if (sum == k) {
                System.out.println(list);
                return true; // found one valid subsequence
            }
            return false;
        }

        // INCLUDE current element
        list.add(arr[index]);
        sum += arr[index];

        if (printOneSubsequence(arr, k, index + 1, list, sum)) {
            return true; // stop further recursion
        }

        // BACKTRACK
        list.remove(list.size() - 1);
        sum -= arr[index];

        // EXCLUDE current element
        if (printOneSubsequence(arr, k, index + 1, list, sum)) {
            return true;
        }

        return false; // no valid subsequence found in this path
    }

    /*
     * APPROACH 3: RETURN COUNT OF SUBSEQUENCES WITH SUM = K
     * - Most important pattern for interview problems
     * - No list required
     */
    public static int countSubsequences(
            int[] arr,
            int k,
            int index,
            int sum) {
        // BASE CASE
        if (index == arr.length) {
            return (sum == k) ? 1 : 0;
        }

        // INCLUDE current element
        int left = countSubsequences(arr, k, index + 1, sum + arr[index]);

        // EXCLUDE current element
        int right = countSubsequences(arr, k, index + 1, sum);

        // total ways
        return left + right;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 1 };
        int k = 2;

        System.out.println("All subsequences:");
        printAllSubsequences(arr, k, 0, new ArrayList<>(), 0);

        System.out.println("\nOne subsequence:");
        printOneSubsequence(arr, k, 0, new ArrayList<>(), 0);

        System.out.println("\nTotal count:");
        System.out.println(countSubsequences(arr, k, 0, 0));
    }
}
