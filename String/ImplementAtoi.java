package String;

public class ImplementAtoi {

    public static int myAtoi(String s) {

        // If string is null or empty, return 0 (no conversion possible)
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Integer limits for 32-bit signed integers
        final int INT_MAX = Integer.MAX_VALUE; // 2147483647
        final int INT_MIN = Integer.MIN_VALUE; // -2147483648

        int i = 0;
        int n = s.length();

        // STEP 1: Skip all leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // If string contains only spaces → return 0
        if (i == n) {
            return 0;
        }

        // STEP 2: Check for an optional sign (+/-)
        int sign = 1;
        if (s.charAt(i) == '+') {
            i++;
        } else if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        }

        // STEP 3: Convert digit characters into number
        // Using long so we can detect overflow BEFORE we cast to int
        long res = 0;

        // Loop until non-digit is found
        while (i < n && Character.isDigit(s.charAt(i))) {

            // Convert digit character to integer
            int digit = s.charAt(i) - '0';

            // Build the number: res = (previous * 10) + new digit
            res = res * 10 + digit;

            // STEP 4: Overflow handling BEFORE final conversion
            // If number crosses 32-bit range → clamp to MIN or MAX
            if (sign * res <= INT_MIN) { // Underflow
                return INT_MIN;
            }
            if (sign * res >= INT_MAX) { // Overflow
                return INT_MAX;
            }

            i++;
        }

        // STEP 5: Apply sign and return the final integer
        return (int) (res * sign);
    }

    public static void main(String[] args) {
        String s = "   -42";
        System.out.println(myAtoi(s)); // Output: -42

        String s2 = "4193 with words";
        System.out.println(myAtoi(s2)); // Output: 4193
    }
}
