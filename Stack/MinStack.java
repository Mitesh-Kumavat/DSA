package Stack;

import java.util.*;

class Pair {
    int a, b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return this.a;
    }

    public int getB() {
        return this.b;
    }
}

class MyStackOptimal {

    Stack<Long> st = new Stack<>();
    long min;
    
    public void push(int val) {
        if (st.isEmpty()) {
            min = val;
            st.push((long) val);
        } else if (val >= min) {
            st.push((long) val);
        } else {
            st.push(2L * val - min);
            min = val;
        }
    }

    public void pop() {
        if (st.isEmpty())
            return;

        long x = st.pop();
        if (x < min) {
            min = 2 * min - x;
        }
    }

    public int top() {
        if (st.isEmpty())
            return -1;

        long x = st.peek();
        return (x >= min) ? (int) x : (int) min;
    }

    public int getMin() {
        if (st.isEmpty())
            return -1;
        return (int) min;
    }
}

public class MinStack {

    // brute force
    Stack<Pair> st = new Stack<>();

    public void push(int x) {
        if (st.isEmpty()) {
            st.push(new Pair(x, x));
        } else {
            Pair p = st.peek();

            if (p.getB() < x) {
                st.push(new Pair(x, p.getB()));
            } else {
                st.push(new Pair(x, x));
            }
        }
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        Pair p = st.peek();

        return p.getA();
    }

    public int getMin() {
        Pair p = st.peek();
        return p.getB();
    }

    public static void main(String[] args) {
        // Implementation

        MyStackOptimal minStack = new MyStackOptimal();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top()); // return 0
        System.out.println(minStack.getMin()); // return -2
    }
}
