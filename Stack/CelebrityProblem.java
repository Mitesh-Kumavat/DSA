package Stack;

import java.util.Stack;

/*
 * CELEBRITY PROBLEM
 *
 * A Celebrity is a person who:
 * 1) Everyone knows them.
 * 2) They know no one.
 *
 * If there are N people:
 * - Row represents: whom I know
 * - Column represents: who knows me
 *
 * arr[i][j] == 1  →  i knows j
 * arr[i][j] == 0  →  i does NOT know j
 */

public class CelebrityProblem {

    /*
     * APPROACH 1: Brute Force using Counting Arrays
     *
     * Idea:
     * - Count how many people know each person (knowMe[])
     * - Count how many people each person knows (iKnow[])
     *
     * Celebrity Conditions:
     * - knowMe[i] == n-1 (everyone knows i)
     * - iKnow[i] == 0 (i knows nobody)
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(n)
     */
    public static int findCelebrity(int[][] arr) {

        int[] knowMe = new int[arr.length]; // how many people know me
        int[] iKnow = new int[arr.length]; // how many people I know

        // Fill counting arrays
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {

                if (arr[i][j] == 1) {
                    knowMe[j]++; // j is known by i
                    iKnow[i]++; // i knows j
                }
            }
        }

        // Check celebrity condition
        for (int i = 0; i < arr.length; i++) {
            if (knowMe[i] == arr.length - 1 && iKnow[i] == 0) {
                return i;
            }
        }

        return -1; // no celebrity found
    }

    /*
     * APPROACH 2: Two Pointer (Optimal O(n))
     *
     * Idea:
     * We eliminate non-celebrities step by step.
     *
     * If person i knows j:
     * → i cannot be celebrity
     * → move i forward
     *
     * If person i does NOT know j:
     * → j cannot be celebrity
     * → move j backward
     *
     * After loop ends,
     * only one potential candidate remains.
     *
     * Then we VERIFY that candidate, by checking:
     * 1) They know no one (row should be all 0s)
     * 2) Everyone knows them (column should be all 1s except diagonal)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int findCelebOptimal(int[][] arr) {

        int n = arr.length;
        int i = 0, j = n - 1;

        // Elimination phase
        while (i < j) {
            if (arr[i][j] == 1) {
                // i knows j → i cannot be celebrity
                i++;
            } else {
                // i does NOT know j → j cannot be celebrity
                j--;
            }
        }

        // Now i is potential celebrity

        // Verification phase
        for (int k = 0; k < n; k++) {

            if (k != i) {

                // Celebrity must:
                // 1) Not know anyone
                // 2) Be known by everyone

                if (arr[i][k] == 1 || arr[k][i] == 0) {
                    return -1; // not a celebrity
                }
            }
        }

        return i;
    }

    /*
     * APPROACH 3: Stack Based Elimination
     *
     * Idea:
     * Push all people into stack.
     * Compare two at a time and eliminate one.
     *
     * If a knows b:
     * → a cannot be celebrity → keep b
     *
     * Else:
     * → b cannot be celebrity → keep a
     *
     * Continue until one person remains.
     *
     * Then verify that last person.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int findCelebrityStack(int[][] arr) {

        int n = arr.length;
        Stack<Integer> st = new Stack<>();

        // Push all people into stack
        for (int i = 0; i < n; i++) {
            st.push(i);
        }

        // Elimination process
        while (st.size() > 1) {

            int a = st.pop();
            int b = st.pop();

            if (arr[a][b] == 1) {
                // a knows b → a cannot be celebrity
                st.push(b);
            } else {
                // a does NOT know b → b cannot be celebrity
                st.push(a);
            }
        }

        // Last remaining candidate
        int celeb = st.pop();

        // Final verification
        for (int i = 0; i < n; i++) {

            if (i != celeb) {

                if (arr[celeb][i] == 1 || arr[i][celeb] == 0) {
                    return -1;
                }
            }
        }

        return celeb;
    }

    public static void main(String[] args) {

        int[][] matrix = {
                { 0, 0, 1, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 1, 0 }
        };

        System.out.println(findCelebrity(matrix)); // Brute
        System.out.println(findCelebOptimal(matrix)); // Optimal
        System.out.println(findCelebrityStack(matrix)); // Stack
    }
}