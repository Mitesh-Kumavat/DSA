/*
 * Problem Statement: Given a 32-bit signed integer, reverse digits of an integer.
 * Leetcode: https://leetcode.com/problems/reverse-integer/
 */
class ReverseNumber {
    public static int reverse(int x) {
        int temp = x;
        long rev = 0;

        while (temp != 0) {
            rev = 10 * rev + temp % 10;
            temp = temp / 10;
        }
        if (rev == (int) rev) // this condition makes sure there has been no overflow
            return (int) rev; // print output in required data type
        else
            return 0;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-12300));
    }
}