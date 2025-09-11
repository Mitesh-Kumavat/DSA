package BinarySearch;

// What is peak element
// arr[i-1] < arr[i] > arr[i+1]
// return the index of peak element
public class PeakElement {

    // Brute force: Linearly check every element
    // TC: O(N)
    public static int peakElementBrute(int[] arr) {
        int n = arr.length;

        if (n == 1) {
            return 0;
        }

        for (int i = 0; i < n - 1; i++) {

            // for the first element
            if (i == 0 && arr[i] > arr[i + 1]) {
                return i;
            }

            if (i != 0 && arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                return i;
            }
        }

        // for the last element
        if (arr[n - 1] > arr[n - 2]) {
            return n - 1;
        }

        return -1;
    }

    // Optimal: https://takeuforward.org/data-structure/peak-element-in-array/
    // TC: O(log N)
    public static int peakElement(int[] arr) {
        int n = arr.length;

        // Some edge cases
        if (n == 1)
            return 0;

        if (arr[0] > arr[1])
            return 0;

        if (arr[n - 1] > arr[n - 2])
            return n - 1;

        int low = 1;
        int high = n - 2;

        while (low <= high) {
            int mid = (low + high) / 2;

            // if arr[mid] is peak element then return it
            if (arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) {
                return mid;
            } else if (arr[mid] > arr[mid - 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 1, 3, 5, 6, 4 };
        // here the answer is 2 and 6 so we can return any one we want
        System.out.println(peakElementBrute(arr));
        System.out.println(peakElement(arr));
    }
}
