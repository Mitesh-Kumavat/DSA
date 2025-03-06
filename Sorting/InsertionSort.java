import java.util.Arrays;

public class InsertionSort {

    public static void insertionSort(int[] arr, int n) {
        for (int i = 0; i <= n - 1; i++) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 12, 2, 53, 63, 34, 6, 78, 3, 5 };
        insertionSort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
