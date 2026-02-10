package Stack;

public class RainWaterTrapping {

    /*
     * BRUTE FORCE / PREFIX-SUFFIX APPROACH
     *
     * Idea:
     * For every index i:
     * Water trapped = min(leftMax, rightMax) - height[i]
     *
     * Steps:
     * 1. Build prefix max array (left max for each index)
     * 2. Build suffix max array (right max for each index)
     * 3. Calculate trapped water for each bar
     *
     * Time Complexity: O(3n)
     * Space Complexity: O(2n)
     */
    public static int rainTrapBrute(int[] arr) {

        int n = arr.length;
        int totalWater = 0;

        int[] prefix = new int[n]; // prefix[i] = max height from 0 to i
        int[] suffix = new int[n]; // suffix[i] = max height from i to n-1

        // Build prefix max array
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = Math.max(arr[i], prefix[i - 1]);
        }

        // Build suffix max array
        suffix[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(arr[i], suffix[i + 1]);
        }

        // Calculate trapped water at each index
        for (int i = 0; i < n; i++) {
            int waterAtI = Math.min(prefix[i], suffix[i]) - arr[i];
            totalWater += waterAtI;
        }

        return totalWater;
    }

    /*
     * OPTIMIZED TWO POINTER APPROACH
     *
     * Idea:
     * - Use two pointers (left & right)
     * - Maintain leftMax and rightMax
     * - Always move the pointer with smaller height
     *
     * Why it works:
     * Water trapped depends on the smaller boundary
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int trap(int[] arr) {

        int left = 0, right = arr.length - 1;
        int leftMax = 0, rightMax = 0;
        int totalWater = 0;

        while (left < right) {

            // Left side is smaller
            if (arr[left] <= arr[right]) {
                
                if (arr[left] < leftMax) {
                    totalWater += leftMax - arr[left];
                } else {
                    leftMax = arr[left];
                }
                left++;

            }
            // Right side is smaller
            else {

                if (arr[right] < rightMax) {
                    totalWater += rightMax - arr[right];
                } else {
                    rightMax = arr[right];
                }
                right--;
            }
        }

        return totalWater;
    }

    public static void main(String[] args) {

        int[] arr = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };

        System.out.println(rainTrapBrute(arr)); // Output: 6
        System.out.println(trap(arr)); // Output: 6
    }
}