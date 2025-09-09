package BinarySearch;

// Example 1:
// Input: nums = [1,3,5,6], target = 5
// Output: 2

// Example 2:
// Input: nums = [1,3,5,6], target = 2
// Output: 1

// Example 3:
// Input: nums = [1,3,5,6], target = 7
// Output: 4

public class SearchInsertPos {

    // Same as LowerBound problem (NOT A SINGLE CHANGE)

    // Brute force: simply using loop approach
    // You can do it by yourself
    // TC: O(N)

    // Optimal: Using Binary Search
    // TC: O(logN)
    public static int searchInsert(int[] arr, int target) {
        int ans = arr.length;
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 6, };
        int target = 2;
        System.out.println(searchInsert(arr, target));
    }
}
