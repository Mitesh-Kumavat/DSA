package Array;

public interface MaxProduct {

    // Brute force: Generate every subarray from i -> j
    // and calculate product of every element of subarr
    // and compare it with max product
    // TC: O(N^2), SC: Constant
    public static int maxProductBrute(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int maxProduct = arr[0];

        for (int i = 0; i < arr.length; i++) {
            int product = 1;
            for (int j = i; j < arr.length; j++) {
                product *= arr[j];

                maxProduct = Math.max(product, maxProduct);
            }
        }
        return maxProduct;
    }

    // Optimal Approach: visit takeuforward's site
    // TC: O(N), SC: Constant
    public static int maxProductOptimal(int[] arr) {
        int maxProduct = Integer.MIN_VALUE;

        int prefix = 1;
        int suffix = 1;

        for (int i = 0; i < arr.length; i++) {
            if (prefix == 0) {
                prefix = 1;
            }
            if (suffix == 0) {
                suffix = 1;
            }

            prefix *= arr[i];
            suffix *= arr[arr.length - i - 1];
            maxProduct = Math.max(maxProduct, Math.max(prefix, suffix));
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        int[] arr = { -2 };
        System.out.println(maxProductOptimal(arr));
    }
}
