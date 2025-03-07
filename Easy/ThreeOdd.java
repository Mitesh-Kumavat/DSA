package Easy;

public class ThreeOdd {

    public static boolean isOdd(int n) {
        return n % 2 != 0;
    }

    public static boolean threeConsecutiveOdds(int[] arr) {

        if (arr.length < 3) {
            return false;
        }

        if (arr.length == 3) {
            if (isOdd(arr[0]) && isOdd(arr[1]) && isOdd(arr[2])) {
                return true;
            }
        }

        for (int i = 0; i < arr.length - 2; i++) {
            if (isOdd(arr[i]) && isOdd(arr[i + 1]) && isOdd(arr[i + 2])) {
                return true;
            }
        }

        return false;
    }

    // This is copied from the leetcode solution tab.
    public static boolean bestApproach(int[] arr) {
        int odds = 0;
        for (int i = 0; i < arr.length && odds < 3; i++) {
            odds = (arr[i] % 2 == 1) ? odds + 1 : 0;
        }
        return odds == 3;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 1, 1 };
        System.out.println(threeConsecutiveOdds(arr));
    }
}
