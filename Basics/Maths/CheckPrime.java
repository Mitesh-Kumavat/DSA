package Basics.Maths;

public class CheckPrime {

    public static boolean isPrime(int n) {
        // Brute Force Approach
        // TC: O(n)
        // SC: O(1)

        if (n <= 1) {
            return false;
        }

        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                cnt++;
            }
        }

        return cnt == 0;
    }

    public static boolean isPrimeOptimized(int n) {
        // Optimized Approach
        // TC: O(sqrt(n))
        // SC: O(1)

        if (n <= 1) {
            return false;
        }

        // Explaination: we only need to check for factors up to sqrt(n).
        // we start from 2 and go up to sqrt(n). If we find any factor in this range,
        // we return false. If no factors are found, we return true.

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int n = 14;
        if (isPrime(n)) {
            System.out.println(n + " is a prime number.");
        } else {
            System.out.println(n + " is not a prime number.");
        }
    }
}
