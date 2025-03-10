// Leetcode: https://leetcode.com/problems/rotate-array/description/
package Array;

import java.util.Arrays;

public class RotateArray {

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void leftRotateByOne(int[] arr) {
        /*
         * This will just rotate the array by one element only
         * Ex: [1, 2, 3, 4, 5] -> [2, 3, 4, 5, 1]
         * Time Complexity: O(N) , Space Complexity: O(1) we are not using any extra
         * space , but in order to solve this problem we are using the array it self so
         * in that case: O(N)
         */
        int temp = arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }
        arr[arr.length - 1] = temp;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        // leftRotateByOne(arr);
        rotate(arr, 34);
        System.out.println(Arrays.toString(arr));
    }
}
