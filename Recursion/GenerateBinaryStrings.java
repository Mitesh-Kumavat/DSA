package Recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateBinaryStrings {

    /*
     * INTUITION:
     * - We build the string character by character
     * - '0' is always safe to add
     * - '1' can be added ONLY if previous character is NOT '1'
     */
    public static void backtrack(List<String> ans, String current, int n) {

        // BASE CASE: string length reached n
        if (current.length() == n) {
            ans.add(current);
            return;
        }

        // 1: Always add '0'
        backtrack(ans, current + "0", n);

        // 2: Add '1' only if last char is NOT '1'
        if (!current.endsWith("1")) {
            backtrack(ans, current + "1", n);
        }
    }

    public static List<String> generateBinaryStrings(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", n);
        return ans;
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(generateBinaryStrings(n));
    }
}
