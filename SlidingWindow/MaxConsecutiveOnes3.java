package SlidingWindow;

public class MaxConsecutiveOnes3 {

    public static int longestOnesBrute(int[] arr, int k) {

        int maxLen = 0; // Stores maximum length found

        // Try every possible starting index
        for (int i = 0; i < arr.length; i++) {

            int noOfZeros = 0; // Count zeros in current subarray

            // Expand subarray from i to end
            for (int j = i; j < arr.length; j++) {

                // If current element is 0 → increase zero count
                if (arr[j] == 0) {
                    noOfZeros++;
                }

                // If zeros are within allowed limit
                if (noOfZeros <= k) {

                    // Update maximum length
                    maxLen = Math.max(maxLen, j - i + 1);
                } else {
                    // If zeros exceed k → stop expanding
                    break;
                }
            }
        }

        return maxLen;
    }

    public static int longestOnes(int[] arr, int k) {

        int maxLen = 0; // Stores maximum valid window length
        int zeros = 0; // Count of zeros inside current window

        int l = 0; // Left pointer
        int r = 0; // Right pointer

        // Expand window using right pointer
        while (r < arr.length) {

            // If current element is 0 → increase zero count
            if (arr[r] == 0)
                zeros++;

            /*
             * If zeros exceed allowed limit k,
             * shrink window from left side
             */
            while (zeros > k) {

                // If left element is zero,
                // removing it reduces zero count
                if (arr[l] == 0)
                    zeros--;

                l++; // Shrink window
            }

            /*
             * At this point,
             * zeros <= k
             * So this window is valid
             */
            maxLen = Math.max(maxLen, r - l + 1);

            // Expand window
            r++;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
        int k = 2;

        System.out.println(longestOnesBrute(nums, k));
    }
}
