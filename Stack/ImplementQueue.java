package Stack;

public class ImplementQueue {
    // Queue using Arrays
    public class QueueUsingArray {
        int front = -1;
        int rear = -1;
        int queue[] = new int[100];

        void push(int x) {
            if (rear == queue.length - 1) {
                System.out.println("Queue Overflow");
                return;
            }
            if (front == -1) {
                front = 0;
            }
            queue[++rear] = x;
        }

        int top() {
            if (front == -1 || front > rear) {
                System.out.println("Queue is empty");
                return -1;
            }
            return queue[front];
        }

        int pop() {
            if (front == -1 || front > rear) {
                System.out.println("Queue Underflow");
                return -1;
            }
            return queue[front++];
        }

        int size() {
            if (front == -1) {
                return 0;
            }
            return rear - front + 1;
        }
    }

    // Queue Using Linked List
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    class QueueUsingLinkedList {
        Node front, rear;
        int size = 0;

        void push(int x) {
            Node newNode = new Node(x);
            if (front == null) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
        }

        int top() {
            if (front == null) {
                System.out.println("Queue is empty");
                return -1;
            }
            return front.data;
        }

        int pop() {
            if (front == null) {
                System.out.println("Queue Underflow");
                return -1;
            }
            int data = front.data;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            size--;
            return data;
        }

        int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        QueueUsingArray q = new ImplementQueue().new QueueUsingArray();
        q.push(10);
        q.push(20);
        System.out.println(q.size()); // Output: 2
        System.out.println(q.top()); // Output: 10
        System.out.println(q.pop()); // Output: 10
        System.out.println(q.top()); // Output: 20

        QueueUsingLinkedList qll = new ImplementQueue().new QueueUsingLinkedList();
        qll.push(30);
        qll.push(40);
        System.out.println(qll.size()); // Output: 2
        System.out.println(qll.top()); // Output: 30
        System.out.println(qll.pop()); // Output: 30
        System.out.println(qll.top()); // Output: 40

    }
}
