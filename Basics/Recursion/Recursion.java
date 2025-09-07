package Basics.Recursion;

public class Recursion {

    public static void print1ToN(int n) {
        if (n <= 0) {
            return;
        }
        print1ToN(n - 1);
        System.out.println(n);
    }

    public static void printNameNTimes(String name, int n) {
        if (n <= 0) {
            return;
        }

        System.out.println(name);
        printNameNTimes(name, n - 1);
    }

    public static void printNto1(int n) {
        if (n <= 0) {
            return;
        }

        System.out.print(n + " ");
        printNto1(n - 1);
    }

    public static int sum(int n) {
        if (n <= 0) {
            return 0;
        }

        int ans = n + sum(n - 1);
        return ans;
    }

    public static int fibo(int n) {
        if (n <= 0)
            return 0;

        if (n <= 1)
            return 1;

        int ans = fibo(n - 1) + fibo(n - 2);
        return ans;
    }

    public static int fact(int n) {
        if (n <= 1)
            return 1;

        return n * fact(n - 1);
    }

    public static void reverseArray(int i, int[] arr, int length) {
        if (i >= length / 2) {
            return;
        }

        int temp = arr[i];
        arr[i] = arr[length - i - 1];
        arr[length - i - 1] = temp;

        reverseArray(i + 1, arr, length);

    }

    public static boolean isPalindrome(String s, int i) {
        if (i >= s.length() / 2) {
            return true;
        }

        if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
            return false;
        }
        return isPalindrome(s, i + 1);
    }

    public static void main(String[] args) {
        // print1ToN(5);
        // printNameNTimes("Learn DSA", 5);
        // printNto1(8);
        // System.out.println(sum(5));
        // System.out.println(fibo(4));
        // System.out.println(fact(4));

        // int[] arr = { 1, 5, 3, 52, 34 };
        // reverseArray(0, arr, arr.length);
        // for (int i = 0; i < arr.length; i++) {
        // System.out.print(arr[i] + " ");
        // }

        System.out.println(isPalindrome("MADAM", 0));
        System.out.println(isPalindrome("MADA", 0));
        System.out.println(isPalindrome("MAM", 0));
        System.out.println(isPalindrome("A", 0));
        System.out.println(isPalindrome("", 0));
    }
}