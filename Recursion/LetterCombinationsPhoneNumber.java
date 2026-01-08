package Recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterCombinationsPhoneNumber {

    /**
     * Backtracking function to build all possible letter combinations.
     *
     * @param index  -> current position in the input digits string
     * @param digits -> input digit string (e.g., "23")
     * @param comb   -> current combination being built
     * @param ans    -> list storing all valid combinations
     * @param map    -> digit to letters mapping (phone keypad)
     */
    public static void backtrack(
            int index,
            String digits,
            StringBuilder comb,
            List<String> ans,
            HashMap<Character, String> map) {

        /*
         * BASE CASE:
         * If we've processed all digits,
         * the current combination is complete.
         */
        if (index == digits.length()) {
            ans.add(comb.toString());
            return;
        }

        // Get the possible letters for the current digit
        String letters = map.get(digits.charAt(index));

        /*
         * Try each letter mapped to the current digit
         */
        for (char letter : letters.toCharArray()) {

            // Choose the current letter
            comb.append(letter);

            // Recurse for the next digit
            backtrack(index + 1, digits, comb, ans, map);

            // Backtrack:
            // Remove the last added letter to try another option
            comb.deleteCharAt(comb.length() - 1);
        }
    }

    /**
     * Main function to generate all letter combinations
     * for the given phone number digits.
     */
    public List<String> letterCombinations(String digits) {

        List<String> ans = new ArrayList<>();

        // Edge case: if input is empty, return empty list
        if (digits == null || digits.length() == 0) {
            return ans;
        }

        // Mapping digits to corresponding letters
        HashMap<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        // Start backtracking from index 0
        backtrack(0, digits, new StringBuilder(), ans, map);

        return ans;
    }

    public static void main(String[] args) {
        String digits = "23";
        LetterCombinationsPhoneNumber lcpn = new LetterCombinationsPhoneNumber();
        System.out.println(lcpn.letterCombinations(digits));

    }
}
