package String;

public class LongestPalindromicSubstring {

    public boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    // BRUTE FORCE APPROACH (O(n³))
    // → Generate all substrings + check palindrome
    public String longestPalindromeBruteForce(String s) {

        if (s.length() <= 1)
            return s;

        String ans = String.valueOf(s.charAt(0)); // smallest possible palindrome

        // Try every possible substring
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {

                String sub = s.substring(i, j + 1); // substring from i → j

                // Check if it is palindrome AND longer than current answer
                if (isPalindrome(sub) && sub.length() > ans.length()) {
                    ans = sub;
                }
            }
        }

        return ans;
    }

    // OPTIMAL APPROACH: EXPAND AROUND CENTER (O(n²))
    // → Expand for odd-length and even-length palindromes
    // Expansion method: expands around the given left/right pointers
    private String expandFromCenter(String s, int left, int right) {
        // Move outward while chars match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // When loop stops → we over-expanded, so return valid palindrome
        return s.substring(left + 1, right);
    }

    public String longestPalindromeOptimal(String s) {

        if (s.length() <= 1)
            return s;

        String ans = "";

        // Try expanding around each character (as center)
        for (int i = 0; i < s.length(); i++) {

            // Odd length palindrome → center at i
            String odd = expandFromCenter(s, i, i);

            // Even length palindrome → center between i and i+1
            String even = expandFromCenter(s, i, i + 1);

            // Take the longer palindrome out of odd/even
            String longer = odd.length() > even.length() ? odd : even;

            // Update global answer
            if (longer.length() > ans.length()) {
                ans = longer;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        LongestPalindromicSubstring lp = new LongestPalindromicSubstring();

        String s = "babad";

        System.out.println("Brute force:  " + lp.longestPalindromeBruteForce(s));
        System.out.println("Optimal:      " + lp.longestPalindromeOptimal(s));
    }
}
