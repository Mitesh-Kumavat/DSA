package BinarySearch;

// LeetCode: Split Array Largest Sum
// Problem: Split array into k subarrays such that the maximum subarray sum is minimized.
// Solution: Binary Search on Answer + Greedy split count.

public class SplitArrayLargestSum {

    /**
     * This function counts how many subarrays are needed
     * if we do not allow any subarray to exceed maxSum.
     *
     * Greedy logic:
     * - Keep adding elements until adding the next element
     * crosses maxSum.
     * - If crossed → start a new subarray.
     *
     * Example:
     * arr = [7,2,5,10,8], maxSum = 14
     * partitions form as:
     * [7,2,5] | [10] | [8] → total = 3 partitions
     */
    public int countOfSplits(int[] arr, int maxSum) {
        int subarrays = 1; // At least 1 partition always
        int currentSum = 0;

        for (int num : arr) {

            // If adding current element does NOT overflow the allowed maxSum
            if (currentSum + num <= maxSum) {
                currentSum += num;
            } else {
                // Overflow → we must start a new split
                subarrays++;
                currentSum = num; // New partition starts with current element
            }
        }

        return subarrays;
    }

    /**
     * Main function:
     * Binary Search for the minimum possible "largest subarray sum".
     *
     * low = max element (because each partition must contain that element)
     * high = sum of entire array (worst case: everything in one partition)
     *
     * For each mid, check:
     * - How many partitions are needed with largest sum <= mid?
     * - If partitions <= k → mid is valid (try smaller)
     * - Else → mid too small, increase it
     */
    public int splitArray(int[] nums, int k) {
        int low = 0;
        int high = 0;

        // Edge case: Cannot create more partitions than elements
        if (nums.length < k)
            return -1;

        // Compute:
        // low → max element of array
        // high → total sum of array
        for (int num : nums) {
            high += num;
            low = Math.max(low, num);
        }

        int finalAns = -1;

        // Binary search between low and high
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Count how many partitions are needed with this mid
            int partitions = countOfSplits(nums, mid);

            if (partitions <= k) {
                // We can try to minimize the answer further
                finalAns = mid;
                high = mid - 1;
            } else {
                // Too many partitions → mid is too small
                low = mid + 1;
            }
        }

        return finalAns;
    }
}
