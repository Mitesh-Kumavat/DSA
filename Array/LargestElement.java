package Array;

public class LargestElement {
    public static int maxElement(int[] arr) {
        int large = arr[0];
        for (int i : arr) {
            if (i > large) {
                large = i;
            }
        }

        return large;
    }

    public static void main(String[] args) {
        int[] arr = { 12, 5, 67, 234, 8, 2, 24, 2 };
        System.out.println(maxElement(arr));
    }
}
