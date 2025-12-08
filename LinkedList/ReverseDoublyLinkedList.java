package LinkedList;

import java.util.Stack;

public class ReverseDoublyLinkedList {

    public static Node printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
        return head;
    }

    // Brute Force Approach using Stack
    // Explanation:
    // 1. Traverse the doubly linked list and push all node data onto a stack
    // 2. Pop from the stack and assign the values back to the nodes
    // This effectively reverses the data in the nodes
    // Time Complexity: O(2N)
    // Space Complexity: O(N)
    public static Node reverseDLLBrute(Node head) {
        if (head == null || head.next == null)
            return head;

        Stack<Integer> stack = new Stack<>();

        Node temp = head;
        // Push all node data onto the stack
        while (temp != null) {
            stack.push(temp.data);
            temp = temp.next;
        }

        temp = head;
        // Pop from stack and assign to nodes
        while (temp != null) {
            temp.data = stack.pop();
            temp = temp.next;
        }

        return head;
    }

    public static Node reverseDLL(Node head) {

        // Edge case: empty list or single node -> nothing to reverse
        if (head == null || head.next == null) {
            return head;
        }

        Node current = head; // pointer to traverse the list
        Node last = null; // will eventually point to the new head

        // Traverse entire list and swap prev <-> next for each node
        while (current != null) {

            last = current.prev; // store current.prev temporarily

            // Swap the links
            current.prev = current.next; // reverse prev pointer
            current.next = last; // reverse next pointer

            // Move current to the "next" node in reversed direction
            // After swap, the next node to visit is stored in current.prev
            current = current.prev;
        }

        // At the end of the loop:
        // current becomes null
        // last points to the previous node of null
        // which is the *new head* of the reversed list

        return last.prev;
    }

    public static void main(String[] args) {
        // Creating a sample doubly linked list: 10 <-> 20 <-> 30 <-> 40 <-> null
        Node head = new Node(10);
        Node second = new Node(20);
        Node third = new Node(30);
        Node fourth = new Node(40);

        head.next = second;
        second.prev = head;
        second.next = third;
        third.prev = second;
        third.next = fourth;
        fourth.prev = third;

        System.out.println("Original Doubly Linked List:");
        printList(head);

        head = reverseDLLBrute(head);

        System.out.println("Reversed Doubly Linked List:");
        printList(head);

        head = reverseDLL(head);
        System.out.println("Reversed Again Doubly Linked List:");
        printList(head);
    }
}
