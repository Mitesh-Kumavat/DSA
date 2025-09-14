package BinarySearch;

// Problem: https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/

public class ShipCapicity {

    // Helper function: To check if we can ship all packages
    // within the given 'days' using the given 'weight' capacity.
    //
    // Idea:
    // Start filling packages one by one into the ship.
    // - If the next package fits in the current day's capacity -> add it
    // - Otherwise, start a new day with this package
    // If at any point required days exceed the given limit -> return false
    //
    // If we succeed, return true.
    public static boolean isWeightCapable(int[] arr, int weight, int days) {
        int sum = 0; // current day's weight sum
        int totalDays = 1; // start with day 1

        for (int el : arr) {
            // If package can fit in the current day
            if ((sum + el) <= weight) {
                sum += el;
            } else {
                // Start new day with this package
                sum = el;
                totalDays++;

                // If days exceed allowed limit, not possible
                if (totalDays > days) {
                    return false;
                }
            }
        }

        // If totalDays required is <= given days, it's possible
        return totalDays <= days;
    }

    // Brute force:
    // The capacity must be at least max element of array (since one big package
    // needs that much)
    // and at most sum of all elements (if we ship everything in one day).
    // So we can linearly check from max -> sum.
    // This would take O((sum - max) * n) time -> too slow.

    
    // Optimal:
    // Apply Binary Search on the answer space [max ... sum].
    // - low = max element (minimum possible capacity)
    // - high = sum of all elements (maximum possible capacity)
    // For each mid, check if shipping is possible in given days.
    // If yes, try smaller capacity (go left)
    // If no, try bigger capacity (go right).
    //
    // TC: O(N * log(sum of weights))
    // SC: O(1)
    public static int shipWithinDays(int[] arr, int days) {

        int max = Integer.MIN_VALUE;
        int high = 0;

        // Find max element and total sum
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            high += arr[i];
        }

        int low = max; // minimum capacity must be at least max element

        // Binary search for minimum capacity
        while (low <= high) {
            int mid = (low + high) / 2;

            // If it's possible with mid capacity, try smaller
            if (isWeightCapable(arr, mid, days)) {
                high = mid - 1;
            } else {
                // Otherwise, increase capacity
                low = mid + 1;
            }
        }

        // At the end, low will always point to the minimum valid capacity
        return low;
    }

    public static void main(String[] args) {
        int[] weights = { 1, 2, 3, 1, 1 };
        int days = 4;
        System.out.println(shipWithinDays(weights, days));
    }
}
