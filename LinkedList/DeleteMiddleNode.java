package LinkedList;

public class DeleteMiddleNode {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Brute force: Count the number of nodes, find the middle, and delete it

    // Optimal approach: Use fast and slow pointers to find the middle node in one pass
    public static Node deleteMiddle(Node head) {
        // Edge case: if the list is empty or has only one node
        if (head == null || head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;
        Node prev = null;

        // Move fast pointer two steps and slow pointer one step
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Now, slow is at the middle node, and prev is the node before it
        prev.next = slow.next; // Remove the middle node

        return head;
    }

    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        head = deleteMiddle(head);

        printList(head);
    }
}
