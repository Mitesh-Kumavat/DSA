package LinkedList;

public class RemoveDuplicatesFromSortedList {
    public static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    /*
     * PROBLEM:
     * Remove duplicates from a SORTED doubly linked list
     * 
     * IMPORTANT OBSERVATION:
     * - List is sorted
     * - Duplicate values will always be ADJACENT
     * 
     * INTUITION:
     * - Keep one pointer to last unique node
     * - Traverse next nodes
     * - If duplicate found → unlink it
     */
    public static Node removeDuplicates(Node head) {

        // Edge cases: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // lastNode -> last unique node we want to keep
        Node lastNode = head;

        // currentNode -> node being checked
        Node currentNode = head.next;

        while (currentNode != null) {

            // CASE 1: Duplicate found
            if (lastNode.data == currentNode.data) {

                // Remove currentNode from list
                lastNode.next = currentNode.next;

                // Fix backward link if needed
                if (currentNode.next != null) {
                    currentNode.next.prev = lastNode;
                }

                // Fully detach duplicate node
                currentNode.next = null;
                currentNode.prev = null;

                // Move currentNode forward
                currentNode = lastNode.next;
                continue;
            }

            // CASE 2: Not duplicate → move both pointers
            lastNode = currentNode;
            currentNode = currentNode.next;
        }

        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(1);
        head.next.prev = head;
        head.next.next = new Node(2);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(3);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.prev = head.next.next.next;

        printList(head);
        head = removeDuplicates(head);
        printList(head);
    }
}
