package LinkedList;

public class ReverseSinglyLinkedList {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    /**
     * Iteratively reverse a singly linked list.
     * Idea:
     * - Maintain 3 pointers: prev, current (temp), next
     * - Break the next link and point current.next to prev
     * - Move prev and current forward
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static Node reverseList(Node head) {
        // Empty list or single node → no change
        if (head == null || head.next == null)
            return head;

        Node temp = head; // Current node being processed
        Node prev = null; // Previous node (initially null)
        Node nextEl; // Stores next node before breaking the link

        while (temp != null) {
            nextEl = temp.next; // Store next
            temp.next = prev; // Reverse the link
            prev = temp; // Move prev one step forward
            temp = nextEl; // Move temp one step forward
        }

        // prev now points to the new head of reversed list
        return prev;
    }

    /**
     * Recursively reverse a linked list.
     *
     * idea:
     * - Go to the last node (this will become the new head)
     * - While coming back (unwinding recursion), reverse the pointers
     *
     * Steps:
     * 1. Recursively reverse everything after head.
     * 2. head.next is now the last node of the reversed part.
     * 3. Point head.next.next back to head → this reverses the arrow.
     * 4. Set head.next = null → break the original forward link.
     */
    public static Node reverseListRecursively(Node head) {

        // Base case: if list is empty OR we reach the last node
        if (head == null || head.next == null) {
            return head; // This becomes the new head
        }

        // Reverse the rest of the list
        Node newHead = reverseListRecursively(head.next);

        // Reverse the link between head and head.next
        Node front = head.next;
        front.next = head;

        // Break the original forward link
        head.next = null;

        return newHead; // New head remains same for all calls
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        System.out.println("Original List:");
        printList(head);

        head = reverseList(head);
        System.out.println("Reversed List:");
        printList(head);
    }
}
