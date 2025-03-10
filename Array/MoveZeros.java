// Leetcode: https://leetcode.com/problems/move-zeroes/description/
package Array;

import java.util.Arrays;

public class MoveZeros {
    public static void moveZeroes(int[] nums) {
        int j = -1;

        // find the first index of where 0 comes
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                j = i;
                break;
            }
        }

        // If 0 is not in the array then just simply return
        if (j == -1) {
            return;
        }

        // but if zero is found then do the following operations
        for (int i = j + 1; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 0, 1, 2, 1, 0, 2, 12, 1, 0 };
        moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }
}
