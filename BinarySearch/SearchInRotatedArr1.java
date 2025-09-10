package BinarySearch;

public class SearchInRotatedArr1 {

    // Brute force: Linear search

    // Optimal: Using binary search
    // We know for sure that either the left part is sorted, or right part
    // so take advantage of this observation
    // and tweak BS algo according to this observation
    public static int searchInRotatedArray(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            // now check which part is sorted

            // Condition for Left part to be sorted
            if (arr[mid] >= arr[low]) {
                // now since the left part is sorted we have to also check
                // if our target lies between this array or not
                // if lies then go to left else go to right
                if (arr[mid] >= target && target >= arr[low]) {
                    // go to left
                    high = mid - 1;
                } else {
                    // go to right
                    low = mid + 1;
                }
            } else { // right part is sorted
                // if target lies between mid & high then go to right else go left
                if (arr[mid] <= target && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 0;
        System.out.println(searchInRotatedArray(arr, target));
    }
}
