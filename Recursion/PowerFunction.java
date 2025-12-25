package Recursion;

public class PowerFunction {

    // Helper method to calculate power using recursion
    private static double power(double x, long n) {
        // Base case: anything raised to 0 is 1
        if (n == 0)
            return 1.0;

        // Base case: anything raised to 1 is itself
        if (n == 1)
            return x;

        // If 'n' is even
        if (n % 2 == 0) {
            // Recursive call: square the base and halve the exponent
            return power(x * x, n / 2);
        }

        // If 'n' is odd
        // Recursive call: multiply base once and reduce exponent by 1
        return x * power(x, n - 1);
    }

    // Public method to handle negative exponents as well
    public static double myPow(double x, int n) {
        // If 'n' is negative, take reciprocal of positive exponent result
        if (n < 0) {
            return 1.0 / power(x, -n);
        }
        // If 'n' is non-negative
        return power(x, n);
    }

    public static void main(String[] args) {
        int x = 2;
        int n = 10;
        System.out.println(myPow(x, n));
    }
}
