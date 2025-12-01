package String;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    // Convert a Roman numeral string into its integer value.
    // Core rule:
    // - If a Roman symbol is smaller than the symbol AFTER it → subtract.
    // - Otherwise → add.
    public static int romanToInt(String s) {
        int ans = 0;

        // Mapping of Roman characters to their integer values
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        // Traverse until second last character because each iteration compares s[i] with s[i+1]
        for (int i = 0; i < s.length() - 1; i++) {
            int curr = map.get(s.charAt(i)); // current symbol value
            int next = map.get(s.charAt(i + 1)); // next symbol value

            // If current < next → subtract (case like IV → 4, IX → 9)
            if (curr < next) {
                ans -= curr;
            }
            // Otherwise → normal addition
            else {
                ans += curr;
            }
        }

        // Always add the last character because it has no next character to compare
        return ans + map.get(s.charAt(s.length() - 1));
    }

    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(romanToInt(s)); // outputs: 1994
    }
}
