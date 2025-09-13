package BinarySearch;

public class MinDaysBouquets {

    public static boolean check(int[] arr, int m, int k, int value) {

        int pair = 0; // adjacent pairs
        int count = 0; // total generated bouquets

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= value) {
                pair++;

                if (pair == k) { // if we have got k flowers pair then increase the bouquets num
                    count++;
                    pair = 0;
                }
                if (count >= m) { // if we have generated m bouquets then return true
                    return true;
                }
            } else {
                pair = 0; // if the pair is broken then reset the pair
            }
        }

        return count >= m;
    }

    // Optimal approach: Binary Search
    // Since the answer always lies between 1 to max(arr),
    // we can apply binary search on this range.
    // For each mid = (low + high)/2 we check:
    // - Can we make at least m bouquets with mid days?
    // If YES, then mid might be the answer,
    // but we try to minimize it, so we move left (high = mid - 1).
    // If NO, then we need more days, so we move right (low = mid + 1).
    // TC: O(n * log(maxElement))
    // SC: O(1)
    public static int minDays(int[] arr, int m, int k) {
        int n = arr.length;

        // If total flowers required are more than available, not possible
        if (m * k > n)
            return -1;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int low = 1;
        int high = max;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (check(arr, m, k, mid)) {
                // if possible to form bouquets in mid days
                // try to minimize further -> go left
                high = mid - 1;
            } else {
                // not possible in mid days -> need more days
                // go right
                low = mid + 1;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[] arr = { 62, 75, 98, 63, 47, 65, 51, 87, 22, 27, 73, 92, 76, 44, 13, 90, 100, 85 };
        int m = 2;
        int k = 7;
        System.out.println(minDays(arr, m, k));
    }
}
