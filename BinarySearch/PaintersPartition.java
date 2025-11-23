package BinarySearch;

// Same problem as SplitLargestSum.java
public class PaintersPartition {

    public static int countPainters(int[] arr, int capicity) {
        int painter = 1;
        int currentBoard = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] + currentBoard <= capicity) {
                currentBoard += arr[i];
            } else {
                painter++;
                currentBoard = arr[i];
            }
        }

        return painter;
    }

    public static int findLargestMinBoards(int[] arr, int k) {
        int ans = 0;

        int low = 0;
        int high = 0;

        for (int i : arr) {
            low = Math.max(low, i);
            high += i;
        }

        while (low <= high) {
            int mid = (low + high) / 2;

            int painters = countPainters(arr, mid);
            if (painters <= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        int boards[] = { 10, 20, 30, 40 };
        int k = 2;
        System.out.println(findLargestMinBoards(boards, k));
    }
}
