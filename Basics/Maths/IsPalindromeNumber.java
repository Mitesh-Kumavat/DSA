package Basics.Maths;

/*
 * Determine whether an integer is a palindrome. Do this without extra space.
 * Leetcode:https://leetcode.com/problems/palindrome-number/
 * 
 * -> Test case:
 * Input: 121
 * Output: true
 * 
 * Input: -121
 * Output: false
 * 
 * Input: 10
 * Output: false
 */
public class IsPalindromeNumber {
    public static boolean isPalindrome(int x) {
        int temp = x;
        long rev = 0;
        while (x != 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        if (temp == (int) rev && temp >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(000));
    }
}
