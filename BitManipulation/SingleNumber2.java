package BitManipulation;

import java.util.Arrays;

public class SingleNumber2 {

    /*
     * Sort the array so that every number appearing 3 times comes together,
     * then scan in steps of 3 to find the one that breaks the pattern.
     *
     * Time Complexity: O(n log n)
     */
    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length - 2; i = i + 3) {

            // If the previous number is different, it must be the single number
            if (nums[i] != nums[i - 1])
                return nums[i - 1];

            // If the next number is different, it must be the single number
            if (nums[i] != nums[i + 1])
                return nums[i + 1];
        }

        // If not found in loop, the last element is the answer
        return nums[nums.length - 1];
    }

    /*
     * BIT MANIPULATION
     *
     * This is the MOST IMPORTANT approach to revise.
     * This is often called:
     * - "Bucket approach using bits"
     *
     * We track how many times each bit appears using two buckets:
     *
     * ones → stores bits that have appeared once
     * twos → stores bits that have appeared twice
     *
     * Any bit that appears a 3rd time must be removed from both.
     *
     * --------------------------------------------------------------------
     * HOW THE CODE EXECUTES :
     *
     * For every number `num`:
     *
     * 1) Add `num` to `ones` ONLY IF that bit is NOT already in `twos`
     * ones = (ones ^ num) & ~twos;
     *
     * - XOR (^) adds the bit if it is new, removes it if it already exists
     * - & ~twos blocks bits that already appeared twice
     *
     * 2) Add `num` to `twos` ONLY IF that bit is NOT already in `ones`
     * twos = (twos ^ num) & ~ones;
     *
     * - XOR toggles the bit in twos
     * - & ~ones ensures a bit cannot exist in both buckets
     *
     * --------------------------------------------------------------------
     * WHY THIS WORKS:
     *
     * Each bit follows this cycle:
     *
     * not seen → ones → twos → removed
     * 0 1 2 (count % 3)
     *
     * When a bit appears the 3rd time:
     * - it is removed from `ones`
     * - it is removed from `twos`
     *
     * - No bit can exist in both ones and twos
     * - Bits appearing 3 times are automatically cleared
     * - `ones` finally holds the number that appeared exactly once
     * 
     * --------------------------------------------------------------------
     * DRY RUN:
     *
     * Input: nums = {2, 2, 3, 2}
     *
     * Binary:
     * 2 = 10
     * 3 = 11
     *
     * Initialize:
     * ones = 00
     * twos = 00
     *
     * ------------------------------------------------
     * num = 2 (10)
     *
     * ones = (00 ^ 10) & ~00
     * = 10 & 11
     * = 10
     *
     * twos = (00 ^ 10) & ~10
     * = 10 & 01
     * = 00
     *
     * ones = 10, twos = 00
     *
     * ------------------------------------------------
     * num = 2 (10)
     *
     * ones = (10 ^ 10) & ~00
     * = 00 & 11
     * = 00
     *
     * twos = (00 ^ 10) & ~00
     * = 10 & 11
     * = 10
     *
     * ones = 00, twos = 10
     *
     * ------------------------------------------------
     * num = 3 (11)
     *
     * ones = (00 ^ 11) & ~10
     * = 11 & 01
     * = 01
     *
     * twos = (10 ^ 11) & ~01
     * = 01 & 10
     * = 00
     *
     * ones = 01, twos = 00
     *
     * ------------------------------------------------
     * num = 2 (10)
     *
     * ones = (01 ^ 10) & ~00
     * = 11 & 11
     * = 11
     *
     * twos = (00 ^ 10) & ~11
     * = 10 & 00
     * = 00
     *
     * ones = 11 → which is 3 (ANSWER)
     *
     */
    public static int singleNumberOptimal(int[] nums) {
        int ones = 0, twos = 0;

        for (int num : nums) {
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }

        // ones contains the number that appears exactly once
        return ones;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 2, 3, 2 };
        System.out.println(singleNumber(nums)); // Sorting approach
        System.out.println(singleNumberOptimal(nums)); // Optimal bit approach
    }
}
