package Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;

        // Edge case:
        if (n == 0 || k == 0) {
            return new int[0];
        }

        // The number of windows formed will be:
        // (n - k + 1)
        // Example: n=8, k=3 → 6 windows
        int[] result = new int[n - k + 1];

        // Deque to store indices of elements.
        // WHY indices and not values?
        // Because we must:
        // 1) Check if element is out of current window range
        // 2) Compare values using nums[index]
        //
        // This deque will always maintain:
        // 1) Elements in decreasing order of values
        // 2) Front element = maximum of current window
        Deque<Integer> win = new ArrayDeque<>();

        for (int i = 0; i < n; ++i) {
            // STEP 1: Remove indices that are out of this window
            // Valid window range at index i:
            // [i - k + 1 ... i]
            //
            // If index <= i - k → it is outside window.
            //
            // Example:
            // i = 3, k = 3
            // valid indices = [1,2,3]
            // index 0 must be removed
            //
            while (!win.isEmpty() && win.peekFirst() <= i - k) {
                win.pollFirst(); // remove from front
            }

            // STEP 2: Maintain decreasing order in deque
            //
            // We want:
            // nums[win[0]] >= nums[win[1]] >= nums[win[2]]
            //
            // If current element nums[i] is greater than
            // elements at back of deque,
            // those smaller elements can NEVER become maximum
            // in this or any future window.
            //
            // So we remove them.
            //
            while (!win.isEmpty() && nums[win.peekLast()] < nums[i]) {
                win.pollLast(); // remove smaller elements
            }

            // STEP 3: Add current index to deque
            //
            // Now deque is valid and decreasing.
            // Add current index at back.
            //
            win.offerLast(i);

            // STEP 4: Store maximum for formed window
            //
            // First full window forms when:
            // i >= k - 1
            //
            // The maximum element of window is always at
            // the FRONT of deque.
            //
            if (i >= k - 1) {
                result[i - k + 1] = nums[win.peekFirst()];
            }
        }

        return result;
    }

    public static int[] maxSlidingWindowBrute(int[] arr, int k) {
        int[] ans = new int[arr.length - k + 1];

        for (int i = 0; i <= arr.length - k; i++) {
            int max = arr[i];
            for (int j = i; j <= (i + k - 1); j++) {
                max = Math.max(arr[j], max);
            }
            ans[i] = max;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindowBrute(arr, k)));
        System.out.println(Arrays.toString(maxSlidingWindow(arr, k)));
    }
}
