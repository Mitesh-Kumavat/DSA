package Recursion;

import java.util.ArrayList;
import java.util.Collections;

public class SubsetSum {

    public static void backtrack(int index, int sum, int[] arr, int n, ArrayList<Integer> ans) {
        if (index == n) {
            ans.add(sum);
            return;
        }

        // pick the element
        backtrack(index + 1, sum + arr[index], arr, n, ans);

        // not pick the element
        backtrack(index + 1, sum, arr, n, ans);
    }

    public static ArrayList<Integer> allSubSetSum(int[] arr, int n) {
        ArrayList<Integer> ans = new ArrayList<>();

        backtrack(0, 0, arr, arr.length, ans);

        Collections.sort(ans);

        return ans;
    }

    public static void main(String[] args) {
        int arr[] = { 3, 2, 1 };
        int n = arr.length;
        System.out.println(allSubSetSum(arr, n));
    }
}
