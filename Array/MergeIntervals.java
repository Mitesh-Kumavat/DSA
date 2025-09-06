package Array;

import java.util.*;

public class MergeIntervals {
    // Brute force: firstly we will sort the given 2D array by its first index
    // TC: O(N*logN + 2N) , Sorting + Iterating
    // SC: Constant or O(N), because we are using list to just return the answer
    public static List<List<Integer>> mergeOverlappingIntervals(int[][] arr) {
        int n = arr.length;

        // Sort the array by its first index
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) { // select an interval:
            int start = arr[i][0];
            int end = arr[i][1];

            // Skip all the merged intervals:
            if (!ans.isEmpty() && end <= ans.get(ans.size() - 1).get(1)) {
                continue;
            }

            // check the rest of the intervals:
            for (int j = i + 1; j < n; j++) {
                if (arr[j][0] <= end) {
                    end = Math.max(end, arr[j][1]);
                } else {
                    break;
                }
            }
            ans.add(Arrays.asList(start, end));
        }

        return ans;

    }

    // Optimal: Same as Brute force, but just one change
    // TC: O(N*logN + 2N) , Sorting + Iterating
    // SC: Constant or O(N), because we are using list to just return the answer
    public static List<List<Integer>> mergeOverlappingIntervalsOptimal(int[][] arr) {
        int n = arr.length;

        // Sort the array by its first index
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            // if the current interval does not
            // lie in the last interval:(set the current element as an interval)
            if (ans.isEmpty() || arr[i][0] > ans.get(ans.size() - 1).get(1)) {
                ans.add(Arrays.asList(arr[i][0], arr[i][1]));
            }
            // if the current interval
            // lies in the last interval:(update the size of the interval by max size)
            else {
                ans.get(ans.size() - 1).set(1, Math.max(ans.get(ans.size() - 1).get(1), arr[i][1]));
            }
        }

        // this is just for leetcode
        // because leetcode required the array to be returned NOT arraylist.
        int[][] ansArray = new int[ans.size()][2];

        int count = 0;
        for (List<Integer> list : ans) {
            ansArray[count][0] = list.get(0);
            ansArray[count][1] = list.get(1);
            count++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] arr = { { 1, 3 }, { 8, 10 }, { 2, 6 }, { 15, 18 } };
        List<List<Integer>> ans = mergeOverlappingIntervalsOptimal(arr);

        System.out.println(ans);
    }
}
