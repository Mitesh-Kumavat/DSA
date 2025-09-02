package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MajorityElement2 {
    // Brute Force:
    // Pick an element and traverse through the array and check
    // if it appears more than N/3 times
    // TC: O(N^2) , SC: O(1)
    public static List<Integer> majority(int[] arr) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            int el = arr[i];

            for (int j = 0; j < arr.length; j++) {
                if (el == arr[j]) {
                    count++;
                }
            }

            if (count > arr.length / 3 && !(ans.contains(el))) {
                ans.add(el);
            }

            if (ans.size() == 2) {
                return ans;
            }
        }

        return ans;
    }

    // Better Approach:
    // TC: O(n*logn), SC: O(N)
    // Put every element to the hashmap with their frequency
    // Key -> element , value -> frequency
    // now after putting it check if its > N/3 or not
    // Remember: There will always be 2 elements only at max
    // Ex: arr.length = 20 , N/3 => 6
    // to be greater than N/3 , you must have 7 el.
    // so there can at max 2 lement who is => 7 , (Total >= 14)
    // So in the ans there will be at max 2 element
    public static List<Integer> majorityUsingHashMap(int[] arr) {

        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int value = map.getOrDefault(arr[i], 0);
            map.put(arr[i], value + 1);

            if (map.get(arr[i]) > arr.length / 3 && !ans.contains(arr[i])) {
                ans.add(arr[i]);
            }

            // We know that no matter what will be the size of arr , it can't have more than
            // two N/3 elements so when we get 2 elements we just return
            if (ans.size() == 2) {
                return ans;
            }
        }

        return ans;

    }

    // Optimal Apprach:
    // TC: O(2N) , SC: O(1)
    // Learn the explaination here:
    // https://takeuforward.org/data-structure/majority-elementsn-3-times-find-the-elements-that-appears-more-than-n-3-times-in-the-array/
    public static List<Integer> majorityElements(int[] arr) {
        List<Integer> ans = new ArrayList<>();

        int cnt1 = 0;
        int cnt2 = 0;
        int el1 = Integer.MIN_VALUE;
        int el2 = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (cnt1 == 0 && arr[i] != el2) {
                el1 = arr[i];
                cnt1 = 1;
            } else if (cnt2 == 0 && arr[i] != el1) {
                el2 = arr[i];
                cnt2 = 1;
            } else if (arr[i] == el1) {
                cnt1++;
            } else if (arr[i] == el2) {
                cnt2++;
            } else {
                cnt1--;
                cnt2--;
            }
        }

        // now just verify it that both the elements are the answers or not
        cnt1 = 0;
        cnt2 = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == el1) {
                cnt1++;
            } else if (arr[j] == el2) {
                cnt2++;
            }
        }

        if (cnt1 > arr.length / 3) {
            ans.add(el1);
        }
        if (cnt2 > arr.length / 3) {
            ans.add(el2);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2 };
        System.out.println(majorityElements(arr));
    }
}
