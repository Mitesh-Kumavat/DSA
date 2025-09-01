package Array;

import java.util.HashMap;
import java.util.Map;

public class CountSubArray {

    // Better force : O(N^2)
    // Generate all the subarray and calculate its sum
    public static int countSubArray(int[] arr, int target) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];

                if (sum == target) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int countSubArrayOptimal(int[] arr, int k) {
        int n = arr.length; // size of the given array.
        Map<Integer, Integer> mpp = new HashMap<>();
        int preSum = 0, cnt = 0;

        mpp.put(0, 1); // Setting 0 in the map.
        for (int i = 0; i < n; i++) {
            // add current element to prefix Sum:
            preSum += arr[i];

            // Calculate x-k:
            int remove = preSum - k;

            // Add the number of subarrays to be removed:
            cnt += mpp.getOrDefault(remove, 0);

            // Update the count of prefix sum
            // in the map.
            mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1);
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3 };
        int target = 3;
        System.out.println(countSubArray(arr, target));

    }
}
