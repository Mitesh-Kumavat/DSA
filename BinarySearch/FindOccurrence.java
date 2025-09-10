package BinarySearch;

import java.util.Arrays;

public class FindOccurrence {

    // Brute force: Iterate through the array and keep two variable first, and last
    // If you encounter the targeted element then check if first is assigned or not
    // if not then assign first and last both
    // and if you again find the target just update the last
    // TC: O(N), SC: O(1)
    public static int[] findFirstAndLastOccurrenceBrute(int[] arr, int target) {
        int first = -1;
        int last = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                if (first == -1) {
                    first = i;
                    last = i;
                } else {
                    last = i;
                }
            }
        }

        return new int[] { first, last };
    }

    // find the first occurrence
    public static int findFirst(int[] arr, int target) {
        int ans = -1;

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                ans = mid;
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    public static int findLast(int[] arr, int target) {
        int ans = -1;

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                ans = mid;
                low = mid + 1; // we need to find last means go to right
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    // Optimal approach: Using binary search
    // Find the first occurrence and last seperately and return them
    // TC: O(2 * logN)
    public static int[] findFirstAndLastOccurrence(int[] arr, int target) {
        int first = findFirst(arr, target);
        if (first == -1) {
            return new int[] { -1, -1 };
        }
        int last = findLast(arr, target);

        return new int[] { first, last };
    }

    // Q: count of occurrence of target
    // A: return (last occurrence - first occurrence)
    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 3, 4, 5, 5, 6, 7, 7 };
        int target = 5;
        System.out.println(Arrays.toString(findFirstAndLastOccurrence(arr, target)));
    }
}