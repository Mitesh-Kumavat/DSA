package Stack;

public class ImplementStack {

    // Stack using Arrays
    static class StackUsingArray {
        int top = -1;
        int stack[] = new int[100];

        int push(int x) {
            if (top == stack.length - 1) {
                System.out.println("Stack Overflow");
                return -1;
            }
            stack[++top] = x;
            return x;
        }

        int top() {
            if (top == -1) {
                System.out.println("Stack is empty");
                return -1;
            }
            return stack[top];
        }

        int pop() {
            if (top == -1) {
                System.out.println("Stack Underflow");
                return -1;
            }
            return stack[top--];
        }

        int size() {
            return top + 1;
        }
    }

    // Stack Using Linked List
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    class StackUsingLinkedList {
        Node top;
        int size = 0;

        void push(int x) {
            Node newNode = new Node(x);
            newNode.next = top;
            top = newNode;
            size++;
        }

        int top() {
            if (top == null) {
                System.out.println("Stack is empty");
                return -1;
            }
            return top.data;
        }

        int pop() {
            if (top == null) {
                System.out.println("Stack Underflow");
                return -1;
            }
            int poppedData = top.data;
            top = top.next;
            size--;
            return poppedData;
        }

        int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        StackUsingArray s = new StackUsingArray();
        s.push(10);
        s.push(20);
        System.out.println(s.size()); // Output: 2
        System.out.println(s.top()); // Output: 20
        System.out.println(s.pop()); // Output: 20
        System.out.println(s.top()); // Output: 10

        StackUsingLinkedList sll = new ImplementStack().new StackUsingLinkedList();
        sll.push(30);
        sll.push(40);
        System.out.println(sll.size()); // Output: 2
        System.out.println(sll.top()); // Output: 40
        System.out.println(sll.pop()); // Output: 40
        System.out.println(sll.top()); // Output: 30
    }
}
