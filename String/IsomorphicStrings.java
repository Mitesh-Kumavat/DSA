package String;

import java.util.HashMap;

public class IsomorphicStrings {

    /**
     * Check if two strings s and t are isomorphic.
     *
     * Idea:
     * - Characters in s must map to exactly one character in t.
     * - Also, two different characters in s cannot map to the same character in t.
     *
     * Example:
     * s = "egg", t = "add"
     * e -> a
     * g -> d
     *
     * Conditions to check:
     * 1) If a mapping exists for s[i], it must map to t[i].
     * 2) If no mapping exists yet, ensure t[i] is not already mapped
     * from another character (bijection requirement).
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) → only 256 possible chars
     */
    public static boolean isIsomorphic(String s, String t) {

        HashMap<Character, Character> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            char ch1 = s.charAt(i); // char from s
            char ch2 = t.charAt(i); // char from t

            // If ch1 was previously mapped
            if (map.containsKey(ch1)) {

                // But mapping doesn't match current character → invalid
                if (map.get(ch1) != ch2) {
                    return false;
                }

            } else {
                // New mapping → but if ch2 is already mapped to someone else → invalid
                if (map.containsValue(ch2))
                    return false;

                // Create mapping ch1 -> ch2
                map.put(ch1, ch2);
            }
        }

        return true; // all checks passed
    }

    /**
     * Optimal Isomorphic String Check
     *
     * Idea:
     * - Maintain two arrays of size 256:
     * map1[c] = the character in t that c from s maps to
     * map2[c] = the character in s that c from t maps from
     *
     * - This ensures:
     * 1) One-to-one mapping (bijection)
     * 2) No two chars in s map to same char in t
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) (always 256 size arrays)
     */
    public boolean isIsomorphicOptimal(String s, String t) {

        int[] mapST = new int[256]; // mapping s -> t
        int[] mapTS = new int[256]; // mapping t -> s

        for (int i = 0; i < s.length(); i++) {

            char a = s.charAt(i);
            char b = t.charAt(i);

            // If already mapped, mapping must match
            if (mapST[a] != 0 && mapST[a] != b)
                return false;

            // If t's character already mapped to someone else → invalid
            if (mapTS[b] != 0 && mapTS[b] != a)
                return false;

            // Create mapping (if not mapped earlier)
            mapST[a] = b;
            mapTS[b] = a;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "foo";
        String t = "bdd";
        System.out.println(isIsomorphic(s, t));
    }
}
