package Array;

import java.util.Arrays;

public class SortColors {

    // Brute Force Approach: we can sort the array using any sorting algorithm.
    // TC: O(nlogn) SC: O(1)

    // Better Approach: we can use three variables to keep track of the count of 0s,
    // 1s and 2s and then modify the array accordingly.
    // TC: O(2n) SC: O(1)
    public static void sortColors(int[] nums) {
        int cnt0 = 0, cnt1 = 0;
        for (int i : nums) {
            if (i == 0)
                cnt0++;
            else if (i == 1)
                cnt1++;
        }

        for (int i = 0; i < cnt0; i++) {
            nums[i] = 0;
        }

        for (int i = cnt0; i < cnt0 + cnt1; i++) {
            nums[i] = 1;
        }

        for (int i = (cnt1 + cnt0); i < nums.length; i++) {
            nums[i] = 2;
        }

    }

    // Optimal Approach: Dutch National Flag Algorithm
    public static void sortColorsOptimal(int[] arr) {
        int low = 0, high = arr.length - 1, mid = 0;
        while (mid <= high) {
            if (arr[mid] == 0) {
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                int temp = arr[high];
                arr[high] = arr[mid];
                arr[mid] = temp;
                high--;
            }

        }
    }

    public static void main(String[] args) {
        int[] arr = { 2, 0, 2, 1, 1, 0 };
        sortColorsOptimal(arr);
        System.out.println(Arrays.toString(arr));
    }
}
