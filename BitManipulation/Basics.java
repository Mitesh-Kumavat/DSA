package BitManipulation;

public class Basics {

    public static String backtrack(String s, int num) {
        if (num == 0) {
            return s;
        }

        if (num % 2 == 1) {
            s = "1" + s;
        } else {
            s = "0" + s;
        }

        return backtrack(s, num / 2);
    }

    public static String converToBinary(int num) {
        return backtrack("", num);
    }

    public static String converToBinaryIterative(int num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            if (num % 2 == 1) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            num = num / 2;
        }

        return sb.reverse().toString();
    }

    public static int binaryToDecimal(String s) {
        int n = s.length();
        int ans = 0;
        int p2 = 1;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                ans += p2;
            }

            p2 *= 2;
        }

        return ans;
    }

    public static void swapTwoNumbers(int a, int b) {

        // Explaination: when we do XOR operation between two same bits, it results in
        // 0 (i.e., 1 ^ 1 = 0 and 0 ^ 0 = 0).

        // So, when we do XOR between a and b and store it in a, a now contains the
        // combined information of both a and b.

        // When we do XOR between this new a and b, the b's information gets canceled
        // out, leaving us with the original a. (since a ^ b ^ b = a)

        // Finally, when we do XOR between the new a (which is a ^ b) and the new b
        // (which is original a), the original b's information gets canceled out,
        // leaving us with the original b. (since a ^ b ^ a = b)

        a = a ^ b; // a = a ^ b
        b = a ^ b; // b = (a ^ b) ^ b = a (here b ^ b = 0 and a ^ 0 = a)
        a = a ^ b; // a = (a ^ b) ^ a = b (here a ^ a = 0 and b ^ 0 = b)

        System.out.println("a: " + a + ", b: " + b);
    }

    public static boolean isIthBitSet(int num, int i) {

        // Explaination: A bitmask is created by left-shifting 1 by i positions (1 <<
        // i).
        // This bitmask has only the ith bit set to 1, while all other bits are 0. When
        // we perform a bitwise AND operation
        // between num and the bitmask (num & mask), it effectively isolates the ith bit
        // of num. If the ith bit of num is set (1), the result of the AND operation
        // will be non-zero; otherwise, it will be zero.

        // Dry Run:
        // Let's say num = 13 (which is 1101 in binary) and i = 2.

        // 1. Create the bitmask:
        // mask = 1 << 2; -> This results in 0100 in binary.

        // 2. Perform the AND operation:
        // result = num & mask; -> 1101 & 0100 = 0100 (which is Non zero).

        // 3. Check if the result is non-zero:
        // Since result (4) is not equal to 0, it indicates that the 2nd bit of num is
        // set.

        int mask = 1 << i;
        return (num & mask) != 0;

        // Another Approach:

        // return ((num >> i) & 1) == 1;

        // Dry Run:
        // Let's say num = 13 (which is 1101 in binary) and i = 2.

        // 1. Right shift num by i positions:
        // shiftedNum = num >> 2; -> This results in 0011 in binary

        // 2. Perform the AND operation with 1:
        // result = shiftedNum & 1; -> 0011 & 0001 = 0001 (which is 1).

        // 3. Check if the result equals 1:
        // Since result (1) is equal to 1, it indicates that the 2nd bit of num is set.

    }

    public static void setIthBit(int num, int i) {

        // Explaination: A bitmask is created by left-shifting 1 by i positions (1 <<
        // i).
        // This bitmask has only the ith bit set to 1, while all other bits are 0. When
        // we perform a bitwise OR operation
        // between num and the bitmask (num | mask), it sets the ith bit of num to 1,
        // regardless of its previous state. All other bits in num remain unchanged.

        // Dry Run:
        // Let's say num = 9 (which is 1001 in binary) and i = 2.

        // 1. Create the bitmask:
        // mask = 1 << 2; -> This results in 0100 in binary

        // 2. Perform the OR operation:
        // result = num | mask; -> 1001 | 0100 = 1101 (which is 13 in decimal).

        int mask = 1 << i;
        int result = num | mask;
        System.out.println("Result after setting the " + i + "th bit: " + result);
    }

    public static void clearIthBit(int num, int i) {

        // Explaination: A bitmask is created by left-shifting 1 by i positions (1 << i)
        // and then negating it (~). This bitmask has all bits set to 1 except for the
        // ith bit, which is set to 0. When we perform a bitwise AND operation between
        // num and the negated bitmask (num & ~mask), it clears the ith bit of num to 0,
        // regardless of its previous state. All other bits in num remain unchanged.

        // Dry Run:
        // Let's say num = 13 (which is 1101 in binary) and i = 2.

        // 1. Create the bitmask:
        // mask = 1 << 2; -> This results in 0100 in binary.
        // negatedMask = ~mask; -> This results in 1011 in binary.

        // 2. Perform the AND operation:
        // result = num & negatedMask; -> 1101 & 1011 = 1001 (which is 9 in decimal).

        int mask = 1 << i;
        int negatedMask = ~mask;
        int result = num & negatedMask;
        System.out.println("Result after clearing the " + i + "th bit: " + result);
    }

    public static void toggleIthBit(int num, int i) {

        // Dry Run:
        // Let's say num = 12 (which is 1100 in binary) and i = 2.

        // 1. Create the bitmask:
        // mask = 1 << 2; -> This results in 0100 in binary

        // 2. Perform the XOR operation:
        // result = num ^ mask; -> 1100 ^ 0100 = 1000 (which is 8 in decimal).

        // XOR operation toggles the bit: if it's 0, it becomes 1; if it's 1, it becomes
        // 0.

        int mask = 1 << i;
        int result = num ^ mask;
        System.out.println("Result after toggling the " + i + "th bit: " + result);
    }

    public static void removeLastSetBit(int num) {

        // Dry Run:
        // Let's say num = 12 (which is 1100 in binary).

        // 1. Subtract 1 from num:
        // num - 1 = 11 -> This results in 1011 in binary.

        // 2. Perform the AND operation:
        // result = num & (num - 1); -> 1100 & 1011 = 1000 (which is 8 in decimal).

        // This operation effectively removes the last set bit (rightmost 1) from num.

        // For Detailed Explanation:
        // When we subtract 1 from a binary number, all bits to the right of the
        // rightmost set bit (1) are flipped, and the rightmost set bit itself is turned
        // off (changed to 0). By performing a bitwise AND operation between the
        // original
        // number and the number obtained after subtracting 1, we effectively remove the
        // rightmost set bit from the original number, while keeping all other bits
        // unchanged.

        // For Example: num => 84 (which is 1010100 in binary)
        // num - 1 => 83 (which is 1010011 in binary)
        // num & (num - 1) => 1010100 & 1010011 = 1010000 (which is 80 in decimal)

        int result = num & (num - 1);
        System.out.println("Result after removing the last set bit: " + result);
    }

    public static boolean isPowerOfTwo(int num) {

        // Dry Run:
        // Let's say num = 16 (which is 10000 in binary).

        // 1. Subtract 1 from num:
        // num - 1 = 15 -> This results in 01111 in binary.

        // 2. Perform the AND operation:
        // result = num & (num - 1); -> 10000 & 01111 = 00000 (which is 0).

        // If num is a power of two, it has exactly one set bit, and num & (num - 1)
        // will be 0.

        // Additionally, we check if num is greater than 0 to exclude non-positive
        // numbers.

        return (num > 0) && ((num & (num - 1)) == 0);
    }

    public static int countSetBits(int num) {
        int count = 0;

        while (num > 0) {
            if ((num & 1) == 1) {  // checking whether the last bit is set or not
                count++;
            }
            num = num >> 1;  // this is equivalent to num = num / 2;
        }

        return count;
    }

    public static void main(String[] args) {
        int num = 12;
        System.out.println(converToBinary(num));
        System.out.println(converToBinaryIterative(num));
        System.out.println(binaryToDecimal("1010"));
        swapTwoNumbers(5, 10);
        System.out.println(isIthBitSet(13, 2));
        setIthBit(9, 2);
        clearIthBit(13, 2);
        toggleIthBit(12, 2);
        removeLastSetBit(12);
        System.out.println(isPowerOfTwo(16));
        System.out.println(countSetBits(13));
    }
}
