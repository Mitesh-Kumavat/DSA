package SlidingWindow;

import java.util.Arrays;
import java.util.HashMap;

public class LongestSubstringWithoutRepeating {

    public static int lengthOfLongestSubstringBrute(String s) {

        if (s.length() == 0)
            return 0;

        int ans = 0;

        for (int i = 0; i < s.length(); i++) {

            int currentLength = 0;

            // HashMap to track characters in current substring
            HashMap<Character, Integer> map = new HashMap<>();

            // Inner loop - expand substring
            for (int j = i; j < s.length(); j++) {

                char ch = s.charAt(j);

                // If character not seen before
                if (!map.containsKey(ch)) {
                    map.put(ch, 1);
                    currentLength++;

                    // Update max answer
                    ans = Math.max(ans, currentLength);
                } else {
                    // Duplicate found → break
                    break;
                }
            }
        }

        return ans;
    }

    public static int lengthOfLongestSubstringOptimal(String s) {

        if (s.length() == 0)
            return 0;

        // Array to store last seen index of characters
        // ASCII size = 256
        int[] hash = new int[256];

        // Initialize all values to -1 (means not seen yet)
        Arrays.fill(hash, -1);

        int ans = 0; // Stores maximum length
        int l = 0; // Left pointer of window
        int r = 0; // Right pointer of window

        // Expand window using right pointer
        while (r < s.length()) {

            char current = s.charAt(r);

            // If character was seen before
            if (hash[current] != -1) {

                // If it lies inside current window -> means [l, r]
                // For Ex: s = "abcdeaf", when r = 5 (pointing to 'a'), l = 0 (pointing to 'a')
                // hash['a'] = 0, which is >= l (0) → means 'a' is inside current window

                // If it lies outside current window → means [l, r] does not contain 'a'
                // For Ex: s = "abcdeaf", when r = 5 (point to 'a'), l = 1 (pointing to 'b')
                // hash['a'] = 0, which is < l (1) → means 'a' is outside current window
                if (hash[current] >= l) {

                    // Move left pointer to one position ahead of last occurrence
                    l = hash[current] + 1;
                }
            }

            // Calculate current window length
            int windowLength = r - l + 1;

            // Update maximum length
            ans = Math.max(ans, windowLength);

            // Update last seen index of current character
            hash[current] = r;

            // Move right pointer forward
            r++;
        }

        return ans;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstringBrute(s));
        System.out.println(lengthOfLongestSubstringOptimal(s));
    }
}
