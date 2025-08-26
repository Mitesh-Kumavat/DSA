package Array;

public class MaxOnes {

    // Optimal Method:
    // TC: O(n)
    // SC: O(1)
    // we will traverse the array and keep a count of consecutive 1's
    // if we encounter a 0, we will reset the count to 0
    // we will also keep track of the maximum count of consecutive 1's
    // and return it at the end
    public static int findMaxConsecutiveOnes(int[] nums) {
        int cnt = 0;
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                cnt++;
                if (cnt > max) {
                    max++;
                }
            } else {
                cnt = 0;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = { 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1 };
        System.out.println(findMaxConsecutiveOnes(arr));
    }
}
