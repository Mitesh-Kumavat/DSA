package SlidingWindow;

public class LongestRepeatingCharacterReplacement {

    public int characterReplacementBrute(String s, int k) {

        int maxLength = 0;

        // Try every starting index
        for (int i = 0; i < s.length(); i++) {

            int[] freq = new int[26];
            int maxFreq = 0;

            // Expand substring from i to end
            for (int j = i; j < s.length(); j++) {

                // Increase frequency of current character
                freq[s.charAt(j) - 'A']++;

                // Update max frequency in current window
                maxFreq = Math.max(maxFreq, freq[s.charAt(j) - 'A']);

                int windowSize = j - i + 1;

                // Characters to replace
                int replacements = windowSize - maxFreq;

                if (replacements <= k) {
                    maxLength = Math.max(maxLength, windowSize);
                }
            }
        }

        return maxLength;
    }

    public static int characterReplacementBetter(String s, int k) {

        int l = 0, r = 0;
        int maxLength = 0;
        int[] count = new int[26];

        while (r < s.length()) {

            // Add right character
            count[s.charAt(r) - 'A']++;

            // Calculate current max frequency
            int maxFreq = 0;
            for (int i = 0; i < 26; i++) {
                maxFreq = Math.max(maxFreq, count[i]);
            }

            // If invalid window, shrink
            while (r - l + 1 - maxFreq > k) {
                count[s.charAt(l) - 'A']--;
                l++;

                // Recalculate maxFreq again
                maxFreq = 0;
                for (int i = 0; i < 26; i++) {
                    maxFreq = Math.max(maxFreq, count[i]);
                }
            }

            maxLength = Math.max(maxLength, r - l + 1);
            r++;
        }

        return maxLength;
    }

    public static int characterReplacementOptimal(String s, int k) {

        int left = 0;
        int maxCount = 0;
        int maxLength = 0;
        int[] count = new int[26];

        for (int right = 0; right < s.length(); right++) {

            // Increase count of current character
            count[s.charAt(right) - 'A']++;

            // Update max frequency seen so far
            maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']);

            // If window becomes invalid, shrink it
            if (right - left + 1 - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        int result = characterReplacementOptimal(s, k);
        System.out.println("Longest Repeating Character Replacement: " + result); // Output: 4
    }
}