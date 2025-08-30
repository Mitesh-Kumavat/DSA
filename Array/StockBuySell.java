package Array;

public class StockBuySell {
    // Brute Force: we will check for every pair and store the proffit if its
    // greater than previos stored proffit
    // TC: O(N^2)
    // SC: O(1)
    public static int maxProffit(int[] arr) {
        int proffit = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[j] >= arr[i] && proffit < arr[j] - arr[i]) {
                    proffit = Math.max(proffit, arr[j] - arr[i]);
                }
            }
        }

        return proffit;
    }

    // Optimized Approach: We will initialize minimum element with Max value,
    // and max proffit with 0
    // we will iterate through every element and store if its min than prev min
    // and at the same time we will calculate proffit and update if its > prev proffit
    // TC: O(N)
    // SC: O(1)
    public static int maxProffitOptimied(int[] arr) {
        int maxProffit = 0;
        int mini = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            mini = Math.min(arr[i], mini);
            maxProffit = Math.max(maxProffit, arr[i] - mini);
        }
        return maxProffit;
    }

    public static void main(String[] args) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        int[] prices2 = { 7, 6, 4, 3, 1 };
        System.out.println(maxProffitOptimied(prices));
        System.out.println(maxProffit(prices2));
    }
}
