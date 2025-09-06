package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeArrays {

    // Brute force: (but with using an extra space)
    // Two pointer approach and extra space
    // TC: O(2(N + M)), SC: O(M+N)
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        List<Integer> list = new ArrayList<>();

        int i = 0;
        int j = 0;

        // use two seperate pointer on each array
        // and compare who is small element
        // put that small element into list, and increase pointer
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                list.add(nums1[i]);
                i++;
            } else {
                list.add(nums2[j]);
                j++;
            }
        }

        // if there are still some elements left in first array
        // then add it to the list
        while (i < m) {
            list.add(nums1[i]);
            i++;
        }

        // if there are still some elements left in second array
        // then add it to the list
        while (j < n) {
            list.add(nums2[j]);
            j++;
        }

        // now rearrange the elements into the first array
        for (int j2 = 0; j2 < list.size(); j2++) {
            nums1[j2] = list.get(j2);
        }

    }

    // Optimal Approach 1: using two pointer
    public static void mergeOptimal(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // last position of nums1 (till there is non zero elements)
        int j = n - 1; // last position of nums2
        int k = m + n - 1; // last position of nums1 where we will place elements

        while (j >= 0) { // run the loop till there are elements in the second array

            // if the first array has large element at the ith index
            // place that to the k-th position of first array and move pointer k,i
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                k--;
                i--;
            } else {
                nums1[k] = nums2[j];
                k--;
                j--;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int[] nums2 = { 2, 5, 6 };
        int m = 3;
        int n = 3;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}
