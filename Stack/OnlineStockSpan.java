package Stack;

import java.util.Stack;

/*
 * StockSpanner class calculates stock span for each incoming price.
 * Stock Span = Number of consecutive days (including today)
 *              where price was less than or equal to today's price.
 *
 * We use a MONOTONIC DECREASING STACK.
 * Stack stores: {price, index}
 */
class StockSpanner {

    // Stack stores pair -> [price, index]
    Stack<int[]> st;

    // Keeps track of current index (day number)
    int idx;

    public StockSpanner() {
        st = new Stack<>();
        idx = -1; // Start from -1 so first increment becomes 0
    }

    public int next(int price) {

        // Move to next day
        idx += 1;

        /*
         * Remove all previous prices which are
         * less than or equal to current price
         *
         * Why?
         * Because they cannot act as a boundary
         * for span anymore.
         */
        while (!st.isEmpty() && st.peek()[0] <= price) {
            st.pop();
        }

        int ans = 0;

        /*
         * If stack becomes empty:
         * Means no greater element on left.
         * So span = total days till now = idx + 1
         */
        if (st.isEmpty()) {
            ans = idx + 1;
        } else {
            /*
             * Otherwise:
             * Span = distance between current index
             * and index of previous greater price
             */
            ans = idx - st.peek()[1];
        }

        // Push current price and index into stack
        st.push(new int[] { price, idx });

        return ans;
    }
}

public class OnlineStockSpan {

    public static void main(String[] args) {

        StockSpanner stockSpanner = new StockSpanner();

        System.out.println(stockSpanner.next(100)); // 1
        System.out.println(stockSpanner.next(80)); // 1
        System.out.println(stockSpanner.next(60)); // 1
        System.out.println(stockSpanner.next(70)); // 2
        System.out.println(stockSpanner.next(60)); // 1
        System.out.println(stockSpanner.next(75)); // 4
        System.out.println(stockSpanner.next(85)); // 6
    }
}