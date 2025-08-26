package Array;

import java.util.ArrayList;

public class IntersectionArray {

    // Brute Force Approach:
    // We will loop through the first array and check for every elemet if there is
    // same element present in the second array or not
    // if the same element is present in the second array then we will also checks
    // it already visited or not, and if not then add it to the answer
    // TC: O(n1 x n2)
    // SC: O(n2)
    public static ArrayList<Integer> intersectArray(int[] a, int[] b) {
        ArrayList<Integer> ans = new ArrayList<>();
        int[] visited = new int[b.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j] && visited[j] == 0) {
                    ans.add(a[i]);
                    visited[j] = 1;
                    break;
                }

                if (b[j] > a[i]) {
                    break;
                }
            }
        }

        return ans;
    }

    // Otpimized Approach:
    // we will follow the 2 pointer method
    // we will put one pointer on 1st array and 2nd pointer on the second array
    // and move the pointer according to the situation (NOTE: arrays are sorted)
    // TC: O(n1+n2)
    // SC: O(1)
    public static ArrayList<Integer> intersectionOfTwoArray(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        ArrayList<Integer> ans = new ArrayList<>();

        while (i < a.length && j < b.length) {
            if (a[i] > b[j]) {
                j++;
            } else if (b[j] > a[i]) {
                i++;
            } else {
                ans.add(a[i++]);
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int arr1[] = { 1, 2, 5, 5, 5, 7, 9, 12, 15, 20, 22, 26, 28 };
        int arr2[] = { 1, 2, 5, 5, 6, 9, 11, 15, 17, 20 };
        ArrayList<Integer> ans = intersectionOfTwoArray(arr1, arr2);
        System.out.println(ans);
    }
}
