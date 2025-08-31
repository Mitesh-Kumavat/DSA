package Array;

import java.util.ArrayList;
import java.util.Collections;

public class LeadersInArray {

    // Brute force: we will pick element from the start and check that it is greater
    // than ever element to its right or not
    // If its not greater than we will not print it.
    // TC: O(N^2)
    // SC: O(1)
    public static void leadersInArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean isLeader = true;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    isLeader = false;
                }
            }

            if (isLeader) {
                System.out.println(arr[i]);
            }
        }
    }

    // Optimal: TC: O(NlogN), SC: O(N)
    // Explaination: Firstly we surely know that last element is always leader
    // And we will move from end of the array to the start
    // so by doing this we can track the max element
    // and that will help us to decide the leader
    // if the element is greater than max it is the leader
    // and at the same time we also have to update the max
    // we are storing this into list because we have to display those in sorted
    // order so we are using collections sort function
    public static ArrayList<Integer> leadersInArrayOptimal(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();

        int max = arr[arr.length - 1];
        list.add(max);
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > max) {
                System.out.println(arr[i]);
                max = arr[i];
                list.add(arr[i]);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        int[] arr = { 10, 22, 12, 3, 0, 6 };
        ArrayList<Integer> list = leadersInArrayOptimal(arr);
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);
    }
}
