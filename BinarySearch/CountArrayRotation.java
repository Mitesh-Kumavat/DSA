package BinarySearch;

public class CountArrayRotation {

    // Optimal:
    // To find that how many time the array is rotated
    // we need to find the minimum element because it will be the breakpoint
    // so just find the break point then return its index
    // As we did the previous question: find min in rotated array
    // we use the same logic, just we need to do minor change
    // we can't use Math.min() function here
    // we have to check manually if the element is smaller then store
    // the element and its index
    // so where the smallest element is, its our answer for how many times array has
    // been rotated
    public static int countArrayRotation(int[] arr) {
        int rotation = arr.length;

        int low = 0;
        int high = arr.length - 1;
        int min = Integer.MAX_VALUE;

        while (low <= high) {

            int mid = (low + high) / 2;

            // left half is sorted
            if (arr[mid] >= arr[low]) {

                if (arr[low] < min) {
                    min = arr[low];
                    rotation = low;
                }
                low = mid + 1;
            } else {
                if (arr[mid] < min) {
                    min = arr[mid];
                    rotation = mid;
                }
                high = mid - 1;
            }
        }

        return rotation;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 5, 6, 7, 0, 1, 2, 3 };
        int[] arr2 = { 1, 2, 3, 4, 4, 5 };
        System.out.println(countArrayRotation(arr2));
    }
}
