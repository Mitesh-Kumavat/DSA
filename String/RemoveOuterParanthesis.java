package String;

public class RemoveOuterParanthesis {

    /**
     * Removes the outermost parentheses from every primitive substring.
     *
     * Example:
     * Input: "(()())(())"
     * Primitives: "(()())", "(())"
     * Final Output: "()()()"
     *
     * Logic:
     * - Maintain a counter 'level' to track the depth.
     * - When we see '(':
     * - If level > 0 → it is NOT an outer parenthesis → append it.
     * - Increase level.
     * - When we see ')':
     * - First decrease level.
     * - If level > 0 after decreasing → it is NOT an outer parenthesis → append it.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) for output
     */
    public static String removeOuterParenthesis(String s) {
        StringBuilder ans = new StringBuilder();
        int level = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '(') {
                // If we are already inside inner parentheses,
                // this '(' is not outer → include it.
                if (level > 0) {
                    ans.append(ch);
                }
                level++; // Increase nesting
            } else {
                level--; // Closing current level first

                // After reducing level, if still inside inner parentheses,
                // this ')' is not outer → include it.
                if (level > 0) {
                    ans.append(ch);
                }
            }
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        String s = "(()())(())";
        String result = removeOuterParenthesis(s);
        System.out.println("Input: " + s);
        System.out.println("Output after removing outermost parentheses: " + result);
    }
}
