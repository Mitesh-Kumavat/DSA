package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {

    // Brute Force: we will run 3 loops to find the elemens
    // which will sum up to 0 and they are then sort and att to the set list
    // so by using set list we make sure that there will be only unique triplets in
    // the ans, and finally we store the set list into List and return
    // TC: O(N3 * log(no. of unique triplets)), where N = size of the array.
    // SC: O(2 * no. of the unique triplets) because we are using set data structure
    // and a list to store the triplets.
    public static List<List<Integer>> threeSumBruteForce(int[] arr) {
        Set<List<Integer>> st = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int j2 = j + 1; j2 < arr.length; j2++) {
                    if (i != j && i != j2 && (arr[i] + arr[j] + arr[j2] == 0)) {
                        List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[j2]);
                        temp.sort(null);
                        st.add(temp);
                    }
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>(st);
        return ans;
    }

    // Better Approach: To optimize it from brute force we have to do it in O(N^2)
    // and to do so we only have to run loop 2 time
    // so as we know a[i] + a[j] + a[k] = 0
    // so we can say that a[k] = -(a[i] + a[j])
    // we will look up for a[k] so that we can get triplet pair
    // To find the k we will use hashmap and store elements and look for k
    // after each iteration of 'i' loop we will clear hashset
    // For detail explaination:
    // https://takeuforward.org/data-structure/3-sum-find-triplets-that-add-up-to-a-zero/
    // TC: O(n^2 * log M) , M is the size of set
    // SC: O(2 * no. of the unique triplets) + O(N) , we are using a set data
    // structure and a list to store the triplets and extra O(N) for storing the
    // array elements in another set.
    public static List<List<Integer>> threeSumBetter(int[] arr) {
        Set<List<Integer>> st = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {

            // declare a set for storing all the unique pairs only
            Set<Integer> set = new HashSet<>();

            for (int j = i + 1; j < arr.length; j++) {

                // find the third element which will help to add the ans to 0
                int k = -(arr[i] + arr[j]);

                // if the third element is in set that means we have found the pair
                if (set.contains(k)) {
                    List<Integer> temp = Arrays.asList(arr[i], arr[j], k);
                    temp.sort(null);
                    st.add(temp);
                }

                set.add(arr[j]);
            }
        }

        // Transfer the unique pairs to List and return
        List<List<Integer>> ans = new ArrayList<>(st);
        return ans;
    }

    // Optimal Approach: Using 2 pointer
    // Deep explaination is in the striver's video so watch it
    // TC: O(NlogN)+O(N2), where N = size of the array.
    // SC: O(N) but it is only for storing the answer otherwise O(1)
    public static List<List<Integer>> threeSum(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();

        // sort the array so that we can use 2 pointer approach
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            int j = i + 1;
            int k = arr.length - 1;

            // If i is not the 1st element of the array
            // and it is same as previous element then just skip this iteration
            // no need to do anything just pass the loop
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            // Now while j does not cross the k
            // we will keep checking the sum and move the pointer j and k accordingly
            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];

                // If sum is > 0 then we have to reduce the sum so we can move k backwards
                if (sum > 0) {
                    k--;
                } else if (sum < 0) {
                    j++; // sum < 0 so we need to increase the sum so move j ahead
                } else {
                    // Sum is 0 so we store that into temp and add it to the ans list
                    List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[k]);
                    ans.add(temp);

                    // now we also have to make sure to move j and k
                    j++;
                    k--;

                    // Now even after moving j and k there still might be duplicate elements
                    // so we also have to skip those (till j doesnt crosses k )
                    // Move the j ahead if its same as previous element
                    while (j < k && arr[j] == arr[j - 1]) {
                        j++;
                    }

                    // Decrease the K if its same as its next element
                    while (j < k && arr[k] == arr[k + 1]) {
                        k--;
                    }
                }
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { -1, 0, 1, 2, -1, -4 };
        List<List<Integer>> ans = threeSum(arr);
        System.out.println(ans);
    }
}
