package BitManipulation;

import java.util.List;

public class MinBitwiseORArray {

    // Intution: Even → impossible → -1
    // Odd → remove the rightmost chain of 1s to minimize OR

    public int[] minBitwiseArray(List<Integer> nums) {

        // Result array
        int[] ans = new int[nums.size()];

        // Iterate through each number
        for (int i = 0; i < nums.size(); i++) {

            int num = nums.get(i);

            /*
             * OBSERVATION:
             * If number is even, its LSB (last bit) is 0.
             * You cannot reduce bitwise OR result to a smaller value
             * by flipping bits for even numbers.
             * Hence, answer is -1.
             */
            if (num % 2 == 0) {
                ans[i] = -1;
                continue;
            }

            /*
             * INTUITION:
             * For odd numbers, last bit is always 1.
             * To minimize OR, we should remove the
             * "rightmost continuous 1s" pattern.
             */

            int cnt = 1;

            /*
             * Find first 0 bit after trailing 1s.
             * We keep moving left while bits are 1.
             */
            while (((num >> cnt) & 1) != 0) {
                cnt++;
            }

            /*
             * Once found, flip the last 1 in the chain.
             * This reduces the number while keeping OR minimal.
             */
            ans[i] = num ^ (1 << (cnt - 1));
        }

        return ans;
    }
    /*
     * Flip the last 1 in the trailing chain
     *
     * Example: num = 7
     * Binary: 111
     * Trailing 1s: positions 0,1,2
     * First 0 at position 3
     * Flip bit at (3 - 1) = 2
     * 111 ^ 100 = 011 → 3
     *
     * Example: num = 13
     * Binary: 1101
     * Trailing 1s: position 0 only
     * First 0 at position 1
     * Flip bit at (1 - 1) = 0
     * 1101 ^ 0001 = 1100 → 12
     */

    public static void main(String[] args) {
        MinBitwiseORArray solution = new MinBitwiseORArray();
        List<Integer> nums = List.of(3, 5, 2, 7, 8);
        int[] result = solution.minBitwiseArray(nums);

        for (int val : result) {
            System.out.print(val + " ");
        }
    }
}
