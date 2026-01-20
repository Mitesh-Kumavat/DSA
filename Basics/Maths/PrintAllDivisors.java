package Basics.Maths;

import java.util.ArrayList;
import java.util.List;

public class PrintAllDivisors {

    public static List<Integer> getAllDivisors(int n) {
        // Brute Force Approach
        // TC: O(n)
        // SC: O(k) where k is number of divisors

        List<Integer> divisors = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
            }
        }

        return divisors;
    }

    public static List<Integer> getAllDivisorsOptimized(int n) {
        // Optimized Approach
        // TC: O(sqrt(n))
        // SC: O(k) where k is number of divisors

        // Explanation:
        // Instead of checking all numbers from 1 to n, we only check up to sqrt
        // n. For each divisor i found, we also add n/i as a divisor.

        List<Integer> divisors = new ArrayList<>();

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i != n / i) { // to avoid adding the square root twice
                    divisors.add(n / i);
                }
            }
        }

        return divisors;
    }

    public static void main(String[] args) {
        int n = 36;
        List<Integer> divisors = getAllDivisors(n);
        System.out.println("Divisors of " + n + " are: " + divisors);
    }
}
