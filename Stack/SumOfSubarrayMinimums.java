package Stack;

import java.util.Stack;

public class SumOfSubarrayMinimums {

    /*
     * Find Next Smaller Element (NSE) index for each element
     * If no smaller element on right, store n
     */
    public int[] findNSE(int[] arr) {

        int n = arr.length;
        int[] nse = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {

            // Remove elements greater than or equal to current
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            // If stack empty → no smaller on right
            nse[i] = st.isEmpty() ? n : st.peek();

            // Push current index
            st.push(i);
        }
        return nse;
    }

    /*
     * Find Previous Smaller Element (PSE) index for each element
     * If no smaller element on left, store -1
     */
    public int[] findPSE(int[] arr) {

        int n = arr.length;
        int[] pse = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from left to right
        for (int i = 0; i < n; i++) {

            // Remove elements strictly greater than current
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            // If stack empty → no smaller on left
            pse[i] = st.isEmpty() ? -1 : st.peek();

            // Push current index
            st.push(i);
        }
        return pse;
    }

    /*
     * Main logic:
     * Each element contributes as minimum in:
     * (i - PSE[i]) * (NSE[i] - i) subarrays
     */
    public int sumSubarrayMins(int[] arr) {

        int mod = (int) 1e9 + 7;

        int[] nse = findNSE(arr);
        int[] pse = findPSE(arr);

        long sum = 0;

        for (int i = 0; i < arr.length; i++) {

            long leftCount = i - pse[i];     // choices on left
            long rightCount = nse[i] - i;    // choices on right

            // contribution of arr[i]
            sum = (sum + arr[i] * leftCount * rightCount) % mod;
        }

        return (int) sum;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 1, 2, 4 };
        SumOfSubarrayMinimums obj = new SumOfSubarrayMinimums();
        int result = obj.sumSubarrayMins(arr);
        System.out.println("Sum of subarray minimums: " + result);
    }
}