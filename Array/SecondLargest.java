package Array;

public class SecondLargest {

    // Optimized solution -> TC ~ O(N)
    // for the brute force and the better solution you can find it in the notes.
    public static int secondLarge(int[] arr) {
        int large = arr[0];
        int secondLarge = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > large) {
                secondLarge = large;
                large = arr[i];
            }

            if (arr[i] > secondLarge && arr[i] != large) {
                secondLarge = arr[i];
            }
        }

        return secondLarge;
    };

    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 7, 7, 5 };
        System.out.println(secondLarge(arr));
    }
}
