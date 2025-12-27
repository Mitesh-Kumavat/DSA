package Recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    /*
     * BACKTRACKING FUNCTION
     *
     * current -> string built so far (possible answer)
     * open -> how many '(' we have used till now
     * close -> how many ')' we have used till now
     * n -> total pairs of parentheses allowed
     *
     * IMPORTANT IDEA:
     * We NEVER build an invalid string.
     * We only add characters when it is SAFE to do so.
     */
    public static void backtrack(List<String> result, String current,
            int open, int close, int n) {

        /*
         * BASE CASE:
         * If the length of the current string is 2 * n,
         * it means we have used:
         * - n opening brackets '('
         * - n closing brackets ')'
         *
         * So this is a COMPLETE and VALID parentheses string.
         */
        if (current.length() == 2 * n) {
            result.add(current);
            return; // stop further recursion from this path
        }

        /*
         * CHOICE 1: Add '('
         *
         * CONDITION:
         * We can add '(' only if we have not used all n opening brackets.
         *
         * Why?
         * Because total '(' allowed = n
         */
        if (open < n) {
            // add '(' and increase open count
            backtrack(result, current + "(", open + 1, close, n);
        }

        /*
         * CHOICE 2: Add ')'
         *
         * CONDITION:
         * We can add ')' only if close < open
         *
         * Why?
         * Because we cannot close a bracket that was never opened.
         * This condition guarantees the string is always VALID.
         */
        if (close < open) {
            // add ')' and increase close count
            backtrack(result, current + ")", open, close + 1, n);
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        // Start backtracking:
        // current = ""
        // open = 0, close = 0
        backtrack(result, "", 0, 0, n);

        return result;
    }

    public static void main(String[] args) {
        int n = 3;

        /*
         * Example:
         * n = 3
         *
         * Output:
         * [((())), (()()), (())(), ()(()), ()()()]
         */
        System.out.println(generateParenthesis(n));
    }
}
