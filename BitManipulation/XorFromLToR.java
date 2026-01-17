package BitManipulation;

/*
    PROBLEM: XOR from L to R (Inclusive)

    Doing this directly in a loop would be O(n),
    but we can optimize it to O(1) using XOR properties.

    IMPORTANT XOR PROPERTIES:
    1. a ^ a = 0
    2. a ^ 0 = a

    KEY OBSERVATION:
    XOR from 1 to n follows a repeating pattern based on n % 4
*/

public class XorFromLToR {

    /*
     * This function returns XOR of all numbers from 1 to n
     * 
     * Pattern of XOR(1 to n):
     * 
     * n % 4 == 0 → result = n
     * n % 4 == 1 → result = 1
     * n % 4 == 2 → result = n + 1
     * n % 4 == 3 → result = 0
     * 
     * This works because XOR repeats every 4 numbers.
     */
    public static int xorUptoN(int n) {

        if (n % 4 == 0) {
            return n;
        } else if (n % 4 == 1) {
            return 1;
        } else if (n % 4 == 2) {
            return n + 1;
        } else {
            return 0;
        }
    }

    /*
     * XOR from L to R can be written as:
     * 
     * XOR(1 to R) ^ XOR(1 to L-1)
     * 
     * Reason:
     * Numbers from 1 to (L-1) cancel out because:
     * a ^ a = 0
     */
    public static int xorFromLToR(int l, int r) {
        return xorUptoN(r) ^ xorUptoN(l - 1);
    }

    public static void main(String[] args) {

        int l = 4;
        int r = 8;

        int result = xorFromLToR(l, r);

        System.out.println("XOR from " + l + " to " + r + " = " + result);
    }
}

/*
 * REVISION SHORT NOTES:
 * - XOR from 1 to n depends on n % 4
 * - XOR(L to R) = XOR(1 to R) ^ XOR(1 to L-1)
 */
