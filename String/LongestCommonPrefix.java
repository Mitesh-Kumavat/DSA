package String;

import java.util.Arrays;

public class LongestCommonPrefix {

    /**
     * Approach 1: Sort + Compare First and Last Strings
     *
     * Idea:
     * - After sorting lexicographically, the smallest string and the largest
     * string will have the minimum common prefix among all strings.
     * - Compare only these two → gives the LCP of the whole array.
     *
     * Steps:
     * 1. Sort array.
     * 2. Compare characters of first and last string.
     * 3. Return prefix until mismatch.
     *
     * Time Complexity: O(n log n + m)
     * n = number of strings
     * m = length of shortest string
     *
     * Space Complexity: O(1)
     */
    public static String longestCommonPrefix(String[] strs) {

        Arrays.sort(strs);

        String first = strs[0];
        String last = strs[strs.length - 1];

        StringBuilder ans = new StringBuilder();
        int i = 0;

        // compare characters of first and last string
        while (i < first.length() && first.charAt(i) == last.charAt(i)) {
            ans.append(first.charAt(i));
            i++;
        }

        return ans.toString();
    }

    /**
     * Approach 2: Vertical Scanning
     *
     * Idea:
     * - Check characters column-by-column (same index in all strings).
     * - If all strings have the SAME char at that index → keep adding to prefix.
     * - If any string mismatches → stop immediately (prefix breaks).
     *
     * Why it works:
     * - A prefix must be present in all strings from index 0 onward.
     * - So we verify each index across all strings.
     *
     * Time Complexity: O(n * m)
     * n = number of strings
     * m = length of the shortest string (max columns to check)
     *
     * Space Complexity: O(1)
     */
    public static String lcpVertical(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        StringBuilder ans = new StringBuilder();

        // Step 1: find shortest string length
        int shortest = Integer.MAX_VALUE;
        for (String s : strs) {
            shortest = Math.min(shortest, s.length());
        }

        // Step 2: scan vertically column-by-column
        for (int i = 0; i < shortest; i++) {

            char ch = strs[0].charAt(i); // character from first string

            // check if all strings have same character at index 'i'
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != ch) { // mismatch → LCP ends
                    return ans.toString();
                }
            }

            // all strings matched at index i → add to prefix
            ans.append(ch);
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        String[] strs = { "Apple", "apple", "App" };
        System.out.println(longestCommonPrefix(strs));
    }
}
