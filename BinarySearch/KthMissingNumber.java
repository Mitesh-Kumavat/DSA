package BinarySearch;

public class KthMissingNumber {

    // Brute force:
    // We will use a loop to traverse the array.
    // If arr[i] <= k: we will simply increase the value of k by 1.
    // Otherwise, we will break out of the loop.
    // Finally, we will return the value of k.
    // Note: The main idea is to shift k by 1 step if the current element is smaller
    // or equal to k. And whenever we get a number > k, we can conclude that k is
    // the missing number.
    // TC: O(N)
    public static int kthMissingNumberBrute(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= k) {
                k++;
            } else {
                break;
            }
        }

        return k;
    }

    // Optimal: Binary search
    // Explaination:
    // https://takeuforward.org/arrays/kth-missing-positive-number/
    // TC: O(log n)
    public static int kthMissingNumber(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int missing = arr[mid] - (mid + 1);

            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return k + low;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 4, 7, 11 };
        int k = 5;
        System.out.println(kthMissingNumberBrute(arr, k));
    }
}
