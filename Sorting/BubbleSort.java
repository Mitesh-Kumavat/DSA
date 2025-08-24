import java.util.Arrays;

// Bubble sort is a simple sorting algorithm that repeatedly steps through the array, compares adjacent elements and swaps them if they are in the wrong order.
// Although the algorithm is simple, it is too slow and impractical for most problems even when compared to insertion sort. It can be practical if the input is usually in sort order but may occasionally have some out-of-order elements nearly in position.
// Bubble sort is not a practical sorting algorithm when n is large. It will not be efficient in the case of a reverse-ordered list. It is not a stable sorting algorithm.
// The time complexity of the bubble sort is O(n^2) in the worst case and average case, and O(n) in the best case.
// The space complexity of the bubble sort is O(1) because it requires a constant amount of extra space.
public class BubbleSort {

    public static void bubbleSortAlgo(int[] arr, int n) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean didSwap = false;
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    didSwap = true;
                }
            }
            if (!didSwap) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 12, 2, 53, 63, 34, 6, 78, 3, 5 };
        int[] arr2 = { 1, 3, 5 };
        bubbleSortAlgo(arr2, arr2.length);
        System.out.println(Arrays.toString(arr2));
    }
}
