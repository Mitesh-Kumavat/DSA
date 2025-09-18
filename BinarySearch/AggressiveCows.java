package BinarySearch;

import java.util.Arrays;

public class AggressiveCows {

    public static boolean canWePlace(int[] stalls, int dist, int cows) {
        int n = stalls.length; // size of array
        int cntCows = 1; // no. of cows placed
        int last = stalls[0]; // position of last placed cow.

        for (int i = 1; i < n; i++) {
            if (stalls[i] - last >= dist) {
                cntCows++; // place next cow.
                last = stalls[i]; // update the last location.
            }
            if (cntCows >= cows)
                return true;
        }
        return false;
    }

    // TC: O(n log m) , m = max-min
    // SC: O(1)
    // function to find the largest minimum distance.
    // Explaination: we will apply binary search on the distance.
    // we will check for every mid distance that can we place the cows
    // at least at that distance.
    // if yes then we will try for larger distance else smaller distance.
    // Better explaination in :
    // http://takeuforward.org/data-structure/aggressive-cows-detailed-solution/
    public static int aggressiveCows(int[] stalls, int k) {
        int n = stalls.length;
        // sort the stalls[]:
        Arrays.sort(stalls);

        int low = 1, high = stalls[n - 1] - stalls[0];
        // apply binary search:
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canWePlace(stalls, mid, k) == true) {
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return high;
    }

    public static void main(String[] args) {
        int[] stalls = { 1, 2, 4, 8, 9 };
        int cows = 3;
        System.out.println(aggressiveCows(stalls, cows));
    }
}
