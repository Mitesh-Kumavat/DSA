package String;

public class CountCollisionsOnRoad {

    public static int countCollisions(String directions) {
        int n = directions.length();
        int left = 0, right = n - 1;

        /*
         * Problem Logic:
         * Cars moving:
         * 'L' → left
         * 'R' → right
         * 'S' → stay
         *
         * A car contributes to a collision only when:
         * - It is forced to stop due to other cars.
         *
         * Observation:
         * 1. Leading 'L' cars (at the very left) will always move outwards → no
         * collision.
         * 2. Trailing 'R' cars (at the very right) will also move outward → no
         * collision.
         *
         * Everything inside [left, right] range is guaranteed to collide if it's L or
         * R.
         * Cars marked 'S' do not add to the count.
         */

        // Skip all leading L's (these cars freely go outside, no collision)
        while (left < n && directions.charAt(left) == 'L')
            left++;

        // Skip all trailing R's (these also move outward freely)
        while (right >= 0 && directions.charAt(right) == 'R')
            right--;

        /*
         * Now count collisions in the valid zone:
         * For every 'L' or 'R', a collision WILL happen.
         * Reason:
         * - At least one car will stop → the moving cars behind/against it will hit.
         */
        int collisions = 0;
        for (int i = left; i <= right; i++) {
            char c = directions.charAt(i);

            // Only moving cars cause collisions
            if (c == 'L' || c == 'R')
                collisions++;
        }

        return collisions;
    }

    public static void main(String[] args) {
        String directions1 = "RLRSLL";
        System.out.println("Collisions in '" + directions1 + "': " + countCollisions(directions1)); // Output: 5

        String directions2 = "LLRR";
        System.out.println("Collisions in '" + directions2 + "': " + countCollisions(directions2)); // Output: 0

        String directions3 = "SSRSSRLLRSLLRSRSSRLRRRRLLRRLSSRR";
        System.out.println("Collisions in '" + directions3 + "': " + countCollisions(directions3)); // Output: 20
    }
}
