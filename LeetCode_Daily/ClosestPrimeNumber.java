/*
 * Leetcode: https://leetcode.com/problems/closest-prime-numbers-in-range/
 * Date: 07-03-25
 */

package LeetCode_Daily;

import java.util.ArrayList;
import java.util.Arrays;

public class ClosestPrimeNumber {

    public static boolean isPrime(int n) {
        if (n <= 1) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)  return false;
        }

        return true;
    }

    public static int[] closestPrimes(int left, int right) {
        int[] ans = new int[2];
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isPrime(i)) {
                arr.add(i);
            }
        }

        System.out.println(arr);
        if (arr.isEmpty() || arr.size() < 2) {
            ans[0] = -1;
            ans[1] = -1;
            return ans;
        } else {
            ans[0] = arr.get(0);
            ans[1] = arr.get(1);
            int diff = ans[0] - ans[1];
            
            for (int i = 0; i <= arr.size() - 2; i++) {
                if (diff < arr.get(i) - arr.get(i + 1)) {
                    ans[0] = arr.get(i);
                    ans[1] = arr.get(i + 1);
                    diff = ans[0] - ans[1];
                }
            }

            return ans;
        }

    }

    public static void main(String[] args) {
        int left = 18, right = 72;
        System.out.println(Arrays.toString(closestPrimes(left, right)));
    }
}
