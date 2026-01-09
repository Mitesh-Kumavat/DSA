package Recursion;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    public static boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        // Two-pointer technique to compare characters from both ends
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    /**
     * Backtracking function to generate all palindrome partitions.
     *
     * @param index   -> current starting index in the string
     * @param s       -> original input string
     * @param current -> current list of palindrome substrings
     * @param result  -> final list containing all valid partitions
     */
    public static void backtrack(
            int index,
            String s,
            List<String> current,
            List<List<String>> result) {

        /*
         * BASE CASE:
         * If we have reached the end of the string,
         * the current partition is complete and valid.
         */
        if (index == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        /*
         * Try all possible substrings starting from 'index'
         * and ending at positions index+1 to s.length()
         */
        for (int end = index + 1; end <= s.length(); end++) {

            // Extract substring from index to end - 1
            String substring = s.substring(index, end);

            /*
             * Only proceed if the chosen substring is a palindrome.
             * This ensures all partitions are palindrome-only.
             */
            if (isPalindrome(substring)) {

                // Choose the palindrome substring
                current.add(substring);

                // Recurse for the remaining string starting at 'end'
                backtrack(end, s, current, result);

                // Backtrack:
                // Remove last added substring to try other partitions
                current.remove(current.size() - 1);
            }
        }
    }

    public static List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();

        backtrack(0, s, new ArrayList<>(), result);

        return result;
    }

    public static void main(String[] args) {
        String str = "aab";

        // Expected Output:
        // [[a, a, b], [aa, b]]
        System.out.println(partition(str));
    }
}
