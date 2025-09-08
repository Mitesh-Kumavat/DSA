package Array;

import java.util.ArrayList;

public class ReversePairs {

    // Brute force: Generate all the pair with i, j and find our ans based on given
    // condition, and increase the count
    // TC: O(N^2), SC: Constant
    public static int reversePairBrute(int[] arr) {
        int cnt = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (long) 2 * arr[j]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    // Optimal Approach: using merger sort
    // Explaination: striver's video
    private static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>(); // temporary array
        int left = low; // starting index of left half of arr
        int right = mid + 1; // starting index of right half of arr

        // storing elements in the temporary array in a sorted manner//

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }

        // if elements on the left half are still left //

        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        // if elements on the right half are still left //
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        // transfering all elements from temporary to arr //
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    public static int countPairs(int[] arr, int low, int mid, int high) {
        int right = mid + 1;
        int cnt = 0;
        for (int i = low; i <= mid; i++) {
            while (right <= high && arr[i] > (long) 2 * arr[right])
                right++;
            cnt += (right - (mid + 1));
        }
        return cnt;
    }

    public static int mergeSort(int[] arr, int low, int high) {
        int cnt = 0;
        if (low >= high)
            return cnt;
        int mid = (low + high) / 2;
        cnt += mergeSort(arr, low, mid); // left half
        cnt += mergeSort(arr, mid + 1, high); // right half
        cnt += countPairs(arr, low, mid, high); // Modification
        merge(arr, low, mid, high); // merging sorted halves
        return cnt;
    }

    public static int reversePair(int[] arr) {
        return mergeSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        // A reverse pair is a pair (i, j) where:
        // 0 <= i < j < nums.length and
        // nums[i] > 2 * nums[j].

        int[] nums = { 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647 };
        System.out.println(reversePairBrute(nums));
        System.out.println(reversePair(nums));
    }
}
