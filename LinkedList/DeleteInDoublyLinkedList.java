package LinkedList;

class Node {
    int data;
    Node prev;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class DeleteInDoublyLinkedList {

    public static Node deleteHead(Node head) {

        if (head == null || head.next == null)
            return null;

        Node prev = head;
        head = head.next;

        head.prev = null;
        prev.next = null;

        return head;
    }

    public static Node deleteTail(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.prev.next = null;
        temp.prev = null;

        return head;
    }

    // index 1-based
    public static Node deleteKthElement(Node head, int k) {

        if (k <= 0 || head == null)
            return head;

        // Case 1: delete head
        if (k == 1) {
            return deleteHead(head);
        }

        Node temp = head;
        int cnt = 1;

        // Move temp to kth node
        while (temp != null && cnt < k) {
            temp = temp.next;
            cnt++;
        }

        // Case 2: k > length
        if (temp == null) {
            return head;
        }

        Node prev = temp.prev;
        Node next = temp.next;

        // Case 3: temp is tail
        if (next == null) {
            return deleteTail(head);
        }

        // Case 4: temp is a middle node
        prev.next = next;
        next.prev = prev;

        // Optional clean-up
        temp.next = null;
        temp.prev = null;

        return head;
    }

    public static Node deleteValx(Node head, int val) {

        if (head == null)
            return null;

        // Case 1: delete head
        if (head.data == val) {
            return deleteHead(head);
        }

        Node temp = head;

        // Find the node with value = val
        while (temp != null) {
            if (temp.data == val)
                break;
            temp = temp.next;
        }

        // Case 2: value not found
        if (temp == null)
            return head;

        Node prev = temp.prev;
        Node next = temp.next;

        // Case 3: delete tail node with value
        if (next == null) {
            prev.next = null; // detach tail
            temp.prev = null;
            return head;
        }

        // Case 4: delete middle node
        prev.next = next;
        next.prev = prev;

        // cleanup (optional)
        temp.next = null;
        temp.prev = null;

        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;
        head.next.next = new Node(3);
        head.next.next.prev = head.next;

        head = deleteKthElement(head, 2);
        head = deleteValx(head, 3);

    }
}
