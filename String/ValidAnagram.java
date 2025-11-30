package String;

import java.util.Arrays;

public class ValidAnagram {

    /**
     * Approach 1: Sorting
     *
     * Logic:
     * - If lengths differ → cannot be anagrams.
     * - Convert both strings into char arrays.
     * - Sort both arrays.
     * - If sorted versions are equal → anagram.
     *
     * Time Complexity: O(n log n) due to sorting.
     * Space Complexity: O(n)
     */
    public static boolean isAnagramSort(String s, String t) {
        if (s.length() != t.length())
            return false;

        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }

    /**
     * Approach 2: Frequency Counting (Optimal)
     *
     * Logic:
     * - Use an array of size 26 to count character frequencies.
     * - Increase count for characters from s.
     * - Decrease count for characters from t.
     * - After processing both strings:
     * → all positions in the frequency array must be zero.
     *
     * Why it works:
     * - Every increment from s must be cancelled by a matching character from t.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static boolean isAnagramCount(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] freq = new int[26]; // since only lowercase letters

        // +1 for s, -1 for t
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        // If any non-zero left → frequencies mismatch
        for (int x : freq) {
            if (x != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "listen";
        String t = "silent";

        System.out.println(isAnagramSort(s, t)); // true
        System.out.println(isAnagramCount(s, t)); // true
    }
}
