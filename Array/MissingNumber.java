package Array;

public class MissingNumber {

    // Optimal Method 1 Using SUM:
    // TC: O(n)
    // SC: O(1)
    // we know that there will be number from 1 -> n
    // so that we can apply the formula : n * (n+1)/2
    // ANS: subtract sum with the sum of the array elements
    public static int findMissingUsingSumMethod(int[] a) {
        int n = a.length;
        int sum = (n * (n + 1)) / 2;
        int sumOfArray = 0;
        for (int i : a) {
            sumOfArray += i;
        }

        return sum - sumOfArray;
    }

    // Optimal Method 2 Using XOR:
    // TC: O(n)
    // SC: O(1)
    // we know that a^a = 0 and a^0 = a
    // so we will take xor of all the elements of the array and store it in xor
    // then we will take xor of all the elements from 1 to n and store it in xor1
    // then we will take xor of both the xor and xor1
    // and return the result
    public static int findMissingUsingXOR(int[] a) {
        int xor1 = 0;
        int xor2 = 0;

        for (int i = 0; i < a.length; i++) {
            xor2 = xor2 ^ a[i];
            xor1 = xor1 ^ (i + 1);
        }

        // xor1 = xor1 ^ a.length;

        return xor1 ^ xor2;
    }

    public static void main(String[] args) {
        int arr[] = { 3, 4, 5, 1, 0, 6, 7, 8 };
        System.out.println(findMissingUsingSumMethod(arr));
        System.out.println(findMissingUsingXOR(arr));
    }
}
