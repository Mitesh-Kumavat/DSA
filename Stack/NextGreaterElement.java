package Stack;

import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElement {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // Map to store: element -> its next greater element
        HashMap<Integer, Integer> map = new HashMap<>();

        // Stack will store elements in decreasing order (monotonic stack)
        Stack<Integer> stack = new Stack<>();

        // Traverse nums2 from right to left
        // Reason: we want to find the next greater element on the right side
        for (int i = nums2.length - 1; i >= 0; i--) {

            int current = nums2[i];

            // Remove all elements from stack that are smaller or equal to current
            // Because they can NEVER be the next greater element for current
            while (!stack.isEmpty() && stack.peek() <= current) {
                stack.pop();
            }

            // If stack becomes empty, it means no greater element exists on right
            // Otherwise, the top of stack is the next greater element
            map.put(current, stack.isEmpty() ? -1 : stack.peek());

            // Push current element into stack
            // It may act as the next greater element for elements on the left
            stack.push(current);
        }

        // Build the result array for nums1 using the map
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] nums1 = { 1, 3, 5, 2, 4 };
        int[] nums2 = { 6, 5, 4, 3, 2, 1, 7 };

        int[] result = nextGreaterElement(nums1, nums2);

        for (int val : result) {
            System.out.print(val + " ");
        }
    }
}
