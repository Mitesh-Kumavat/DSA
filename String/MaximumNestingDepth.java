package String;

public class MaximumNestingDepth {

    // Optimal Approach: Single Pass
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    // Explanation: We traverse the string once, maintaining a counter for the
    // current depth of nested parentheses. We increment the counter when we
    // encounter an opening parenthesis '(' and decrement it when we encounter a
    // closing parenthesis ')'. We also keep track of the maximum depth encountered
    // during the traversal.
    public static int maxDepth(String s) {
        int max = 0;
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                cnt++;
                max = Math.max(cnt, max);
            } else if (s.charAt(i) == ')') {
                cnt--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";
        System.out.println(maxDepth(s)); // outputs: 3
    }
}
