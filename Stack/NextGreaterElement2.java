package Stack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement2 {

    /*
     * PROBLEM:
     * Given a circular array, find the Next Greater Element (NGE) for every
     * element.
     * 
     * Next Greater Element of an element x:
     * -> The first element greater than x when we move to the right
     * -> Since the array is circular, after reaching the end, we continue from the
     * start
     * 
     * Example:
     * nums = [1, 2, 1]
     * 
     * Circular traversal:
     * index 0 -> 1 -> next greater is 2
     * index 1 -> 2 -> no greater element -> -1
     * index 2 -> 1 -> next greater is 2
     * 
     * Output: [2, -1, 2]
     */

    public static int[] nextGreaterElements(int[] nums) {

        int n = nums.length;

        // This array will store the final answer
        // ans[i] = next greater element of nums[i]
        int[] ans = new int[n];

        // Stack will store "potential next greater elements"
        // We store actual values, NOT indices
        Stack<Integer> stack = new Stack<>();

        /*
         * KEY IDEA:
         * To handle circular array:
         * -> Traverse the array twice (2 * n times)
         * 
         * Why traverse from right to left?
         * -> Because Next Greater Element depends on elements on the right
         * 
         * Loop runs from (2n - 1) to 0
         */
        for (int i = n * 2 - 1; i >= 0; i--) {

            // Modulo gives the real index in original array
            // This simulates circular behavior
            int index = i % n;

            int currentEl = nums[index];

            /*
             * Remove all elements from stack that are:
             * <= current element
             * 
             * Why?
             * -> They can NEVER be the next greater element
             * -> Because current element blocks them
             */
            while (!stack.isEmpty() && stack.peek() <= currentEl) {
                stack.pop();
            }

            /*
             * After popping:
             * -> If stack is empty: no greater element exists
             * -> Else: top of stack is the next greater element
             */
            ans[index] = stack.isEmpty() ? -1 : stack.peek();

            /*
             * Push current element into stack
             * -> It may become the next greater element
             * for elements on the left
             */
            stack.push(currentEl);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 1 };
        System.out.println(Arrays.toString(nextGreaterElements(nums)));
    }
}