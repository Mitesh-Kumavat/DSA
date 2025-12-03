package String;

import java.util.HashMap;

public class CountKDistinctSubstrings {

    // --------------------------------------------
    // MAIN FUNCTION: exactly K distinct characters
    // exactlyK = atMost(K) - atMost(K - 1)
    // --------------------------------------------
    public static int countExactlyKDistinct(String s, int k) {
        return countAtMostKDistinct(s, k) - countAtMostKDistinct(s, k - 1);
    }

    // ----------------------------------------------------------------------
    // Helper Method: count substrings with AT MOST k distinct characters
    // Sliding Window Approach - O(n)
    //
    // IDEA:
    // Keep expanding the window with 'right'.
    // If distinct characters exceed k, shrink from 'left'.
    //
    // Every time the window [left, right] is valid (<= k distinct),
    // it contributes (right - left + 1) NEW substrings that END at 'right'.
    //
    // Example:
    // Window = "pqp"
    // New substrings ending at last 'p':
    // 1) "p"
    // 2) "qp"
    // 3) "pqp"
    // (Count = window size)
    // ----------------------------------------------------------------------
    public static int countAtMostKDistinct(String s, int k) {
        if (k < 0)
            return 0; // edge case for (k-1)

        HashMap<Character, Integer> freq = new HashMap<>();
        int left = 0;
        int count = 0;

        for (int right = 0; right < s.length(); right++) {

            // Add current character to window
            char ch = s.charAt(right);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            // If window violates (distinct > k), shrink it
            while (freq.size() > k) {
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);

                // remove from map if freq becomes zero
                if (freq.get(leftChar) == 0) {
                    freq.remove(leftChar);
                }
                left++; // shrink window
            }

            // Now window has AT MOST k distinct chars
            // → contributes (right - left + 1) new substrings
            count += (right - left + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        String s1 = "pqpqs";
        int k1 = 2;
        System.out.println(countExactlyKDistinct(s1, k1)); // Output: 7

        String s2 = "abcbaa";
        int k2 = 3;
        System.out.println(countExactlyKDistinct(s2, k2)); // Output: 5
    }
}
