package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    // Brute force: same as 3 sum problem, just add one more loop -> [i, j, k, l]
    // sort the generated pair after 4 loop and store it into hashset
    // TC: O(n^4), SC: O(No of pairs * 2) storing in set and then store it into ans

    // Better approach:
    // numl[l] = target - (num[i] + num[j] + num[k])
    // Just there are some tweaks in the approach because here there are 3 var
    // TC: O(n^3 * log(M)) M = set size , SC: O(N + no of pairs * 2)
    // https://takeuforward.org/data-structure/4-sum-find-quads-that-add-up-to-a-target-value/

    // Optimal Approach:
    public static List<List<Integer>> fourSumProblem(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {

            // if i is same as the prev element then skip this iteration
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < arr.length; j++) {
                // if j is same as the prev element then skip this iteration to prevent
                // duplicate elements storing in ans
                if (j > i + 1 && arr[j] == arr[j - 1]) {
                    continue;
                }

                int k = j + 1;
                int l = arr.length - 1;

                while (k < l) {
                    long sum =(long) arr[i] + arr[j] + arr[k] + arr[l];

                    if (sum == target) {
                        ans.add(Arrays.asList(arr[i], arr[j], arr[k], arr[l]));
                        k++;
                        l--;

                        // now move the k and l untill there are no duplicates

                        while (k < l && arr[k] == arr[k - 1])
                            k++;

                        while (k < l && arr[l] == arr[l + 1])
                            l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }

                }
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 3, 3, 4, 4, 2, 1, 2, 1, 1 };
        int target = 9;
        List<List<Integer>> ans = fourSumProblem(nums, target);
        System.out.println("THIS IS FOUR SOME");
        System.out.println(ans);
    }
}
