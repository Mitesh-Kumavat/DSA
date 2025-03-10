package Array;

import java.util.ArrayList;

public class UnionArray {

    public static ArrayList<Integer> findUnion(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int i = 0;
        int j = 0;
        int last = -1;
        ArrayList<Integer> unionArr = new ArrayList<>();

        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                if (last != arr1[i]) {
                    unionArr.add(arr1[i]);
                    last = arr1[i];
                }
                i++;
            } else {
                if (last != arr2[j]) {
                    unionArr.add(arr2[j]);
                    last = arr2[j];
                }
                j++;
            }
        }

        while (j < n2) {
            if (last != arr2[j]) {
                unionArr.add(arr2[j]);
                last = arr2[j];
            }
            j++;
        }

        while (i < n1) {
            if (last != arr1[i]) {
                unionArr.add(arr1[i]);
                last = arr1[i];
            }
            i++;
        }

        return unionArr;
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 1, 3, 3, 4, 5, 5 };
        int[] arr2 = { 1, 2, 2, 4, 5, 6, 6, 7, 8, 9, 9, 10, 1111, 11111 };
        ArrayList<Integer> li = findUnion(arr1, arr2);
        System.out.println(li);
    }
}
