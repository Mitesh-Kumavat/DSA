package String;

public class SumOfBeautyOfAllSubstring {

    public int beautySum(String s) {
        int n = s.length();
        int total = 0;

        /*
         * Beauty Definition:
         * For every substring:
         * beauty = (max frequency char) - (min frequency char)
         *
         * We generate all substrings starting from i.
         * For each starting index i, we expand j towards the right.
         *
         * While expanding:
         * - Update freq of current character.
         * - Update maxFreq.
         * - Compute minFreq among characters that appear at least once.
         * - Add (maxFreq - minFreq) to total.
         */

        for (int i = 0; i < n; i++) {

            // Frequency array for current substring starting at i
            int[] freq = new int[26];
            int maxFreq = 0;

            for (int j = i; j < n; j++) {

                // Add current character to freq
                int idx = s.charAt(j) - 'a';
                freq[idx]++;

                // Update maximum frequency seen so far in this substring
                if (freq[idx] > maxFreq)
                    maxFreq = freq[idx];

                // Find minimum frequency among characters that appear at least once
                int minFreq = Integer.MAX_VALUE;
                for (int k = 0; k < 26; k++) {
                    if (freq[k] > 0 && freq[k] < minFreq) {
                        minFreq = freq[k];
                    }
                }

                // If substring has only one character, minFreq stays MAX -> fix it
                if (minFreq == Integer.MAX_VALUE)
                    minFreq = 0;

                // Add beauty of current substring to answer
                total += (maxFreq - minFreq);
            }
        }

        return total;
    }

    public static void main(String[] args) {
        SumOfBeautyOfAllSubstring solution = new SumOfBeautyOfAllSubstring();

        String s1 = "aabcb";
        System.out.println("Sum of Beauty for '" + s1 + "': " + solution.beautySum(s1)); // Output: 5

        String s2 = "abc";
        System.out.println("Sum of Beauty for '" + s2 + "': " + solution.beautySum(s2)); // Output: 0
    }
}
