package Array;

import java.util.HashMap;

public class LongestSubArray {

    // Brute force with TC : O(n^2)
    // Explaination: Iterate through the entire array and generate
    // subarray from i -> n, arr[i...n]
    // Calculate the sum of the subarray and check if the sum is
    // equal to the given target
    // If equal then compare the previous stored length of the subarray and update
    // it if the current subarray length is greater than the previous stored length
    public static int findLongestSubArrayWithSumK(int[] arr, long k) {
        int len = 0;
        for (int i = 0; i < arr.length; i++) { // iterate through entire array
            int sum = 0;
            for (int j = i; j < arr.length; j++) { // generate subarray from i -> n, arr[i...n]
                sum += arr[j];

                if (sum == k) { // check if the sum is equal to the given target then compare the previos stored
                                // length of the subarray
                    len = Math.max(len, j - i + 1);
                }
            }
        }
        return len;
    }

    // Better approach using HashMap with TC : O(n) and SC : O(n)
    // NOTE: This is the optimal approach which works for both negative and positive
    // numbers
    // Explaination: We will use a HashMap to store the prefix sum and its index
    // We will iterate through the array and keep adding the elements to the prefix
    // sum
    // At each step, we will check if the prefix sum - k is present in the HashMap
    // If present, we will calculate the length of the subarray and update the max
    // length if the current length is greater than the previous stored length
    public static int findLongestSubArrayWithSumK_HashMap(int[] arr, long k) {
        HashMap<Long, Integer> map = new HashMap<>();
        long prefixSum = 0;
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];

            if (prefixSum == k) {
                maxLength = i + 1; // Update maxLength if the prefixSum itself is equal to k
            }

            if (map.containsKey(prefixSum - k)) {
                maxLength = Math.max(maxLength, i - map.get(prefixSum - k));
            }

            // Store the prefix sum and its index if not already present
            if (!map.containsKey(prefixSum)) {
                map.put(prefixSum, i);
            }
        }
        return maxLength;
    }

    // Optimal approach : TC : O(2n) and SC : O(1)
    // Explaination: This approach works only when the array contains only
    // non-negative
    public static int findLongSubArrayLength(int[] arr, long k) {
        int maxLength = 0;
        long sum = arr[0];
        int left = 0, right = 0, n = arr.length;

        while (right < n) {
            while (left <= right && sum > k) {
                sum -= arr[left];
                left--;
            }

            if (sum == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }

            right++;
            if (right < n) {
                sum += arr[right];
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] a = { -1, 0, 1, -5, 1, 0, 4, 7, 1, 2, 9, 1, 0, 3, 0, 5, 0, 1, 9 };
        long k = 8;
        int maxLength = findLongestSubArrayWithSumK(a, k);
        int maxLength2 = findLongestSubArrayWithSumK_HashMap(a, k);
        System.out.println("Length of the longest sub-array with sum " + k + " is: " + maxLength);
        System.out.println("Length of the longest sub-array with sum " + k + " is: " + maxLength2);
    }
}
