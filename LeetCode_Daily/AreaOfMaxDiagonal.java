// LeetCode Problem Link: https://leetcode.com/problems/maximum-area-of-longest-diagonal-rectangle

class AreaOfMaxDiagonal {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int index = 0;
        int maxAns = Integer.MIN_VALUE;
        for (int i = 0; i < dimensions.length; i++) {
            int l = dimensions[i][0];
            int b = dimensions[i][1];
            int ans = (l * l) + (b * b);
            if (ans > maxAns || (ans == maxAns && (l * b) > dimensions[index][0] * dimensions[index][1])) {
                maxAns = ans;
                index = i;
            }
        }
        return dimensions[index][0] * dimensions[index][1];
    }
}