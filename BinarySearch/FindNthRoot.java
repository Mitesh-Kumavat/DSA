package BinarySearch;

public class FindNthRoot {

    // Brute force: run from 1 to m (number itself)
    // and find the power of every number
    // if it is same as the m then return i
    // if it exceeds m then return -1
    // TC: O(m * log n) || log n for finding the power
    public static int findNthRootBrute(int n, int m) {
        for (int i = 1; i <= m; i++) {
            int ans = (int) Math.pow(i, n);
            if (ans == m) {
                return i;
            } else if (ans > m) {
                break;
            }

        }

        return -1;
    }

    // Optimal : Using BS
    // TC: O(log m * log n)
    public static int findNthRoot(int n, int m) {

        int low = 1;
        int high = m;

        while (low <= high) {
            int mid = (low + high) / 2;
            long num = (long) Math.pow(mid, n);

            if (num == m) {
                return mid;
            }

            if (num > m) { // the number is > m, means we have to go to left to get smaller value
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int n = 1;
        int number = 69;
        System.out.println(findNthRootBrute(n, number));
        System.out.println(findNthRoot(10, 1000000000));
    }
}
