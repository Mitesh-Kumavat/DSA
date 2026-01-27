package Basics.Maths;

/*
    Sieve of Eratosthenes
    --------------------
    This algorithm is used to find how many prime numbers exist
    that are strictly LESS THAN a given number n.

    Prime number:
    A number greater than 1 which has only two divisors:
    1 and itself.
    Example: 2, 3, 5, 7, 11
*/

public class SieveOfEratosthenes {

    /*
     * Time Complexity (TC): O(n log log n)
     * Space Complexity (SC): O(n)
     * 
     * Why?
     * - We use one boolean array of size n → O(n) space
     * - Marking multiples is very fast and efficient → O(n log log n)
     */

    public static int countPrimes(int n) {

        /*
         * Step 1: Create a boolean array
         * isPrime[i] will tell whether number i is prime or not
         * 
         * - true → number is prime
         * - false → number is NOT prime
         * 
         * Initially, Java sets all values to false by default
         */
        boolean[] isPrime = new boolean[n];

        /*
         * Step 2: Assume all numbers from 2 to n-1 are prime
         * We start from 2 because:
         * - 0 and 1 are NOT prime numbers
         */
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        /*
         * Step 3: Mark non-prime numbers
         * Outer loop runs till i*i < n because:
         * - If i > sqrt(n), its multiples are already marked earlier
         * 
         * Example:
         * If n = 100
         * sqrt(100) = 10
         * So we only need to check till 10
         */
        for (int i = 2; i * i < n; i++) {

            /*
             * If current number i is already marked as NOT prime,
             * then no need to process it
             */
            if (!isPrime[i]) {
                continue;
            }

            /*
             * Step 4: Mark all multiples of i as NOT prime
             * We start from i*i because:
             * - Smaller multiples like 2*i, 3*i are already handled
             * by smaller prime numbers
             * 
             * Example:
             * For i = 3
             * Start from 3*3 = 9
             * Mark: 9, 12, 15, 18 ...
             */
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }

        /*
         * Step 5: Count how many primes are left
         * If isPrime[i] is true, then i is a prime number
         */
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        /*
         * Return total count of prime numbers
         * that are LESS THAN n
         */
        return count;
    }

    public static void main(String[] args) {

        /*
         * Example:
         * n = 10
         * Prime numbers less than 10 are:
         * 2, 3, 5, 7
         * 
         * Output should be 4
         */
        int n = 10;

        System.out.println(
                "Number of primes less than " + n + " is: " + countPrimes(n));
    }
}
