package BinarySearch;

public class SmallestDivisor {

    public static int divisorAns(int[] arr, int val) {
        int sum = 0;
        for (int i : arr) {
            sum += Math.ceil((double) i / (double) val);
        }
        return sum;
    }

    // Explaination:
    // https://takeuforward.org/arrays/find-the-smallest-divisor-given-a-threshold/
    // TC: O(log m * n) -> m is the max element of array, n is the size of array
    public static int smallestDivisor(int[] arr, int threshold) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= max) {
                max = arr[i];
            }
        }

        int low = 1;
        int high = max;

        int ans = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            int sum = divisorAns(arr, mid);

            if (sum <= threshold) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }

        return low;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 5, 9 };
        int threshold = 6;

        System.out.println(smallestDivisor(arr, threshold));
    }
}
