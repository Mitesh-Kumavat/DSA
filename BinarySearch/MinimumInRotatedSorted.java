package BinarySearch;

public class MinimumInRotatedSorted {

    // Brute force: Iterate through array using min variable to store min element
    // TC: O(N)

    // Optimal: using binary search
    // Rotated array always have either the right half sorted or left half sorted
    // use this as an advantage
    // If the left half is sorted then pick its lowest element which is arr[low]
    // and move to the right so we can find the lowest from the right if there is
    // any(array is rotated so there might be lowest el in the right)
    // if this is not the case that means left half is not sorted and right is
    // sorted so pick the lowest el from right which is arr[mid] and move to left
    // TC: O(log N)
    public static int minimum(int[] arr) {
        int el = Integer.MAX_VALUE;

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            // if the entire array is already sorted then dont look for other steps
            // just store the ans and break the loop
            if (arr[low] <= arr[high]) {
                el = Math.min(arr[low], el);
                break;
            }

            // If the left half is sorted then pick up the smallest element
            // and go to the right
            if (arr[mid] >= arr[low]) {
                el = Math.min(el, arr[low]);
                low = mid + 1;
            } else {
                el = Math.min(arr[mid], el);
                high = mid - 1;
            }
        }

        return el;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 5, 6, 7, 0, 1, 2 };
        System.out.println(minimum(arr));
    }
}
