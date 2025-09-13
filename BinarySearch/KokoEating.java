package BinarySearch;
// Problem: https://leetcode.com/problems/koko-eating-bananas/description/

public class KokoEating {

    // Brute force:
    // the answer will aways lies from 1 to Max element of array
    // in this example range is : (1 - 11)
    // loop through 1 to 11 and check if i's ans is <= hour
    // it might be our answer.
    // TC: O(maxElement * n)
    // this will give you Time limit exceed error in leetcode

    // Optimal Approach: Using binary seach
    // we know for sure that ans will always be in the given range only
    // which is [1....11], so we can apply binary search on this

    // low -> 1, high -> 11, mid -> 6
    // Now we will check for 6 if its ans is <= hour then this might be our answer
    // so we will eliminate right portion and store 6 as our answer and go to left
    // we go left because we need minimum answer

    // low -> 1, high -> 5, mid -> 3
    // now our mid will be at 3, but it is not our answer because if we select 3
    // bananas/hr then our ans k will be > hour so we have to go to right

    // low -> 4, high -> 5, mid -> 4
    // now we have 4 whose ans is exactly as hour, so we will store it
    // and now as per the BS we will go to left again
    // but the moment we go left our high crosses left so we will return our ans

    // TC: O(log(maxElement of array) * N)
    // SC: O(1)
    public static int minEatingSpeed(int[] arr, int h) {

        int max = Integer.MIN_VALUE;

        // Find the max element from array
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= max) {
                max = arr[i];
            }
        }

        int low = 1;
        int high = max;
        int ans = 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            boolean valid = checkHourlyRate(arr, mid, h);

            if (valid) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        // we can also return low variable because it will always point to our ans
        return ans;
    }

    public static boolean checkHourlyRate(int[] arr, int hourlyRate, int h) {

        int totalTime = 0;

        for (int i = 0; i < arr.length; i++) {
            totalTime += Math.ceil((double) arr[i] / hourlyRate);
        }

        if (totalTime > h) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] piles = { 3, 6, 7, 11 };
        int hour = 8;
        System.out.println(minEatingSpeed(piles, hour));
    }
}
