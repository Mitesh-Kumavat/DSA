package Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {

    public static boolean linearSearch(int[] a, int num) {
        int n = a.length; // size of array
        for (int i = 0; i < n; i++) {
            if (a[i] == num)
                return true;
        }
        return false;
    }

    // Brute Force:
    // https://takeuforward.org/data-structure/longest-consecutive-sequence-in-an-array/
    // read it explanation from here
    // TC: O(N^2) , SC: O(1)
    public static int longestConsecutiveBruteForce(int[] a) {
        int n = a.length; // size of array
        int longest = 1;
        // pick a element and search for its
        // consecutive numbers:
        for (int i = 0; i < n; i++) {
            int x = a[i];
            int cnt = 1;
            // search for consecutive numbers
            // using linear search:
            while (linearSearch(a, x + 1) == true) {
                x += 1;
                cnt += 1;
            }

            longest = Math.max(longest, cnt);
        }
        return longest;

    }

    // Better Solution: we will sort the array
    // now just dry run this to understand this or go to
    // https://takeuforward.org/data-structure/longest-consecutive-sequence-in-an-array/
    // TC: O(NlogN) + O(N)
    public static int LongestConsecutiveBetter(int[] arr) {
        int longest = 1;
        int currentCount = 0;
        int lastSmaller = Integer.MIN_VALUE;

        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] - 1 == lastSmaller) {
                currentCount++;
                longest = Math.max(longest, currentCount);
                lastSmaller = arr[i];
            } else if (arr[i] == lastSmaller) {
                continue;
            } else {
                lastSmaller = arr[i];
                currentCount = 1;
            }
        }

        return longest;
    }

    // Optimal Solution: Using Set Data Structure
    // Firstly we will store every element in set
    // TC: O(N) + O(2*N) ~ O(3*N)
    // SC: O(N)
    public static int LongestConsecutiveOptimal(int[] arr) {

        if (arr.length == 0)
            return 0;

        Set<Integer> set = new HashSet<Integer>();
        int longest = 1;

        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        // Find the longest sequence
        for (int it : set) {
            // if 'it' is a starting number
            if (!set.contains(it - 1)) {
                // find consecutive numbers
                int cnt = 1;
                int x = it;
                while (set.contains(x + 1)) {
                    x = x + 1;
                    cnt = cnt + 1;
                }
                longest = Math.max(longest, cnt);
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        int[] arr = { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 };
        System.out.println(LongestConsecutiveBetter(arr));
    }
}
