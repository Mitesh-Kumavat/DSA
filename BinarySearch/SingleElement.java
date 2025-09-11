package BinarySearch;

public class SingleElement {

    // Brute force:
    // Since the array is sorted we can check if the element we are standing at
    // is either same as next or previous element, if not then this element is
    // single and return that
    // TC: O(n)
    public static int singleElementBrute(int[] arr) {
        int element = arr[0];

        if (arr.length == 1) {
            return element;
        }

        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i - 1] == arr[i] || arr[i + 1] == arr[i]) {
                continue;
            } else {
                element = arr[i];
                return element;
            }
        }

        // check for last element
        if (arr[arr.length - 1] != arr[arr.length - 2]) {
            element = arr[arr.length - 1];
        }

        return element;
    }

    // Brute 2: using xor. Do XOR of every element and return the xor
    // Xor works : a ^ a = 0 and a ^ 0 = a
    // TC: O(n)

    // Optimal:
    // Ex: [1,1,2,2,3,3,4,5,5]
    // so we can observe that before the single element
    // every element on the left is in pair with the index (even, odd)
    // and after the single element every double elements are at index (odd, even)
    // so we need to find the point where this index has been changing
    public static int singleElement(int[] arr) {
        int n = arr.length;

        // Edge cases
        if (n == 1)
            return arr[0];

        if (arr[0] != arr[1])
            return arr[0];

        if (arr[n - 1] != arr[n - 2])
            return arr[n - 1];

        int low = 1;
        int high = n - 2;

        while (low <= high) {
            int mid = (low + high) / 2;

            // If arr[mid] is the single element:
            if (!(arr[mid] == arr[mid + 1]) && !(arr[mid] == arr[mid - 1])) {
                return arr[mid];
            }

            // if we are on the left half then element the left half
            if ((mid % 2 == 1 && arr[mid] == arr[mid - 1]) || (mid % 2 == 0 && arr[mid] == arr[mid + 1])) {
                low = mid + 1;
            } else {
                // Eliminate the right half:
                high = mid - 1;
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 1, 2, 2, 3, 3, 4, 5, 5 };
        System.out.println(singleElementBrute(arr));
        System.out.println(singleElement(arr));
    }
}
