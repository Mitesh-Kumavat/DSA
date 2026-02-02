package Stack;

import java.util.Queue;

public class StackUsingQueues {
    Queue<Integer> stack = new java.util.LinkedList<>();

    public void push(int x) {
        int size = stack.size();
        stack.add(x);
        for (int i = 0; i < size; i++) {
            stack.add(stack.remove());
        }

    }

    public int pop() {
        return stack.remove();
    }

    public int top() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // usage
        StackUsingQueues s = new StackUsingQueues();
        s.push(1);
        s.push(2);
        System.out.println(s.top()); // returns 2
        System.out.println(s.pop()); // returns 2
        System.out.println(s.empty()); // returns false
    }
}
