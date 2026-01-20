package BitManipulation;

public class DivideTwoIntegers {

    /*
     * Idea:
     * we subtract largest possible multiples of divisor using powers of 2.
     * - Similar to how division is done in binary.
     * 
     * Example:
     * 10 / 3
     * 3 * 2^1 = 6 -> subtract
     * 3 * 2^0 = 3 -> subtract
     * quotient = 2 + 1 = 3
     */

    public static int divide(int dividend, int divisor) {
        // Overflow case: -2^31 / -1 = 2^31 (out of int range)
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        // If divisor is 1, return dividend directly
        if (divisor == 1)
            return dividend;

        // Result is positive if both have same sign
        boolean isPositive = (dividend >= 0 && divisor >= 0) ||
                (dividend < 0 && divisor < 0);

        // Use long to safely handle Integer.MIN_VALUE
        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long quotient = 0;

        // Keep subtracting until dividend becomes smaller than divisor
        while (n >= d) {

            long temp = d; // current multiple of divisor
            long count = 1; // corresponding power of 2

            /*
             * Keep doubling temp until it exceeds dividend
             * temp = d * 2^k
             * count = 2^k
             */
            while ((temp << 1) <= n) {
                temp <<= 1; // temp = temp * 2
                count <<= 1; // count = count * 2
            }

            // Subtract the largest possible multiple
            n -= temp;

            // Add power-of-two count to quotient
            quotient += count;
        }

        // Apply sign and return
        return isPositive ? (int) quotient : (int) -quotient;
    }

    public static void main(String[] args) {

        /*
         * DRY RUN (10 / 3)
         * 
         * dividend = 10, divisor = 3
         * 
         * n = 10, d = 3
         * 
         * Loop 1:
         * temp = 3, count = 1
         * temp << 1 = 6 <= 10 → double
         * temp = 6, count = 2
         * temp << 1 = 12 > 10 → stop
         * 
         * n = 10 - 6 = 4
         * quotient = 2
         * 
         * Loop 2:
         * temp = 3, count = 1
         * temp << 1 = 6 > 4 → stop
         * 
         * n = 4 - 3 = 1
         * quotient = 3
         * 
         * Loop ends (n < d)
         * 
         * Result = 3
         */

        System.out.println(divide(10, 3)); // Output: 3
    }
}
