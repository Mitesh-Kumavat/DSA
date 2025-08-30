package Array;

import java.util.ArrayList;
import java.util.Arrays;

public class RearrangeArray {

    // Brute Force:we will create 2 list which will be
    // pos number only, negative num only
    // than for the final ans we will insert it into ans arr
    // by following correct order
    // TC: O(2N), SC: O(N)
    // NOTE: THIS IS OPTIMAL SOLUTION IF THE ARRAY DOES NOT
    // HAVE SAME NUMBER OF POS AND NEG ELEMENT
    public static int[] rearrangeArrayBrute(int[] arr) {
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();

        for (int el : arr) {
            if (el < 0) {
                neg.add(el);
            } else {
                pos.add(el);
            }
        }

        // This is for the variety 2
        // where we are sure that array does not have equal pos and neg
        if (pos.size() > neg.size()) {
            for (int i = 0; i < neg.size(); i++) {
                arr[i * 2] = pos.get(i);
                arr[i * 2 + 1] = neg.get(i);
            }
            int index = neg.size() * 2;
            for (int i = neg.size(); i < pos.size(); i++) {
                arr[index] = pos.get(i);
                index++;
            }
        } else {
            for (int i = 0; i < pos.size(); i++) {
                arr[i * 2] = neg.get(i);
                arr[i * 2 + 1] = pos.get(i);
            }
            int index = pos.size() * 2;
            for (int i = pos.size(); i < neg.size(); i++) {
                arr[index] = neg.get(i);
                index++;
            }
        }

        return arr;
    }

    // TC: O(n)
    // SC: O(n)
    // Optimal solution:
    public static int[] rearrangeArray(int[] nums) {
        int[] ans = new int[nums.length];
        int pos = 0, neg = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                ans[neg] = nums[i];
                neg += 2;
            } else {
                ans[pos] = nums[i];
                pos += 2;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, -4, -5, 3, 4 };
        System.out.println(Arrays.toString(rearrangeArrayBrute(arr)));
        System.out.println();
        int[] arr2 = { 1, 2, 3, -1, -2, -3, 4, -4 };
        System.out.println(Arrays.toString(rearrangeArray(arr2)));
    }
}
