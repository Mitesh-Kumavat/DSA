// Leetcode: https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
package Array;

public class IsSorted {
    public static boolean isSorted(int[] nums) {
        int n = nums.length;
        int count = 0;

        // Case(No rotation) -> Count will be -> 1
        // Case(with rotation) -> Count will be -> 0
        // This means that there is no rotation in the array and if there will be
        // rotation the count will stay -> 0
        if (nums[0] < nums[n - 1]) {
            count++;
        }

        // Case(No rotation) : Now if there is sorted array and there is no rotation
        // that means the initial count always be 1 , but if a element is found that
        // makes the array unsorted then the count will increase and it returns false

        // Case(With Rotation): If there will be an rotation the count will be initially
        // 0
        // and we know that at some point the rotation occurs and thus it increase the
        // count to 1 , and after that if the unsorted array found then it returns
        // false.
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1])
                count++;
            if (count > 1)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        // TEST CASES

        // int[] arr = { 2, 1 };
        // int[] arr = { 3, 4, 5, 1, 2 };
        // int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        // int[] arr = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        // int[] arr = { 4, 6, 9, 9, 10, 13, 13, 14, 14, 14, 15, 15, 15, 15, 16, 16, 18,
        // 18, 19, 20, 20, 22, 22, 22, 24, 25, 25, 27, 27, 28, 28, 31, 31, 33, 34, 36,
        // 36, 36, 39, 40, 41, 41, 42, 42, 44, 47, 50, 52, 53, 53, 55, 55, 56, 63, 63,
        // 70, 71, 72, 72, 74, 76, 76, 77, 79, 80, 80, 80, 81, 84, 84, 85, 85, 88, 88,
        // 89, 89, 89, 90, 91, 92, 93, 93, 94, 94, 94, 96, 97, 97, 97, 97, 98, 99, 99,
        // 100, 1, 1, 1, 2, 2, 4 };

        int[] arr = { 2, 1, 3, 4 };
        System.out.println(isSorted(arr));
    }
}
