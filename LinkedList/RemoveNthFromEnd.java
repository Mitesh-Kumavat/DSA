package LinkedList;

public class RemoveNthFromEnd {

    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // BRUTE FORCE APPROACH
    public static Node removeNthFromEndBrute(Node head, int n) {

        int size = 0;
        Node temp = head;

        // Step 1: Count total number of nodes in the list
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        // Step 2: If we need to delete the first node
        if (n == size) {
            return head.next;
        }

        // Step 3: Find the index just before the node we want to remove
        int targetIndex = size - n; // position from the start
        temp = head;

        // Stop exactly at the node just before the target
        while (temp != null) {
            targetIndex--;

            // When targetIndex becomes 0, temp is exactly before the node to delete
            if (targetIndex == 0) {
                temp.next = temp.next.next; // remove the node
                break;
            }

            temp = temp.next;
        }

        return head;
    }

    // OPTIMAL APPROACH (TWO POINTER)
    public static Node removeNthFromEndOptimal(Node head, int n) {

        Node fast = head;
        Node slow = head;

        // Step 1: Move fast pointer n steps ahead
        // This creates a gap of n nodes between fast and slow
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Step 2: If fast becomes null here,
        // it means the node to delete is the head itself
        if (fast == null) {
            return head.next;
        }

        // Step 3: Move both fast and slow until fast reaches the last node
        // When fast.next becomes null, slow will be just before the target node
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Step 4: Remove the target node
        slow.next = slow.next.next;

        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        int n = 2;

        head = removeNthFromEndOptimal(head, n);

        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
