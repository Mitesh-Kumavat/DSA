package BitManipulation;

public class SingleNumber3 {

    /*
     * Problem:
     * - Every number appears exactly twice except TWO numbers.
     * - Find those two unique numbers.
     * 
     * Approach (Bit Manipulation):
     * - XOR of same numbers = 0
     * - XOR of 0 with a number = number
     */

    public static int[] singleNumber3(int nums[]) {

        // Step 1: Take XOR of all elements
        // Duplicates cancel out, result = a ^ b (where a and b are unique numbers)
        long xor = 0;
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }

        /*
         * Step 2: Find the rightmost set bit in xor
         * - This bit is different between the two unique numbers
         * - Formula: xor & (-xor)
         * Example:
         * xor =  010100
         * -xor = 101100
         * result = 000100 (rightmost set bit)
         */
        int rightmostSetBit = (int) (xor & -xor);

        // Step 3: Divide numbers into two groups based on this bit
        // Group 1: bit is 0
        // Group 2: bit is 1
        int ans[] = new int[2];

        for (int i = 0; i < nums.length; i++) {

            // If the rightmost set bit is NOT present
            if ((nums[i] & rightmostSetBit) == 0) {
                ans[0] ^= nums[i]; // XOR group 1
            }
            // If the rightmost set bit IS present
            else {
                ans[1] ^= nums[i]; // XOR group 2
            }
        }

        /*
         * Since duplicate numbers go to the same group,
         * they cancel out. Each group finally contains
         * exactly one unique number.
         */
        return ans;
    }

    public static void main(String[] args) {

        int nums[] = { 1, 2, 1, 3, 2, 5 };

        int result[] = singleNumber3(nums);

        System.out.println("The two single numbers are: " + result[0] + " and " + result[1]);
    }
}
