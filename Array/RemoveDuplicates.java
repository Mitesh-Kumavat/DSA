package Array;

import java.util.Arrays;

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[i + 1] = nums[j];
                i++;
            }
        }

        return i + 1;
    }

    public static void main(String[] args) {
        RemoveDuplicates solution = new RemoveDuplicates();
        int arr[] = { 1, 1, 1, 2, 2, 2, 2, 3, 4 };
        System.out.println(solution.removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }
}
