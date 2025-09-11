package BinarySearch;

public class SearchInRotatedArr2 {

    // Approach: Same as Search in rotated array 1
    // Changes: we have now duplicates in the given array
    // so add one condition only
    // TC: O(N/2)
    public static boolean searchInRotatedArray(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return true;
            }

            // This is the specail case for the arr who is like this:
            // 3,3,3,1,2,3,3,3,3,3
            // so the mid = low = high
            // so we need to trim down the searc space in this manner
            if (arr[mid] == arr[low] && arr[low] == arr[high]) {
                low++;
                high--;
                continue;
            }

            if (arr[mid] >= arr[low]) {
                if (arr[mid] >= target && target >= arr[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (arr[mid] <= target && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 3, 3, 3, 1, 2, 3, 3, 3, 3, 3, 3, 3 };
        int target = 1;
        System.out.println(searchInRotatedArray(arr, target));
    }
}
