package LinkedList;

public class DeleteAllOccurrences {

    public static class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    /*
     * PROBLEM:
     * Delete ALL occurrences of a given key from a
     * DOUBLY LINKED LIST.
     * 
     * Example:
     * 1 <-> 2 <-> 3 <-> 2 <-> 4
     * key = 2
     * 
     * Result:
     * 1 <-> 3 <-> 4
     */

    public static Node deleteAllOccurrences(Node head, int key) {

        /*
         * POINTERS USED
         * -------------------------------------------------
         * temp → current node being checked
         * prev → previous node of temp
         * nextNode → next node of temp
         */
        Node temp = head;
        Node prev = null;
        Node nextNode = null;

        /*
         * MAIN TRAVERSAL:
         * Traverse the entire list once
         * Time Complexity: O(n)
         */
        while (temp != null) {

            // Case 1: Current node value matches key
            if (temp.data == key) {

                /*
                 * If node to be deleted is HEAD
                 * Move head forward
                 */
                if (temp == head) {
                    head = head.next;
                }

                // Store next and previous nodes
                nextNode = temp.next;
                prev = temp.prev;

                /*
                 * Link next node with prev node
                 * Skip current node
                 */
                if (nextNode != null) {
                    nextNode.prev = prev;
                }

                if (prev != null) {
                    prev.next = nextNode;
                }

                // Move temp forward (important to avoid infinite loop)
                temp = temp.next;

            }
            // Case 2: Current node does NOT match key
            else {
                temp = temp.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {

        Node head = new Node(1);

        head.next = new Node(2);
        head.next.prev = head;

        head.next.next = new Node(3);
        head.next.next.prev = head.next;

        head.next.next.next = new Node(2);
        head.next.next.next.prev = head.next.next;

        head.next.next.next.next = new Node(4);
        head.next.next.next.next.prev = head.next.next.next;

        printList(head);

        int key = 2;
        head = deleteAllOccurrences(head, key);

        printList(head);
    }
}
