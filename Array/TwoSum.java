package Array;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    // Brute Froce
    // TC: O(N^2)
    // SC: O(1)
    // Explaination: We will use two loops to iterate through the array and check if
    // the sum of any two elements equals the target. If we find such a pair, we
    // return their indices.
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                sum = nums[j] + nums[i];
                if (sum == target && i != j) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] { -1, -1 };
    }


    // Optimized Approach
    // TC: O(N)
    // SC: O(N)
    // Explaination: We will use a HashMap to store the elements we have seen so far
    // and their indices. For each element, we will check if the complement (target - current element) exists in the HashMap. If it does, we have found our pair and return their indices.
    public static int[] twoSumOtpimized(int[] arr, int target) {
        HashMap<Integer, Integer> mpp = new HashMap<>();

        int ans[] = { 0, 0 };
        for (int i = 0; i < arr.length; i++) {
            if (mpp.containsKey(target - arr[i])) {
                ans[0] = i;
                ans[1] = mpp.get(target - arr[i]);
                return ans;
            } else {
                mpp.put(arr[i], i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 4, 5, 8, 2, 5, 3, 9 };
        int target = 8;

        int[] ans = twoSumOtpimized(arr, target);
        System.out.println(Arrays.toString(ans));
    }
}
