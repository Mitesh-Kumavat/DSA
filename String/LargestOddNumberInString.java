package String;

public class LargestOddNumberInString {

    /**
     * Returns the largest odd number that is a prefix of the given string.
     *
     * Logic:
     * - Traverse from the end.
     * - The first odd digit you find determines the answer.
     * - Return substring from 0 to that index.
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static String largestOddNumber(String num) {

        int i = num.length() - 1;

        // move left until we find an odd digit
        while (i >= 0) {

            // if current digit is odd → answer found
            if ((num.charAt(i) - '0') % 2 == 1) {
                return num.substring(0, i + 1);
            }

            i--;
        }

        // no odd number found
        return "";
    }

    public static void main(String[] args) {
        String num = "35427";
        System.out.println(largestOddNumber(num)); // output: 35427
    }
}
