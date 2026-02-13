package Stack;

import java.util.Stack;

public class RemoveKDigits {

    /*
     * PROBLEM:
     * Remove k digits from the number such that
     * the remaining number is the smallest possible.
     *
     * CORE IDEA (Greedy + Monotonic Increasing Stack):
     * - If current digit is smaller than stack top,
     * remove the bigger digit (because it increases number).
     * - Early removal of bigger digits leads to smaller number.
     *
     * We want digits in increasing order.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */

    public static String removeKdigits(String num, int k) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < num.length(); i++) {

            char current = num.charAt(i);

            /*
             * If:
             * - stack not empty
             * - top digit is bigger than current digit
             * - still allowed to remove digits (k > 0)
             *
             * Then remove the bigger digit.
             *
             * WHY?
             * Because a smaller digit on left gives smaller number.
             */
            while (!stack.isEmpty()
                    && stack.peek() > current
                    && k > 0) {

                stack.pop();
                k--;
            }

            stack.push(current);
        }

        /*
         * If k still > 0:
         * It means number was already increasing
         * Example: 123456
         * So remove digits from the end.
         */
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        // If all digits removed
        if (stack.isEmpty())
            return "0";

        StringBuilder res = new StringBuilder();

        /*
         * Stack gives digits in reverse order,
         * so we collect them first.
         */
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }

        /*
         * Remove leading zeros.
         * Since number is reversed currently,
         * leading zeros appear at end.
         */
        while (res.length() > 0 && res.charAt(res.length() - 1) == '0') {
            res.deleteCharAt(res.length() - 1);
        }

        // If everything becomes zero
        if (res.length() == 0)
            return "0";

        // Reverse back to original order
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        String s = "1231212";
        int k = 3;
        System.out.println(removeKdigits(s, k));
    }
}