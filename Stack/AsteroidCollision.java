package Stack;

import java.util.Arrays;
import java.util.Stack;

public class AsteroidCollision {

    /*
     * RULES:
     * +ve → moving right
     * -ve → moving left
     *
     * Collision only when:
     * stack.peek() > 0 AND current < 0
     *
     * Bigger asteroid survives.
     * Equal size → both destroyed.
     */

    public static int[] asteroidCollision(int[] arr) {

        Stack<Integer> stack = new Stack<>();

        for (int current : arr) {

            boolean isDestroyed = false;

            // Collision possible only if current is moving left
            if (current < 0) {

                // While top of stack is moving right → possible collision
                while (!stack.isEmpty() && stack.peek() > 0) {

                    int top = stack.peek();

                    // Case 1: current is bigger → destroy top
                    if (Math.abs(current) > top) {
                        stack.pop(); // remove top and continue checking
                    }

                    // Case 2: equal size → both destroyed
                    else if (Math.abs(current) == top) {
                        stack.pop();
                        isDestroyed = true;
                        break;
                    }

                    // Case 3: top is bigger → current destroyed
                    else {
                        isDestroyed = true;
                        break;
                    }
                }
            }

            // If current asteroid survived → push into stack
            if (!isDestroyed) {
                stack.push(current);
            }
        }

        // Convert stack to array
        int[] ans = new int[stack.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] arr = { 3, 5, -6, 2, -1, 4 };
        int[] arr2 = { 8, -8 };
        int[] arr3 = { -2, -1, 1, 2 };
        int[] arr4 = { -2, 1, 1, -1 };

        System.out.println(Arrays.toString(asteroidCollision(arr)));
        System.out.println(Arrays.toString(asteroidCollision(arr2)));
        System.out.println(Arrays.toString(asteroidCollision(arr3)));
        System.out.println(Arrays.toString(asteroidCollision(arr4)));
    }
}