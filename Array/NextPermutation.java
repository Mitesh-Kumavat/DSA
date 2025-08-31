package Array;

import java.util.Arrays;

public class NextPermutation {
    // Brute Force :
    // Generate all the possible permutation and store them in sorted way
    // now perform linear search for the given question and return the next perm
    // We will not code it because :
    // 1. It will take O(n!) time to generate all the permutation
    // 2. O(n!) space to store all the permutation
    // 3. O(n) time to search the next permutation
    // So overall O(n*n!) time and O(n!) space

    // Optimal Approach :
    // STEP 1: (Find Breakpoint)
    // First we will find the element which is smaller than the next element
    // a[i] < a[i+1]
    // STEP 2: (Find someone who is slightly great and swap)
    // now we will find the element which is slightly greater than a[i] but the
    // smallest among the remaining and swap it.
    // STEP 3 :(Sort and place)
    // now we have left some element so we can arrange then in ascending order
    // Remember: we can sort it but if we observe carefully it will always be in the
    // increasing order from the n-1 to the breakpoint even if we swapped in step 2
    // so we can just reverse the remaining array portion and get the answer
    // TC: near about O(3N), SC: O(1)
    public static int[] nextPermutation(int[] arr) {
        int n = arr.length;
        int index = -1;
        
        // here we are finding the brakpoint 
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                index = i;
                break;
            }
        }

        // This means that the given permutation is the last permuation
        // so we have to give the org array
        // so we just need to reverse it
        if (index == -1) {
            reverse(arr, 0, arr.length - 1);
            return arr;
        } else {
            
            // now we have to find the element which is slightly
            // greater than the breakpoint element
            // we have run loop from backward because we know that
            // we can find the element which is slighlty greater not the biggest from the back
            // and than swap it
            for (int i = n - 1; i >= index; i--) {
                if (arr[i] > arr[index]) {
                    int temp = arr[i];
                    arr[i] = arr[index];
                    arr[index] = temp;
                    break;
                }
            }

            // now we just have to reverse the left over elements after the breakpoint
            reverse(arr, index + 1, arr.length - 1);
            return arr;
        }
    }

    public static void reverse(int[] arr, int start, int end) {
        while (start <= end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 5, 4, 3, 0, 0 };
        int[] ans = nextPermutation(arr);
        System.out.println(Arrays.toString(ans));
    }
}
