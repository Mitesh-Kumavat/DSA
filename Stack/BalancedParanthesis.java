package Stack;

import java.util.*;

public class BalancedParanthesis {

    /*
     * This method checks whether the given string has
     * balanced parentheses.
     *
     * Valid brackets:
     * () {} []
     */
    public static boolean isValid(String s) {

        // If length is odd, it can never be balanced
        // because every opening bracket needs one closing bracket
        if (s.length() % 2 != 0) {
            return false;
        }

        // Stack is used to store opening brackets
        Stack<Character> stack = new Stack<>();

        /*
         * Map stores the relation between
         * closing bracket -> opening bracket
         *
         * Example:
         * ')' should match '('
         */
        Map<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put(')', '(');
        bracketMap.put('}', '{');
        bracketMap.put(']', '[');

        // Traverse each character in the string
        for (int i = 0; i < s.length(); i++) {

            char current = s.charAt(i);

            // If current character is an opening bracket
            // push it into the stack
            if (current == '(' || current == '{' || current == '[') {
                stack.push(current);
            }
            // Otherwise, it is a closing bracket
            else {

                // If stack is empty, there is no opening bracket
                // available to match with this closing bracket
                if (stack.isEmpty()) {
                    return false;
                }

                // Get the expected opening bracket
                char expectedOpening = bracketMap.get(current);

                // Pop the top element from stack and compare
                if (stack.pop() != expectedOpening) {
                    return false;
                }
            }
        }

        // If stack is not empty at the end,
        // it means some opening brackets were not closed
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println(isValid("{}()[]")); // true
        System.out.println(isValid("{}([{()}])")); // true
        System.out.println(isValid("{}([{()}{}]{})")); // true
        System.out.println(isValid("{}([{()}{}{]})")); // false
        System.out.println(isValid("){")); // false
    }
}
