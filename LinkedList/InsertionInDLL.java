package LinkedList;

class Node {
    int data;
    Node prev;
    Node next;

    Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class InsertionInDLL {

    public static Node insertBeforeHead(Node head, int data) {
        if (head == null) {
            return new Node(data);
        }

        Node newEl = new Node(data);
        newEl.next = head;
        head.prev = newEl;
        head = newEl;

        return head;
    }

    public static Node insertAfterTail(Node head, int data) {
        if (head == null) {
            return new Node(data);
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        Node newEl = new Node(data);
        temp.next = newEl;
        newEl.prev = temp;
        return head;
    }

    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static Node insertBeforeValx(Node head, int data, int val) {

        if (head == null)
            return null;

        // Case 1: insert before head
        if (head.data == val) {
            return insertBeforeHead(head, data);
        }

        Node temp = head;

        // Find node with value = val
        while (temp != null && temp.data != val) {
            temp = temp.next;
        }

        // Case 2: value not found
        if (temp == null)
            return head;

        Node newEl = new Node(data);
        Node back = temp.prev;
        Node front = temp; // since we insert BEFORE temp

        // Link back <-> newEl
        back.next = newEl;
        newEl.prev = back;

        // Link newEl <-> front
        newEl.next = front;
        front.prev = newEl;

        return head;
    }

    public static Node insertBeforeKthIndex(Node head, int k, int data) {

        // Invalid index or empty list
        if (k <= 0 || head == null)
            return head;

        // Case 1: insert before head (k == 1)
        if (k == 1) {
            return insertBeforeHead(head, data);
        }

        Node temp = head;
        int count = 1;

        // Move temp to Kth node
        while (temp != null && count < k) {
            temp = temp.next;
            count++;
        }

        // Case 2: K > length -> cannot insert
        if (temp == null)
            return head;

        // Now temp is the Kth node, insert before temp
        Node newEl = new Node(data);

        Node back = temp.prev; // node before Kth
        Node front = temp; // Kth node itself

        // back <-> newEl
        back.next = newEl;
        newEl.prev = back;

        // newEl <-> front
        newEl.next = front;
        front.prev = newEl;

        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head = insertBeforeHead(head, 20);
        head = insertBeforeHead(head, 30);
        head = insertAfterTail(head, 40);
        head = insertAfterTail(head, 90);
        head = insertBeforeHead(head, 20);
        // 20 <-> 30 <-> 20 <-> 10 <-> 40 <-> 90 <-> null

        head = insertBeforeValx(head, 25, 10);
        // 20 <-> 30 <-> 20 <-> 25 <-> 10 <-> 40 <-> 90 <-> null

        head = insertBeforeKthIndex(head, 3, 15);
        // 20 <-> 30 <-> 15 <-> 20 <-> 25 <-> 10 <-> 40 <-> 90 <-> null

        printList(head);
    }
}
