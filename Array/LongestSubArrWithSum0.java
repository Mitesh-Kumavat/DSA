package Array;

import java.util.HashMap;

public class LongestSubArrWithSum0 {

    // Brute - > Genearte all the subarray using 2 loops
    // now use 3rd loop to check the sum of generated subarray
    // TC: O(N^3)

    // Better Approach: generate all the subarray and check for the sum
    // if sum = 0 then compare the longest with the prev longest
    public static int longestSubArrWithSum0(int[] arr) {
        int longest = 0;
        for (int i = 0; i < arr.length; i++) {
            long sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == 0) {
                    longest = Math.max(longest, j - i + 1);
                }
            }
        }
        return longest;
    }

    // Optimal: we will follow the prefix sum technique
    // If we found the sum which already exist in the hashmap that means it is the
    // sub array with sum 0 so we have to udpate max
    // TC: O(n * log n) , Iterating over the array and to store el in map
    public static int longestSubArr(int[] arr) {
        int max = 0;
        int sum = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == 0) {
                max = i + 1;
            } else {
                if (map.get(sum) != null) {
                    max = Math.max(max, i - map.get(sum));
                } else {
                    map.put(sum, i);
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = { 1, -1, 2, -2, 3, -3, 4, 4, 5, -5 };
        System.out.println(longestSubArr(arr));

    }
}
