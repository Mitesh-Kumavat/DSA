package BinarySearch;

public class BinarySearchCode {

    // This is the Iterative approach without recursion
    // Simplest and easiest
    public static int binarySearchIterative(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (target > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    // Using recursion
    public static int binarySearchRecursive(int[] arr, int low, int high, int target) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (target > arr[mid]) {
            return binarySearchRecursive(arr, mid + 1, high, target);
        } else {
            return binarySearchRecursive(arr, low, mid - 1, target);
        }
    }

    // Time Complexity: O(logN) || NOTE: log base 2
    public static void main(String[] args) {
        int[] arr = { -1, 0, 3, 5, 9, 12 };
        int target = 9;
        System.out.println(binarySearchIterative(arr, target));
        System.out.println(binarySearchRecursive(arr, 0, arr.length, target));
    }
}