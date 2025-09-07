package Array;

public class ContainerWithMostWater {

    // Brute force:
    // generate every single pair who can store the water
    // and calculate their capicity and compare every time with max capicity
    // so this way we can just run two loops one loop i -> o to n
    // second loop j -> i+1 to n
    // TC: O(N^2)
    // SC: O(1)
    public static int maxArea(int[] arr) {
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] <= arr[j]) {
                    ans = Math.max(ans, arr[i] * (j - i));
                } else if (arr[i] >= arr[j]) {
                    ans = Math.max(ans, arr[j] * (j - i));
                }
            }
        }

        return ans;
    }

    // Optimal Approach: Using two pointers
    // keep one pointer i to the start of the array and j to the end of the array
    // now calculate the area to store the water and move pointers accordingly
    // Q: How to move pointers?
    // A: if i-th element is small then move i-th pointer ahead because we know
    // j-the element will be greater than i-th, and if j-th element is small then decrease j
    // so we can now easily find the answer using one iteration only
    // TC: O(N)
    // SC: O(1)
    public static int maxAreaOptimal(int[] arr) {
        int ans = 0;
        int i = 0;
        int j = arr.length - 1;

        while (i <= j) {
            if (arr[i] <= arr[j]) {
                int length = j - i;
                ans = Math.max(arr[i] * length, ans);
                i++;
            } else {
                int length = j - i;
                ans = Math.max(arr[j] * length, ans);
                j--;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        System.out.println(maxArea(arr));
        System.out.println(maxAreaOptimal(arr));
    }
}
