package BinarySearch;

// Same as Lower bound just one change in condition
// Condition: arr[mid] > x

public class UpperBound {

    public static int upperBound(int[] arr, int x) {
        int n = arr.length;
        int ans = n;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            // if our condition satisfy that means mid might be an answer
            // but we still need to find the smallest index so go left
            if (arr[mid] > x) {
                ans = mid;
                high = mid - 1;
            } else { // and if not then go the right part of the array
                low = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 5, 8, 15, 19 };
        int x = 8;
        System.out.println(upperBound(arr, x));
    }
}
