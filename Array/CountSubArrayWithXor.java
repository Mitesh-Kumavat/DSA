package Array;

import java.util.HashMap;

public class CountSubArrayWithXor {

    // TRY BY YOUR SELF
    // Brute Force: we will generate every subarray using two loops
    // then we run the third loop to check for the xor with k
    // so overall 3 loops, 2 for generating subarray
    // 1 loop to check xor k
    // TC: O(N^3), SC: Constant

    // Better Approach: same as brute force but just reduce one loop
    // calculate xor in the second loop
    // TC: O(N^2), SC: Constant
    public static int countSubarr(int[] arr, int k) {
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            int xor = 0;
            for (int j = i; j < arr.length; j++) {
                xor = xor ^ arr[j];

                if (xor == k) {
                    ans++;
                }
            }
        }

        return ans;
    }

    // Optimized: Using Hashmap and prefix sum technique
    // For an example if we are finding the subarr with k
    // then call the entire arry is XR
    // so in the XR array we are looking someone whos XOR is k
    // so that some is X, and to find X we use this: X = XR ^ K,
    // More detailed explaination:
    // https://takeuforward.org/data-structure/count-the-number-of-subarrays-with-given-xor-k/
    public static int countSubArrOptimized(int[] arr, int k) {
        int ans = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        int prefixXOR = 0;
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {

            prefixXOR ^= arr[i];

            int x = prefixXOR ^ k;

            if (map.containsKey(x)) {
                ans += map.get(x);
            }

            if (map.containsKey(prefixXOR)) {
                map.put(prefixXOR, map.get(prefixXOR) + 1);
            } else {
                map.put(prefixXOR, 1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 2, 2, 6, 4 };
        int k = 6;
        System.out.println(countSubArrOptimized(arr, k));
    }
}
