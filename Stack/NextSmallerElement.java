package Stack;

import java.util.Arrays;
import java.util.Stack;

public class NextSmallerElement {

    public static int[] nextSmall(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            int current = arr[i];

            // Remove all elements from stack that are
            // greater than or equal to current element
            // because they cannot be the next smaller element
            while (!stack.isEmpty() && stack.peek() >= current) {
                stack.pop();
            }

            // If stack is empty, no smaller element exists on right
            // Otherwise, top of stack is the next smaller element
            ans[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element into stack
            // so it can be the next smaller element for elements on left
            stack.push(current);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 8, 5, 2, 25 };
        System.out.println(Arrays.toString(nextSmall(arr)));
    }
}
