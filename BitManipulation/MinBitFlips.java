package BitManipulation;

public class MinBitFlips {
    public static int minBitFlips(int a, int b) {
        // Dry Run:
        // a = 29 (Binary: 11101)
        // b = 15 (Binary: 01111)
        // xor = a ^ b = 11101 ^ 01111 = 10010
        // Now, count the number of 1's in xor (which indicates differing bits)

        // Why XOR?: XOR operation between two bits results in 1 if the bits are different
        // and 0 if they are the same. Thus, by performing XOR between a and b, we can
        // easily identify the bits that need to be flipped to convert a to b.
        int xor = a ^ b;
        int count = 0;

        while (xor > 0) {
            if ((xor & 1) == 1) {
                count++;
            }
            xor = xor >> 1;
        }

        return count;
    }

    public static void main(String[] args) {
        int a = 29; // Binary: 11101
        int b = 15; // Binary: 01111

        // Output: 3
        int result = minBitFlips(a, b);
        System.out.println("Minimum bit flips required to convert " + a + " to " + b + " is: " + result);
    }
}