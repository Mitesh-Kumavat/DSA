/*
 * Leetcode: https://leetcode.com/problems/majority-element/
 */
package Array;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    // Brute Force: we will take every element and check it in the entire array if
    // its greater than N/2 times return it
    // TC: O(N) , SC: O(1)
    public static int majorityElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
            if (count > nums.length / 2) {
                return nums[i];
            }
        }

        return -1;
    }

    // Better Approach: Using hashmap
    // TC: O(n logn) + O(n)
    // SC: O(n) if array contains all the unique elements
    public static int majorityElementBetter(int[] arr) {
        int n = arr.length;

        HashMap<Integer, Integer> map = new HashMap<>();

        // Store every element in hashmap along side its counts.
        for (int i = 0; i < arr.length; i++) {
            int value = map.getOrDefault(arr[i], 0);
            map.put(arr[i], value + 1);
        }

        // Now iterate through every el and check if its counts is > n/2 or not
        for (Map.Entry<Integer, Integer> it : map.entrySet()) {
            if (it.getValue() > n / 2) {
                return it.getKey();
            }
        }

        return -1;
    }

    // Optimal Approach: Moore's voting algorithm
    // TC: O(2N)
    // SC: O(1)
    // Explaination: We will take an element and a count variable
    // If count is 0 we will update the element and make count=1
    // If the next element is same as the current element we will increase the count
    // else we will decrease the count
    // At last the element will be our majority element
    // But we have to verify that the element is > N/2 or not
    public static int majorityElementOptimal(int[] arr) {
        int el = arr[0], count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (count == 0) {
                el = arr[i];
                count = 1;
            } else if (el == arr[i]) {
                count++;
            } else {
                count--;
            }
        }

        // Verify that the number is > N/2 or not
        count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (el == arr[i]) {
                count++;
            }
        }

        if (count > arr.length / 2) {
            return el;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 2, 1, 1, 1, 2, 2, 1, 1 };
        System.out.println(majorityElement(arr));
        System.out.println(majorityElementBetter(arr));
        System.out.println(majorityElementOptimal(arr));
    }
}
