package BinarySearch;

// Problem: Given a sorted array of N integers and an integer x, write a program to find the lower bound of x.
// The lower bound is the smallest index, where arr[ind] >= x. But if any such index is not found, the lower bound algorithm returns n i.e. size of the given array.

public class LowerBound {

    // Brute force: loop throught the array, and if at any moment
    // arr[i] >= x return the i
    public static int lowerBoundBrute(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= x) {
                return i;
            }
        }
        return arr.length;
    }

    // Optmial Approach: Using Binary search
    // just tweak the binary search little bit to get the answer
    public static int lowerBound(int[] arr, int x) {
        int n = arr.length;
        int ans = n;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            // if our condition satisfy that means mid might be an answer
            // but we still need to find the smallest index so go left
            if (arr[mid] >= x) {
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
        int x = 9;

        System.out.println(lowerBoundBrute(arr, x));
        System.out.println(lowerBound(arr, x));
    }
}
