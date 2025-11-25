package BinarySearch;

public class MedianOfSortedArray {

    /**
     * Brute-force approach to find the median of two sorted arrays.
     *
     * INTUITION:
     * -------------------------------------------------------------
     * - We have two arrays sorted in non-decreasing order.
     * - The simplest way to find the median is:
     * 1. Merge both arrays into one sorted array (just like merge step in merge
     * sort)
     * 2. Find the median in the merged array.
     *
     * TIME COMPLEXITY:
     * -------------------------------------------------------------
     * - Merging both arrays → O(n1 + n2)
     * - Median lookup → O(1)
     * - Total = O(n1 + n2)
     *
     * SPACE COMPLEXITY:
     * -------------------------------------------------------------
     * - We create a new array of size (n1 + n2)
     * - Space = O(n1 + n2)
     *
     * This is not optimal (optimal is O(log(min(n1,n2)))) but perfect
     * for understanding the problem, and easy to implement.
     */
    public static double medianOfArrBrute(int[] arr1, int[] arr2) {
        double ans = 0;

        int i = 0;
        int j = 0;
        int count = 0;

        int totalLength = arr1.length + arr2.length;

        int newArr[] = new int[totalLength];

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                newArr[count++] = arr1[i++];
            } else {
                newArr[count++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            newArr[count++] = arr1[i++];
        }

        while (j < arr2.length) {
            newArr[count++] = arr2[j++];
        }

        if (totalLength % 2 == 0) {
            ans = (newArr[totalLength / 2] + newArr[totalLength / 2 - 1]) / 2.0;
        } else {
            ans = newArr[totalLength / 2];
        }

        return ans;
    }

    /**
     * BETTER (TWO-POINTER) APPROACH — O(n1 + n2) Time, O(1) Extra Space
     * ------------------------------------------------------------------------
     * INTUITION:
     * We don't need the full merged array.
     * For finding the median, we only need:
     * - element at index `ind1 = (n/2 - 1)` → left median (for even n)
     * - element at index `ind2 = n/2` → right median
     *
     * Instead of storing all elements, we simulate the merge (like merge sort)
     * and stop when we reach these two required indices.
     *
     * This avoids extra memory usage and is faster in practice.
     */
    public static double medianOfArrBetter(int[] a, int[] b) {
        // Get sizes of both arrays
        int n1 = a.length, n2 = b.length;

        // Total size
        int n = n1 + n2;

        // Median indices
        int ind2 = n / 2;
        int ind1 = ind2 - 1;

        // Initialize pointers and values
        int cnt = 0, i = 0, j = 0;
        int ind1el = -1, ind2el = -1;

        // Merge step to find median
        while (i < n1 && j < n2) {
            if (a[i] < b[j]) {
                if (cnt == ind1)
                    ind1el = a[i];
                if (cnt == ind2)
                    ind2el = a[i];
                i++;
            } else {
                if (cnt == ind1)
                    ind1el = b[j];
                if (cnt == ind2)
                    ind2el = b[j];
                j++;
            }
            cnt++;
        }

        // Remaining elements in a
        while (i < n1) {
            if (cnt == ind1)
                ind1el = a[i];
            if (cnt == ind2)
                ind2el = a[i];
            cnt++;
            i++;
        }

        // Remaining elements in b
        while (j < n2) {
            if (cnt == ind1)
                ind1el = b[j];
            if (cnt == ind2)
                ind2el = b[j];
            cnt++;
            j++;
        }

        // Return median
        if (n % 2 == 1)
            return (double) ind2el;
        return (ind1el + ind2el) / 2.0;
    }

    public static double findMedianSortedArrays(int[] a, int[] b) {
        /**
         * OPTIMAL SOLUTION — BINARY SEARCH ON PARTITION
         * ---------------------------------------------------------------
         * TIME: O(log(min(n1, n2)))
         * SPACE: O(1)
         *
         * WHY THIS PROBLEM IS HARD:
         * - You cannot merge fully — O(n) time.
         * - You must find the correct “partition point“ between the two arrays.
         * - The left half must contain exactly half of the elements of the
         * combined sorted array.
         *
         * KEY IDEA:
         * ---------------------------------------------------------------
         * We perform binary search on the *smaller* array to decide:
         * How many elements should come from a ?
         * How many from b ?
         *
         * We choose cut1 in 'a': (elements taken from a to left half)
         * cut2 is automatically determined for 'b':
         *
         * cut2 = (totalElements + 1)/2 – cut1
         *
         * Now LEFT half = a[0 … cut1-1] + b[0 … cut2-1]
         * and RIGHT half = a[cut1 … end] + b[cut2 … end]
         *
         * VALID PARTITION CONDITIONS:
         * - every element on LEFT <= every element on RIGHT
         *
         * That means:
         * l1 <= r2 AND l2 <= r1
         *
         * Where:
         * l1 = last element of left half from a
         * r1 = first element of right half from a
         * l2 = last element from b
         * r2 = first element from b
         *
         * IF VALID PARTITION:
         * - If total is EVEN → median = average(max(l1, l2), min(r1, r2))
         * - If total is ODD → median = max(l1, l2)
         *
         * IF NOT VALID:
         * - if l1 > r2 → cut1 is too big → move LEFT (high = cut1 - 1)
         * - else → cut1 too small → move RIGHT (low = cut1 + 1)
         */

        // Ensure 'a' is the smaller array to limit the binary search space
        if (a.length > b.length)
            return findMedianSortedArrays(b, a);

        int n1 = a.length, n2 = b.length;
        int low = 0, high = n1;

        // Binary search on array 'a'
        while (low <= high) {

            // cut1 = #elements taken from a in left partition
            int cut1 = (low + high) / 2;

            // cut2 = take remaining elements from b
            int cut2 = (n1 + n2 + 1) / 2 - cut1;

            // Elements just before and after the partition
            // Use MIN/MAX values for boundary safety
            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : a[cut1 - 1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : b[cut2 - 1];
            int r1 = (cut1 == n1) ? Integer.MAX_VALUE : a[cut1];
            int r2 = (cut2 == n2) ? Integer.MAX_VALUE : b[cut2];

            // Check if this partition is correct
            if (l1 <= r2 && l2 <= r1) {

                // Even total length → average of two middle values
                if ((n1 + n2) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }

                // Odd total length → the middle is the max of left side
                return Math.max(l1, l2);
            }

            // If l1 > r2, we took too many elements from 'a'
            else if (l1 > r2) {
                high = cut1 - 1;
            }

            // Else, we need to take more elements from 'a'
            else {
                low = cut1 + 1;
            }
        }

        return 0.0; // Should never reach here in valid input
    }
    // 1. Always binary search smaller array.
    // 2. Partition both arrays so left contains half elements.
    // 3. leftA_last = l1, rightA_first = r1
    //    leftB_last = l2, rightB_first = r2
    // 4. Correct partition:
    // l1 <= r2 && l2 <= r1
    // 5. If l1 > r2 → move left (high = cut1 - 1)
    // Else → move right (low = cut1 + 1)
    // 6. Median:
    // odd → max(l1, l2)
    // even → (max(l1, l2) + min(r1, r2)) / 2

    public static void main(String[] args) {
        int[] arr1 = { 1, 3 };
        int[] arr2 = { 2 };
        System.out.println(findMedianSortedArrays(arr1, arr2));
    }
}
