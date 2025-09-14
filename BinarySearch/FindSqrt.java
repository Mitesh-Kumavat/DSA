package BinarySearch;

public class FindSqrt {

    // Brute force:
    // loop from 1 to n to check if the number is exact sqrt then return
    // but if number is something like 35 then 5 is the ans because
    // 5 square is 25 and 6 square is 36
    // and 6 crosses the n so we will return 5.
    // TC: O(N)
    public static int findSqrtBrute(int n) {

        if (n == 0 || n == 1)
            return n; // handle base cases

        int ans = 1;
        for (int i = 1; i <= n; i++) {
            if (i * i == n) {
                return i; // perfect square
            } else if (i * i > n) {
                ans = i - 1; // last valid i before crossing n
                break;
            }
        }
        return ans;
    }

    // Optimal: using binary search
    // here we can see the range that our
    // ans will always be from [1 .... n] only
    // so we can apply BS on this to eliminate portions
    // and get to our desired answer
    public static int findSqrt(int n) {
        // base cases
        if (n == 1 || n == 0) {
            return n;
        }

        int ans = 1;
        int low = 1;
        int high = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long value = (long) mid * (long) mid;

            if (value <= n) { // this might be our possible answer
                ans = mid; // store the answer
                low = mid + 1; // and look for the max ans, so go to right
            } else {
                high = mid - 1; // go to left part, so you will find there ans
            }
        }

        // either you can return high or ans variable
        // because when you do dry run you will notice that
        // high always ends up at the element which is your ans
        return ans;
    }

    public static void main(String[] args) {
        int n = 36;
        System.out.println(findSqrt(n)); // output : 6
        int n2 = 35;
        System.out.println(findSqrt(n2)); // output: 5 root of 28 is approximately 5.292. So, the floor value will be 5.
    }
}
