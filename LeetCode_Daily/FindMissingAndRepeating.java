package LeetCode_Daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FindMissingAndRepeating {

    // optimized approach(Usinf Frequency Arr) -> i have copied it from leetcode's
    // submissions
    public int[] findMissingAndRepeatedValuesUsingFrequencyArr(int[][] grid) {
        int n = grid.length;
        int size = n * n;
        int[] freq = new int[size + 1];
        int repeated = -1, missing = -1;

        for (int[] row : grid) {
            for (int num : row) {
                freq[num]++;
            }
        }

        for (int num = 1; num <= size; num++) {
            if (freq[num] == 2)
                repeated = num;
            if (freq[num] == 0)
                missing = num;
        }

        return new int[] { repeated, missing };
    }

    // This is another approach using hashset
    public int[] findMissingAndRepeatedValuesUsingHashSet(int[][] grid) {
        int n = grid.length;
        int size = n * n;
        Set<Integer> seen = new HashSet<>();
        int repeated = -1, missing = -1;

        for (int[] row : grid) {
            for (int num : row) {
                if (!seen.add(num)) {
                    repeated = num;
                }
            }
        }

        for (int num = 1; num <= size; num++) {
            if (!seen.contains(num)) {
                missing = num;
                break;
            }
        }

        return new int[] { repeated, missing };
    }

    // This is the first time i have solved any leetcode's daily problem,
    // I know its not the best approach that i have used but still i have done this 😅

    public static int[] findMissingAndRepeatedValues(int[][] grid) {
        int[] ans = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (hm.containsKey(grid[i][j])) {
                    hm.put(grid[i][j], hm.get(grid[i][j]) + 1);
                    ans[0] = grid[i][j];
                } else {
                    hm.put(grid[i][j], 1);
                }
            }
        }

        if (!hm.containsKey(1)) {
            ans[1] = 1;
            return ans;
        }

        for (HashMap.Entry<Integer, Integer> entry : hm.entrySet()) {
            boolean diff = hm.containsKey(entry.getKey() + 1);

            if (!diff) {
                ans[1] = entry.getKey() + 1;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 2, 2 },
                { 3, 4 },
        };
        System.out.println(Arrays.toString(findMissingAndRepeatedValues(grid)));
    }
}
