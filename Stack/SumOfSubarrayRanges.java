package Stack;

import java.util.Stack;

public class SumOfSubarrayRanges {
    public static long subArrayRangesBrute(int[] arr) {
        long ans = 0;

        for (int i = 0; i < arr.length; i++) {
            int max = arr[i];
            int min = arr[i];

            for (int j = i; j < arr.length; j++) {
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                ans += (max - min);
            }

        }

        return ans;
    }

    // Function to find indices of Next Smaller Elements
    private static int[] findNSE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            ans[i] = !st.isEmpty() ? st.peek() : n;
            st.push(i);
        }
        return ans;
    }

    // Function to find indices of Next Greater Elements
    private static int[] findNGE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            ans[i] = !st.isEmpty() ? st.peek() : n;
            st.push(i);
        }
        return ans;
    }

    // Function to find indices of Previous Smaller or Equal Elements
    private static int[] findPSEE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }
            ans[i] = !st.isEmpty() ? st.peek() : -1;
            st.push(i);
        }
        return ans;
    }

    // Function to find indices of Previous Greater or Equal Elements
    private static int[] findPGEE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) {
                st.pop();
            }
            ans[i] = !st.isEmpty() ? st.peek() : -1;
            st.push(i);
        }
        return ans;
    }

    // Function to compute sum of subarray minimums
    private static long sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] nse = findNSE(arr);
        int[] psee = findPSEE(arr);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int left = i - psee[i];
            int right = nse[i] - i;
            long freq = 1L * left * right;
            sum += freq * arr[i];
        }
        return sum;
    }

    // Function to compute sum of subarray maximums
    private static long sumSubarrayMaxs(int[] arr) {
        int n = arr.length;
        int[] nge = findNGE(arr);
        int[] pgee = findPGEE(arr);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int left = i - pgee[i];
            int right = nge[i] - i;
            long freq = 1L * left * right;
            sum += freq * arr[i];
        }
        return sum;
    }

    // Function to compute total sum of subarray ranges (max - min)
    public static long subArrayRanges(int[] arr) {
        return sumSubarrayMaxs(arr) - sumSubarrayMins(arr);
    }

    public static void main(String[] args) {
        int[] arr = { 4, -2, -3, 4, 1 };
        int[] arr2 = { 1, 2, 3 };
        int[] arr3 = { 1, 3, 3 };
        System.out.println(subArrayRanges(arr));
        System.out.println(subArrayRanges(arr2));
        System.out.println(subArrayRanges(arr3));
    }
}
