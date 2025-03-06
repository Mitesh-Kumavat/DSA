import java.util.Arrays;

public class SelectionSort {

    public static void selectionSortAlgo(int[] arr) {
        /*
         * Iterate to the second last index of the array because the last index element
         * will be automatically sorted after the whole array is sorted.
         */
        for (int i = 0; i <= arr.length - 2; i++) {
            int mini = i;
            /*
             * Iterate over the whole array and compare the minimum value with the each
             * element
             * if there is min value then store the index of the value in the mini variable
             */
            for (int j = i; j <= arr.length - 1; j++) {
                if (arr[j] < arr[mini]) {
                    mini = j;
                }
            }

            /*
             * Now swap the i th index of the array with the min value.
             */
            int temp = arr[mini];
            arr[mini] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 12, 42, 523, 13, 54, 1, 0, 3 };

        // Time complexity of the Selection Sort is ~ O(n^2)
        // for all the best, worst and avg case
        selectionSortAlgo(arr);
        System.out.println(Arrays.toString(arr));
    }
}