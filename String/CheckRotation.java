package String;

public class CheckRotation {

    /**
     * Brute-force approach to check if goal is a rotation of s.
     *
     * Logic:
     * - If lengths differ → cannot be rotations.
     * - Try every possible rotation of string s.
     * - For each i, rotate s by taking substring(i to end) + substring(0 to i).
     * - If any rotated version equals goal → it's a valid rotation.
     *
     * Time Complexity: O(n²) because each rotation + comparison costs O(n)
     * Space Complexity: O(n) due to creating new rotated strings.
     */
    public static boolean checkRotation(String s, String goal) {
        if (s.length() != goal.length())
            return false;

        // Generate all possible rotations of s
        for (int i = 0; i < s.length(); i++) {

            // Rotate by splitting at index i:
            // Example: s = "abcde", i=2 → "cde" + "ab" = "cdeab"
            String rotated = s.substring(i) + s.substring(0, i);

            // If rotation matches goal → return true
            if (rotated.equals(goal)) {
                return true;
            }
        }

        // No rotation matched
        return false;
    }

    /**
     * Optimal approach to check string rotation.
     *
     * Key Idea:
     * - A rotated string MUST appear as a substring of (s + s).
     * - Example:
     * s = "abcde"
     * s + s = "abcdeabcde"
     * goal = "cdeab" → Yes, it appears inside.
     *
     * Steps:
     * - If lengths differ → cannot be rotations.
     * - Create doubled = s + s.
     * - Just check if goal exists in this doubled string.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     */
    public static boolean checkRotationOptimal(String s, String goal) {
        if (s.length() != goal.length())
            return false;

        // Concatenate string with itself
        String doubled = s + s;

        // If goal is a substring → rotation exists
        if (doubled.contains(goal)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        String s = "abcde";
        String s2 = "abcdd";
        String goal = "cdeab";

        System.out.println(checkRotation(s, goal)); // true
        System.out.println(checkRotationOptimal(s2, goal)); //false
    }
}
