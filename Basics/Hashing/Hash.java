package Basics.Hashing;
/* 
Example: 1
Input: arr[] = {10,5,10,15,10,5};
Output: 10  3
	    5   2
        15  1
Explanation:10 occurs 3 times in the array
	        5 occurs 2 times in the array
            15 occurs 1 time in the array
Example: 2
Input: str = "ababds";
Output: a  2
	    b  2
        d  1
        s  1
Explanation:10 occurs 3 times in the array
	        5 occurs 2 times in the array
            15 occurs 1 time in the array
*/

import java.util.HashMap;

public class Hash {

    public static void countFrequency(int[] arr) {
        int hash[] = new int[1000];

        for (int i = 0; i < arr.length; i++) {
            hash[arr[i]] += 1;
        }

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != 0) {
                System.out.println(i + " " + hash[i]);
            }
        }
    }

    public static void countCharFrequency(String str) {
        HashMap<Character, Integer> hm = new HashMap<>();

        // Time complexity of the Map is log n in every case (best, average and worst)
        // if we have to set the element in the map it is log n.
        // if we have to print the map it is also log n

        // But the time complexity of the unorder map is O(1) in best and average case
        // in the worst case it will be O(N)

        // In Java, there is no direct equivalent to C++'s unordered_map.
        // However, HashMap in Java provides similar functionality with average O(1)
        // time complexity for get and put operations.
        for (int i = 0; i < str.length(); i++) {
            if (hm.containsKey(str.charAt(i))) {
                hm.put(str.charAt(i), hm.get(str.charAt(i)) + 1);
            } else {
                hm.put(str.charAt(i), 1);
            }
        }

        for (HashMap.Entry<Character, Integer> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        // Printing the complete Hashmap
        System.out.println(hm);
    }

    public static void main(String[] args) {
        int arr[] = { 10, 5, 10, 15, 10, 5 };
        countFrequency(arr);
        countCharFrequency("ABADEDsafdasfsafsfdABS");
    }
}