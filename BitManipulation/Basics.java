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

    public static void main(String[] args) {
        int num = 12;
        System.out.println(converToBinary(num));
        System.out.println(converToBinaryIterative(num));
        System.out.println(binaryToDecimal("1010"));
    }
}
