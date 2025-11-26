package BinarySearch;

public class KthElOfSortedArr {
    public static int kthElementBrute(int[] a, int[] b, int k) {

        int cnt = 0, i = 0, j = 0;
        k = k - 1;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                if (cnt == k) {
                    return a[i];
                }
                i++;
            } else {
                if (cnt == k) {
                    return b[j];
                }
                j++;
            }
            cnt++;
        }

        while (i < a.length) {
            if (cnt == k) {
                return a[i];
            }
            cnt++;
            i++;
        }
        while (j < b.length) {
            if (cnt == k) {
                return b[j];
            }
            cnt++;
            j++;
        }

        return -1;
    }

    public int kthElement(int[] a, int[] b, int k) {
        int m = a.length;
        int n = b.length;

        // Ensure 'a' is the smaller array for optimization
        if (m > n) {
            return kthElement(b, a, k);
        }

        int left = k; // Number of elements in the left partition
        int low = Math.max(0, k - n), high = Math.min(k, m);

        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = left - mid1;

            int l1 = (mid1 > 0) ? a[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? b[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < m) ? a[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n) ? b[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = mid1 - 1; // Move left
            } else {
                low = mid1 + 1; // Move right
            }
        }

        return -1; // Should never reach here if inputs are valid
    }

    public static void main(String[] args) {
        int[] a = { 100, 112, 256, 349, 770 };
        int[] b = { 72, 86, 113, 119, 265, 445, 892 };
        int k = 7;

        System.out.println(kthElementBrute(b, a, k));
    }
}
