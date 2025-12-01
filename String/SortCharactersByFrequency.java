package String;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharactersByFrequency {

    // Optimal Approach: Using HashMap + Max-Heap
    public static String sortByFreq(String s) {

        // Step 1: Count frequency of each character
        Map<Character, Integer> mp = new HashMap<>();
        for (char c : s.toCharArray()) {
            mp.put(c, mp.getOrDefault(c, 0) + 1); // increment frequency
        }

        // Step 2: Max-Heap based on frequency (highest freq at top)
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue());

        pq.addAll(mp.entrySet()); // add all (char, freq) pairs

        // Step 3: Build answer - append each char frequency times
        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();

            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey()); // add the char freq times
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "tree";
        System.out.println(sortByFreq(s)); // outputs: eetr or eert
    }
}
