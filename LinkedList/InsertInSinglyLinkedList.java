package LinkedList;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class InsertInSinglyLinkedList {

    /*
     * INSERT AT HAND
     * Idea:
     * - Create a new node
     * - Point its next to current head
     * - Make new node the head
     * 
     * Example:
     * List : 10 -> 20 -> 30
     * insertAtHead(head, 5)
     * Result : 5 -> 10 -> 20 -> 30
     */
    public static Node insertAtHead(Node head, int data) {

        Node newEl = new Node(data);

        // if list is empty → new node becomes head
        if (head == null) {
            return newEl;
        }

        newEl.next = head; // new node points to old head
        return newEl; // return new head
    }

    /*
     * INSERT AT LAST
     * Idea:
     * - Traverse till last node
     * - Attach new node at the end
     * 
     * Example:
     * List: 10 → 20 → 30
     * insertAtLast(head, 99)
     * Result: 10 → 20 → 30 → 99
     */
    public static Node insertAtLast(Node head, int data) {
        if (head == null) {
            return new Node(data); // empty list
        }

        Node temp = head;

        // go to last node
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = new Node(data); // attach new node
        return head;
    }

    /*
     * INSERT AT K-TH POSITION (1-based index)
     * Idea:
     * - If k=1 → same as insertAtHead
     * - Otherwise move to (k-1)th node
     * - Insert new node between (k-1)th and k-th node
     * 
     * Example:
     * List : 10 → 20 → 30 → 40
     * insertAtKthPosition(head, 3, 99)
     * 
     * Step:
     * - (k-1)=2nd node = 20
     * Result:
     * 10 → 20 → 99 → 30 → 40
     */
    public static Node insertAtKthPosition(Node head, int k, int data) {

        if (k <= 0)
            return head;

        // inserting at head when k == 1
        if (head == null && k == 1) {
            return new Node(data);
        }

        if (k == 1) {
            Node newEl = new Node(data);
            newEl.next = head;
            return newEl;
        }

        Node temp = head;
        int cnt = 0;

        // Move to (k-1)th node
        while (temp != null) {
            cnt++;

            if (cnt == (k - 1)) {
                Node newEl = new Node(data);

                newEl.next = temp.next; // link new node to kth node
                temp.next = newEl; // link (k-1)th node to new node

                break;
            }

            temp = temp.next;
        }

        return head; // if k is out of range, original list returned
    }

    /*
     * INSERT BEFORE VALUE x
     * Idea:
     * - If x is at head → new node becomes new head
     * - Else → find node with value x
     * - Insert new node before it using prev pointer
     * 
     * Example:
     * List: 10 → 20 → 30 → 40
     * insertBeforeValx(head, 30, 99)
     * 
     * Result:
     * 10 → 20 → 99 → 30 → 40
     */
    public static Node insertBeforeValx(Node head, int val, int data) {

        if (head == null)
            return null;

        // Case 1: Insert before head
        if (head.data == val) {
            Node newEl = new Node(data);
            newEl.next = head;
            return newEl;
        }

        Node temp = head;
        Node prev = null;

        // Find the node with value = val
        while (temp != null) {

            if (temp.data == val) {
                Node newEl = new Node(data);

                prev.next = newEl; // link (previous node) → new node
                newEl.next = temp; // link new node → current node (value = val)

                return head;
            }

            prev = temp; // move prev forward
            temp = temp.next; // move temp forward
        }

        // value not found → no change
        return head;
    }

    public static void main(String[] args) {

        // Initially create: 1
        Node head = new Node(1);

        head = insertAtHead(head, 0); // 0 → 1
        head = insertAtHead(head, 10); // 10 → 0 → 1
        head = insertAtHead(head, 20); // 20 → 10 → 0 → 1
        // Now list: 20 → 10 → 0 → 1

        head = insertAtLast(head, 60); // end
        head = insertAtLast(head, 70); // end
        // List: 20 → 10 → 0 → 1 → 60 → 70

        head = insertAtKthPosition(head, 3, 100);
        // Insert at 3rd position:
        // 20 → 10 → 100 → 0 → 1 → 60 → 70

        head = insertBeforeValx(head, 1, 200);
        // 20 → 10 → 100 → 0 → 200 → 1 → 60 → 70

        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
