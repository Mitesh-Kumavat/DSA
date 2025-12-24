package Array;

import java.util.Arrays;

class AppleRedistribution {

    /*
     * PROBLEM:
     * Given:
     * - apple[] -> number of apples in each basket
     * - capacity[] -> capacity of each box
     *
     * TASK:
     * Find the MINIMUM number of boxes required
     * to store ALL apples.
     *
     * NOTE:
     * - Apples can be moved freely
     * - One box can hold apples from multiple baskets
     */

    public int minimumBoxes(int[] apple, int[] capacity) {

        /*
         * STEP 1: Calculate total apples
         * We don't care about individual baskets,
         * only the TOTAL number of apples matters.
         */
        int totalApples = 0;
        for (int i = 0; i < apple.length; i++) {
            totalApples += apple[i];
        }

        /*
         * STEP 2: Sort capacities
         * Greedy approach:
         * To use minimum boxes, pick the LARGEST
         * capacity boxes first.
         *
         * Sorting helps us easily access largest boxes
         * from the end of the array.
         */
        Arrays.sort(capacity);

        /*
         * minCapacityUsed -> sum of capacities used so far
         * boxCount -> number of boxes used
         */
        int minCapacityUsed = 0;
        int boxCount = 0;

        /*
         * STEP 3: Pick boxes from largest to smallest
         * Keep adding box capacities until we can
         * store all apples.
         */
        for (int i = capacity.length - 1; i >= 0; i--) {

            minCapacityUsed += capacity[i];
            boxCount++;

            /*
             * As soon as total capacity >= total apples,
             * we have our answer.
             */
            if (minCapacityUsed >= totalApples) {
                return boxCount;
            }
        }

        return boxCount;
    }

    public static void main(String[] args) {
        AppleRedistribution obj = new AppleRedistribution();

        int[] apple = { 1, 3, 2 };
        int[] capacity = { 4, 3, 1, 5, 2 };

        System.out.println(obj.minimumBoxes(apple, capacity));
    }
}
