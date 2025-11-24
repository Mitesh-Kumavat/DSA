package BinarySearch;

import java.util.PriorityQueue;

class Pair {
    double distance;
    int index;

    Pair(double distance, int index) {
        this.distance = distance;
        this.index = index;
    }
}

public class DistanceBetween {

    /**
     * Brute-force approach to minimize the maximum distance between gas stations.
     *
     * Given an array of existing station positions and K new stations to add,
     * we repeatedly place each new station inside the interval which currently
     * has the largest section length.
     *
     * Example:
     * arr = [1, 2, 3, 4, 5], k = 4
     * We want to add 4 extra stations between these points such that the
     * longest gap between consecutive stations becomes as small as possible.
     *
     * HOW THIS BRUTE FORCE LOGIC WORKS:
     * ---------------------------------------------------------------------
     * - For each interval (arr[i] → arr[i+1]), we keep track of how many
     * new stations we have already placed in that interval.
     *
     * - Every time we place a station, we:
     * 1. Find the interval with the current largest section length.
     * (sectionLength = intervalLength / (howMany[i] + 1))
     * 2. Add a new station inside that interval by incrementing howMany[i].
     *
     * - After placing all k stations, we compute the maximum section length
     * across all intervals — that value is our final answer.
     *
     * TIME COMPLEXITY:
     * - Outer loop: k iterations
     * - Inner loop: O(n)
     * - Total: O(k * n)
     *
     * This is slow for large inputs but easy to understand logically.
     */
    public static double minimiseMaxDistanceBetweenGasStationsBrute(int[] arr, int k) {

        // howMany[i] = number of extra stations placed between arr[i] and arr[i+1]
        int[] howMany = new int[arr.length - 1];

        // Place k new gas stations
        for (int i = 0; i < k; i++) {

            double maxDistance = -1; // tracks the biggest current section length
            int maxIndex = -1; // interval index where section is largest

            // Check each interval
            for (int j = 0; j < arr.length - 1; j++) {

                // Distance between arr[j] and arr[j+1]
                double diff = arr[j + 1] - arr[j];

                /*
                 * Each added station splits an interval into (howMany[j] + 1) sections.
                 * Example: If interval length = 10 and 2 stations placed inside,
                 * sections = 3
                 * sectionLength = 10 / 3 = 3.33
                 *
                 * We want the interval with the largest sectionLength.
                 */
                double sectionLength = diff / (howMany[j] + 1.0);

                // Track the interval with the current largest section
                if (sectionLength > maxDistance) {
                    maxDistance = sectionLength;
                    maxIndex = j;
                }
            }

            // Place 1 new gas station in the interval with maximum section length
            howMany[maxIndex]++;
        }

        // After placing all K stations, compute the largest final section length
        double ans = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            double diff = arr[i + 1] - arr[i];
            double sectionLength = diff / (howMany[i] + 1.0);

            ans = Math.max(ans, sectionLength);
        }

        return ans;
    }

    public static double minimiseMaxDistanceBetweenGasStationsUsingHeap(int[] arr, int k) {

        /**
         * OPTIMIZED APPROACH USING MAX-HEAP
         * -----------------------------------
         *
         * WHY THIS WORKS:
         * Instead of scanning all intervals every time (O(n) per placement),
         * we store intervals in a max-heap, where the interval with the
         * largest current section length stays at the top.
         *
         * Steps:
         * 1. Push every interval into a max-heap with:
         * - current max section length
         * - interval index
         *
         * 2. For each of the k new stations:
         * - Pop the interval having the **largest** section length
         * - Add a station inside it → this reduces the largest section length
         * - Recalculate new section length
         * - Push updated interval back into heap
         *
         * 3. After all k placements, heap top = maximum minimized section length.
         *
         * WHY THIS IS BETTER:
         * - Instead of O(k * n)
         * - Heap approach costs O(k * log n)
         * This is significantly faster when k is large.
         */

        // howMany[i] tracks how many new stations have been added to interval i
        int[] howMany = new int[arr.length - 1];

        /**
         * Max heap storing Pair(distance, index)
         * - distance = current largest section length of interval
         * - index = which interval this distance belongs to
         *
         * Comparator sorts in descending order → max heap
         */
        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(b.distance, a.distance));

        // Initialize heap with all intervals (their initial distances)
        for (int i = 0; i < arr.length - 1; i++) {
            double initialDist = arr[i + 1] - arr[i]; // only 1 section initially
            pq.add(new Pair(initialDist, i));
        }

        // Place K stations optimally
        for (int i = 1; i <= k; i++) {

            // Fetch the interval having the largest current section length
            Pair top = pq.poll();
            int idx = top.index;

            // Add one more station in this interval
            howMany[idx]++;

            // Recalculate section length:
            // intervalLength / number_of_sections
            // number_of_sections = howMany[idx] + 1
            double totalDist = arr[idx + 1] - arr[idx];
            double newDist = totalDist / (howMany[idx] + 1);

            // Push the updated interval back to the heap
            pq.add(new Pair(newDist, idx));
        }

        /**
         * After placing all K stations,
         * the top of the heap contains the interval with the LARGEST minimized section.
         *
         * That value is the final minimized maximum distance.
         */
        return pq.poll().distance;
    }

    /**
     * Optimal solution using Binary Search on the answer.
     *
     * Intuition:
     * We assume a possible maximum allowed distance "mid".
     * Then we calculate how many stations are required so that
     * all gaps are reduced to <= mid.
     *
     * If requiredStations <= k → mid is feasible.
     * If requiredStations > k → mid is too small, increase mid.
     *
     * We binary search on mid until the answer converges.
     */
    public static double minimiseMaxDistanceOptimal(int[] arr, int k) {

        double low = 0;
        double high = 0;

        // Find initial search boundaries: high = largest existing gap
        for (int i = 0; i < arr.length - 1; i++) {
            high = Math.max(high, arr[i + 1] - arr[i]);
        }

        // Precision for binary search
        double eps = 1e-6;

        // Binary search on the possible answer
        while (high - low > eps) {

            double mid = (low + high) / 2.0;

            // How many stations needed if max allowed distance = mid?
            if (canBeLessOrEqual(arr, k, mid)) {
                // mid is possible → try to reduce it
                high = mid;
            } else {
                // mid is not possible → increase it
                low = mid;
            }
        }

        return high; // high (or low) both converge to same minimal value
    }

    /**
     * Greedy helper:
     * Given a max allowed distance "dist",
     * return whether we can achieve it using <= k stations.
     */
    private static boolean canBeLessOrEqual(int[] arr, int k, double dist) {

        int requiredStations = 0;

        for (int i = 0; i < arr.length - 1; i++) {

            double gap = arr[i + 1] - arr[i];

            // Number of stations needed to ensure each segment <= dist
            // Example: gap = 9, dist = 3 → need floor(9 / 3) = 3 stations
            // Actually need stations = floor(gap / dist)
            int needed = (int) (gap / dist);

            // If gap divides evenly (like 9%3=0), we subtract 1
            // because placing 2 stations gives 3 segments
            if (Math.abs((needed * dist) - gap) < 1e-12) {
                needed--;
            }

            requiredStations += needed;
        }

        return requiredStations <= k;
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5 };
        int k = 4;

        System.out.println(minimiseMaxDistanceBetweenGasStationsBrute(arr, k));
        System.out.println(minimiseMaxDistanceBetweenGasStationsUsingHeap(arr, k));
    }
}
